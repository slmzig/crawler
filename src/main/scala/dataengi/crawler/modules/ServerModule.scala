package dataengi.crawler.modules

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.softwaremill.macwire.wire
import com.typesafe.config.{Config, ConfigFactory}
import dataengi.crawler.controllers.CrawlerController
import dataengi.crawler.server.{HttpServer, RestApi}
import dataengi.crawler.services.{CrawlerService, CrawlerServiceImpl}

import scala.concurrent.ExecutionContextExecutor


trait ServerModule {
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  lazy val config: Config = ConfigFactory.load()
  lazy val server: HttpServer = wire[HttpServer]
  lazy val crawlerController: CrawlerController = wire[CrawlerController]
  lazy val restAPI: RestApi = wire[RestApi]
  lazy val crawlerService: CrawlerService = wire[CrawlerServiceImpl]

}

