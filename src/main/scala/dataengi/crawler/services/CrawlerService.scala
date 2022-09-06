package dataengi.crawler.services

import dataengi.crawler.models.CrawlResponse
import dataengi.crawler.processors.Parser

import scala.concurrent.Future

trait CrawlerService {
  def crawl(endpoints: List[String]): Future[CrawlResponse]
}

final class CrawlerServiceImpl(parser:Parser) extends CrawlerService {
  override def crawl(endpoints: List[String]): Future[CrawlResponse] = parser.stream(endpoints)
}
