package dataengi.crawler.controllers

import akka.http.scaladsl.server.{Directives, Route}
import dataengi.crawler.services.CrawlerService
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport._
import play.api.libs.json.{Format, Json}

import scala.concurrent.Future

case class CrawlResponse()

object CrawlRequest {
  implicit val CrawlRequestFormat: Format[CrawlRequest] = Json.format[CrawlRequest]
}

object CrawlResponse {
  implicit val CrawlResponseFormat: Format[CrawlResponse] = Json.format[CrawlResponse]
}


case class CrawlRequest(urls:List[String])

final class CrawlerController(crawlService:CrawlerService) extends Controller with Directives {

  val routes: Route =
    path("api/crawl") {
      post {
        entity(as[CrawlRequest]) { request: CrawlRequest =>
          onSuccess(crawlService.crawl(request.urls))((result: CrawlResponse) => complete(Json.toJson(result)))
        }
      }
    }
}


