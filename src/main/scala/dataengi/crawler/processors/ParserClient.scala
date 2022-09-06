package dataengi.crawler.processors

import dataengi.crawler.models.Data
import org.jsoup.Jsoup

/**
 * in this file we can add different parser clients
 * then we can set one that we want to use in ServerModule.scala
 */
trait ParserClient {
  def connectAndParse(url: String): Data
}

class JsoupParserClient extends ParserClient {
  override def connectAndParse(url: String): Data = Data(url, Jsoup.connect(url).get().toString)
}