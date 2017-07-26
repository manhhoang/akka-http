package com.scala.akka_http_restclient.services

import com.scala.akka_http_restclient.models._
import com.scala.akka_http_restclient.services.dao.MoviesDao
import com.scala.akka_http_restclient.services.external.ImdbService
import com.scala.akka_http_restclient.utils.ActorContext

import scala.concurrent.Future

trait MovieService {

  def save(movieRegistration: MovieRegistration): Future[RegistrationResult.Value]

  def reserve(movieIdentification: MovieIdentification): Future[ReservationResult.Value]

  def read(movieIdentification: MovieIdentification): Future[Option[MovieInformation]]

}

object MovieService {
  def apply(): MovieService = new MovieServiceImpl(MoviesDao(), ImdbService())
}

class MovieServiceImpl(dao: MoviesDao, imdbService: ImdbService) extends MovieService with ActorContext {

  override def save(movieRegistration: MovieRegistration): Future[RegistrationResult.Value] = {
    val existingMovie = read(movieRegistration.movieIdentification)

    def save =
      for {
        movieTitle <- imdbService.movieTitleById(movieRegistration.imdbId)
        movieInfo = new MovieInformation(movieRegistration, movieTitle)
        _ <- dao.create(movieInfo)
      } yield RegistrationResult.RegitrationSuccessful

    existingMovie flatMap {
      case Some(x) => Future.successful(RegistrationResult.AlreadyExists)
      case None => save

    }
  }

  override def reserve(movieIdentification: MovieIdentification): Future[ReservationResult.Value] = {
    val existingMovie = read(movieIdentification)
    existingMovie flatMap {
      case Some(x) =>
        if (x.reservedSeats < x.availableSeats)
          dao.update(x.reserveOneSeat()).map(_ => ReservationResult.ReservationSuccessful)
        else
          Future.successful(ReservationResult.NoSeatsLeft)
      case None =>
        Future.successful(ReservationResult.NoSuchMovie)
    }
  }

  override def read(movieIdentification: MovieIdentification): Future[Option[MovieInformation]] = {
    dao.read(movieIdentification)
  }

}
