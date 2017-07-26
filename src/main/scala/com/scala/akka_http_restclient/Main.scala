package com.scala.akka_http_restclient

import akka.event.{ Logging, LoggingAdapter }
import akka.http.scaladsl.Http
import com.scala.akka_http_restclient.http.HttpService
import com.scala.akka_http_restclient.utils.{ ActorContext, Config, Migration }

object Main extends App with Config with Migration with ActorContext {
  val log: LoggingAdapter = Logging(system, getClass)
  val httpService = HttpService()

  migrate()

  Http().bindAndHandle(httpService.routes, httpInterface, httpPort)
}
