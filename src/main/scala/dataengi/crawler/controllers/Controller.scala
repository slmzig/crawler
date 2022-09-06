package dataengi.crawler.controllers

import akka.http.scaladsl.server.Route

/** common controller
  *
  * in this trait we can add all common methods
  */
trait Controller {
  def routes: Route
}
