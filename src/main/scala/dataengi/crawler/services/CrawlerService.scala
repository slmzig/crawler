package dataengi.crawler.services

import dataengi.crawler.controllers.CrawlResponse

import scala.concurrent.Future

trait CrawlerService {
  def crawl(endpoints: List[String]): Future[CrawlResponse]
}

final class CrawlerServiceImpl extends CrawlerService {
  override def crawl(endpoints: List[String]): Future[CrawlResponse] = ???
}
