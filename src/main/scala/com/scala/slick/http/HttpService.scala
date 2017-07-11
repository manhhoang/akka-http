package com.scala.slick.http

import akka.http.scaladsl.server.Directives._
import com.scala.slick.http.routes.{AuthServiceRoute, UsersServiceRoute}
import com.scala.slick.services.{AuthService, UsersService}
import com.scala.slick.utils.CorsSupport

import scala.concurrent.ExecutionContext

class HttpService(usersService: UsersService,
                  authService: AuthService
                 )(implicit executionContext: ExecutionContext) extends CorsSupport {

  val usersRouter = new UsersServiceRoute(authService, usersService)
  val authRouter = new AuthServiceRoute(authService)

  val routes =
    pathPrefix("v1") {
      corsHandler {
        usersRouter.route ~
        authRouter.route
      }
    }

}
