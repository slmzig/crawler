package dataengi.crawler

import dataengi.crawler.modules.ServerModule

import scala.io.StdIn

/** entry point of the microservice
  *
  *  starting http server
  *  getting host and port from application.conf
  */
object Bootstrap extends App with ServerModule {
  println("starting server ....")

  val future = server.start()
  StdIn.readLine() // let it run until user presses return, hack for sbt run
  future
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}
