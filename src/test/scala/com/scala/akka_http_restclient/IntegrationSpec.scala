package com.scala.akka_http_restclient

import com.scala.akka_http_restclient.services.dao.MoviesDao
import com.scala.akka_http_restclient.utils.Migration
import org.scalatest.BeforeAndAfterAll

trait IntegrationSpec extends BaseSpec with Migration with BeforeAndAfterAll {
  val dao = MoviesDao()

  override protected def beforeAll() = {
    reloadSchema()
  }

  override protected def afterAll() = {
    reloadSchema()
  }

}
