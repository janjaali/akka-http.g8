package service

import akka.actor.ActorSystem
import akka.http.scaladsl.model.StatusCodes.InternalServerError
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import akka.util.Timeout
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.duration._

object ApiRoutes extends LazyLogging {
  implicit val timeout: Timeout = 10.seconds

  def routes(routes: Route*)(implicit actorSystem: ActorSystem): Route =
    handleExceptions(exceptionHandler) {
      pathPrefix("api" / "rest") {
        routes.foldLeft[Route](reject)(_ ~ _)
      }
    }

  private val exceptionHandler = ExceptionHandler {
    case ex: Exception =>
      logger.error(s"Catch exception in general exceptionHandler: \$ex")
      complete(InternalServerError, "Something strange happened.")
  }
}
