package dataengi.crawler.controllers

import akka.http.scaladsl.server.{Directives, Route}
import dataengi.crawler.models.{CrawlRequest, CrawlResponse}
import dataengi.crawler.services.CrawlerService
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport._
import play.api.libs.json.{Format, Json}

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


