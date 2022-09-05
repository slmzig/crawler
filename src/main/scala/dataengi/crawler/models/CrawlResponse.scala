package dataengi.crawler.models

import play.api.libs.json.{Format, Json}

case class CrawlResponse(result: List[Data])

object CrawlResponse {
  implicit val CrawlResponseFormat: Format[CrawlResponse] = Json.format[CrawlResponse]
}