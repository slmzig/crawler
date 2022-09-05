package dataengi.crawler.controllers

import akka.http.scaladsl.model.{HttpEntity, MediaTypes, StatusCodes}
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.testkit.ScalatestRouteTest
import dataengi.crawler.models.{CrawlRequest, CrawlResponse, Data}
import dataengi.crawler.services.CrawlerService
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport._
import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import play.api.libs.json.Json

import scala.concurrent.Future

class CrawlerControllerTest
  extends AnyWordSpecLike
    with Matchers
    with ScalatestRouteTest
    with Directives
    with MockFactory {


  "test successful api/crawl endpoint" in {

    val expectedResponse = CrawlResponse(
      List(Data("url", "body"))
    )
    val crawlerService: CrawlerService = mock[CrawlerService]
    (crawlerService.crawl _).expects(List("https://google.com")).returning(Future.successful(expectedResponse)).once()

    lazy val crawlerController = new CrawlerController(crawlerService)

    val request = CrawlRequest(List("https://google.com"))

    val entity = HttpEntity(MediaTypes.`application/json`, Json.toJson(request).toString)

    Post("/api/crawl", entity) ~> crawlerController.routes  ~> check {
      status shouldBe StatusCodes.OK
      responseAs[CrawlResponse] shouldBe expectedResponse
    }
  }

}
