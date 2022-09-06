package dataengi.crawler.models

import play.api.libs.json.{Format, Json}

case class Data(url: String, data: String)

object Data {
  implicit val DataFormat: Format[Data] = Json.format[Data]
}
