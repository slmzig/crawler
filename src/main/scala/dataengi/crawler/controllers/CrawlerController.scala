package dataengi.crawler.controllers

import akka.http.scaladsl.server.{Directives, Route}
import dataengi.crawler.services.CrawlerService
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport._
import play.api.libs.json.{Format, Json}

case class Data(url: String, body: String)

case class CrawlResponse(result: List[Data])

object CrawlRequest {
  implicit val CrawlRequestFormat: Format[CrawlRequest] = Json.format[CrawlRequest]
}

object Data {
  implicit val DataFormat: Format[Data] = Json.format[Data]
}

object CrawlResponse {
  implicit val CrawlResponseFormat: Format[CrawlResponse] = Json.format[CrawlResponse]
}


case class CrawlRequest(urls: List[String])

final class CrawlerController(crawlService: CrawlerService) extends Controller with Directives {

  val routes: Route =
    path("api" / "crawl") {
      post {
        entity(as[CrawlRequest]) { request: CrawlRequest =>
          onSuccess(crawlService.crawl(request.urls))((result: CrawlResponse) => complete(Json.toJson(result)))
        }
      }
    }
}


