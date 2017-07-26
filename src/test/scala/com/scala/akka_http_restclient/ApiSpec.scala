package com.scala.akka_http_restclient

import akka.event.{ LoggingAdapter, NoLogging }
import akka.http.scaladsl.testkit.ScalatestRouteTest

trait ApiSpec extends IntegrationSpec with ScalatestRouteTest {
  protected val log: LoggingAdapter = NoLogging
}
