package dataengi.crawler.modules

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.softwaremill.macwire.wire
import com.typesafe.config.{Config, ConfigFactory}
import dataengi.crawler.controllers.CrawlerController
import dataengi.crawler.processors.{JsoupParserClient, Parser, ParserClient, ParserImpl}
import dataengi.crawler.server.HttpServer
import dataengi.crawler.services.{CrawlerService, CrawlerServiceImpl}

import scala.concurrent.ExecutionContextExecutor


trait ServerModule {
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  lazy val config: Config = ConfigFactory.load()
  lazy val server: HttpServer = wire[HttpServer]
  lazy val crawlerController: CrawlerController = wire[CrawlerController]
  lazy val crawlerService: CrawlerService = wire[CrawlerServiceImpl]
  lazy val parser: Parser = wire[ParserImpl]
  lazy val crawlerSe: ParserClient = wire[JsoupParserClient]

}

