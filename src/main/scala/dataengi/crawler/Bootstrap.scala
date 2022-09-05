package dataengi.crawler

import akka.http.scaladsl.Http
import dataengi.crawler.modules.ServerModule

import scala.concurrent.Future
import scala.util.{Failure, Success}

object Bootstrap extends App with ServerModule {
  println("starting server ....")

  startServer().onComplete {
    case Success(_) =>
      println("Application initialize successfully")
    case Failure(error) =>
      println("Couldn't initialize application", error)
      sys.exit(1)
  }

  private def startServer(): Future[Http.ServerBinding] =
    server
      .start()
      .transform {
        case res@Success(binding) =>
          println(s"Server start on ${binding.localAddress}")
          res
        case fail@Failure(_) => fail
      }
}
