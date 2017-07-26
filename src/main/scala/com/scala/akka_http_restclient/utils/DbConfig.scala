package com.scala.akka_http_restclient.utils

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

trait DbConfig {
  protected val dbProfile: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("db.movies")
  protected val db = dbProfile.db
}
