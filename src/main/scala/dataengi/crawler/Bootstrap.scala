package dataengi.crawler

import dataengi.crawler.modules.ServerModule

import scala.util.{Failure, Success}

/** entry point of the microservice
  *
  *  starting http server
  *  getting host and port from application.conf
  */
object Bootstrap extends App with ServerModule {
  println("starting server ....")

  val binding = server.start()

  binding.onComplete {
    case Success(_) => println("Success!")
    case Failure(error) => println(s"Failed: ${error.getMessage}")
  }

}
