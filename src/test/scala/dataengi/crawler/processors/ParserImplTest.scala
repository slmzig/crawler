package dataengi.crawler.processors

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.Config
import dataengi.crawler.models.{CrawlResponse, Data}
import org.scalamock.scalatest.MockFactory
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.wordspec.AnyWordSpec

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContextExecutor, Future}

class ParserImplTest extends AnyWordSpec with MockFactory with GivenWhenThen {

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher


  "test parser method" in {

    Given("url that we need to process")
    val url = "https://google.com"

    Given("expectedResult - this is mock result for processing google.com url")
    val expectedResult = "<html><title>google</title></html>"

    Given("mocked classes: ParserClient / Config")
    val client: ParserClient = mock[ParserClient]
    val config: Config = mock[Config]

    Given("mock parallelism value")
    (config.getInt _).expects("server.stream.parallelism").returning(10).once()

    Given("mock result from parser client")
    (client.connectAndParse _).expects(url).returning(Future.successful(Data(url, expectedResult))).once()

    val parser = new ParserImpl(client, config)(materializer, executionContext)

    When("when we pass url to parser")
    val result: Future[Data] = parser.parse(url)

    Then("we get html body")
    Await.result(result, 3.seconds) shouldEqual Data(url, expectedResult)
  }

  "test stream method" in {

    val data: Data = Data("https://google.com", "<html><title>google</title></html>")

    val client: ParserClient = mock[ParserClient]
    val config: Config = mock[Config]
    (config.getInt _).expects("server.stream.parallelism").returning(10).once()
    val parser = new ParserImpl(client, config)(materializer, executionContext)
    (client.connectAndParse _).expects(data.url).returning(Future.successful(data)).once()

    val futureResult = parser.stream(List("https://google.com"))

    val actualResult = Await.result(futureResult, 5.seconds)

    actualResult shouldEqual CrawlResponse(List(data))
  }
}
