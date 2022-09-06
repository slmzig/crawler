package dataengi.crawler.models

import play.api.libs.json.{Format, Json}

case class CrawlRequest(urls: List[String])

object CrawlRequest {
  implicit val CrawlRequestFormat: Format[CrawlRequest] = Json.format[CrawlRequest]
}
