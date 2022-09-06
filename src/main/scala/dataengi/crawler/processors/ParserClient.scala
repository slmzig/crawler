package dataengi.crawler.processors

import dataengi.crawler.models.Data
import org.jsoup.Jsoup

trait ParserClient {
  def connectAndParse(url: String): Data
}

class JsoupParserClient extends ParserClient {
  override def connectAndParse(url: String): Data = Data(url, Jsoup.connect(url).get().toString)
}