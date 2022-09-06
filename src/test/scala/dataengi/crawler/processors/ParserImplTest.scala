package dataengi.crawler.processors

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.Config
import dataengi.crawler.models.{CrawlResponse, Data}
import org.scalamock.scalatest.MockFactory
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.wordspec.AnyWordSpec

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContextExecutor}

class ParserImplTest
  extends AnyWordSpec
    with MockFactory {

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val data: Data = Data("https://google.com", "<html><title>google</title></html>")
  "test parser" in {
    val client: ParserClient = mock[ParserClient]
    val config: Config = mock[Config]
    (config.getInt _).expects("server.stream.parallelism").returning(10).once()
    val parser = new ParserImpl(client, config)(materializer, executionContext)

    (client.connectAndParse _).expects(data.url).returning(data).once()
    val result: Data = parser.parse(data.url)
    result shouldEqual data
  }

  "test stream" in {
    val client: ParserClient = mock[ParserClient]
    val config: Config = mock[Config]
    (config.getInt _).expects("server.stream.parallelism").returning(10).once()
    val parser = new ParserImpl(client, config)(materializer, executionContext)
    (client.connectAndParse _).expects(data.url).returning(data).once()

    val futureResult = parser.stream(List("https://google.com"))

    val actualResult = Await.result(futureResult, 5.seconds)

    actualResult shouldEqual CrawlResponse(List(data))
  }
}
