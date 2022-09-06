package dataengi.crawler

import dataengi.crawler.modules.ServerModule

import scala.io.StdIn

object Bootstrap extends App with ServerModule {
  println("starting server ....")

  val future = server.start()
  StdIn.readLine() // let it run until user presses return
  future
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}
