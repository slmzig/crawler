package dataengi.crawler.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.typesafe.config.Config
import dataengi.crawler.controllers.CrawlerController

import scala.concurrent.Future

/**
 * akka http server
 *
 * @param config
 * @param crawlerController
 * @param system
 */
final class HttpServer(config: Config, crawlerController: CrawlerController)(implicit system: ActorSystem) {
  private val host: String = config.getString("server.address.host")
  private val port: Int = config.getInt("server.address.port")

  def start(): Future[Http.ServerBinding] = Http().bindAndHandle(crawlerController.routes, host, port)
}