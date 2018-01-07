package service

import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.InternalServerError
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import akka.util.Timeout
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.duration._

object Routes extends LazyLogging {
  implicit val timeout: Timeout = 10.seconds

  def routes(implicit actorSystem: ActorSystem): Route =
    handleExceptions(exceptionHandler) {
      pathPrefix("api" / "rest") {
        complete("hello world!")
      }
    }

  private val exceptionHandler = ExceptionHandler {
    case ex: Exception =>
      logger.error(s"catch Exception in general exceptionHandler")
      logger.error(ex.getMessage)
      complete(HttpResponse(InternalServerError, entity = "Something strange happened"))
  }
}
