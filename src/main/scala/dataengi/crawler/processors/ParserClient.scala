package dataengi.crawler.processors

import dataengi.crawler.models.Data
import org.jsoup.Jsoup

import scala.concurrent.{ExecutionContext, Future}

/**
  * in this file we can add different parser clients
  * then we can set one that we want to use in ServerModule.scala
  */
trait ParserClient {
  def connectAndParse(url: String): Future[Data]
}

class JsoupParserClient(implicit executionContext: ExecutionContext) extends ParserClient {
  override def connectAndParse(url: String): Future[Data] =
    Future(Data(url, Jsoup.connect(url).get().toString))
}
