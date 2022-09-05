package dataengi.crawler.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.typesafe.config.Config

import scala.concurrent.Future

final class HttpServer(config: Config, restApi: RestApi)(implicit system: ActorSystem) {
  private val host: String = config.getString("server.address.host")
  private val port: Int = config.getInt("server.address.port")

  def start(): Future[Http.ServerBinding] = Http().bindAndHandle(restApi.routes, host, port)
}