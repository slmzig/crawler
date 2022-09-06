package dataengi.crawler.processors

import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Keep, Sink, Source}
import com.typesafe.config.Config
import dataengi.crawler.models.{CrawlResponse, Data}

import scala.concurrent.{ExecutionContext, Future}

trait Parser {
  def parse(url: String): Data

  def stream(urls: List[String]): Future[CrawlResponse]
}

class ParserImpl(parserClient: ParserClient, config: Config)(implicit val materializer: ActorMaterializer,
                                                             implicit val executionContext: ExecutionContext)
    extends Parser {

  private val parallelism: Int = config.getInt("server.stream.parallelism")

  def parse(url: String): Data =
    parserClient.connectAndParse(url)

  def stream(urls: List[String]): Future[CrawlResponse] =
    Source(urls)
      .mapAsync(parallelism) { url: String =>
        // todo change this
        Future.successful(parserClient.connectAndParse(url))
      }
      .toMat(Sink.fold(List.empty[Data]) { (agg: List[Data], data: Data) =>
        data :: agg
      })(Keep.right)
      .run()
      .map(CrawlResponse(_))
}
