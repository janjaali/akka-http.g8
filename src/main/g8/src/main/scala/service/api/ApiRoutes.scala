package service.api

import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.InternalServerError
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import akka.util.Timeout
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.duration._

class ApiRoutes(routes: Route*) extends LazyLogging {
  implicit val timeout: Timeout = 10.seconds

  def api(implicit actorSystem: ActorSystem): Route =
    handleExceptions(exceptionHandler) {
      pathPrefix("api" / "rest") {
        routes.foldLeft[Route](reject)(_ ~ _)
      }
    }

  private val exceptionHandler = ExceptionHandler {
    case ex: Exception =>
      logger.error(s"Catch exception in general exceptionHandler: \$ex")
      complete(HttpResponse(InternalServerError, entity = "Something strange happened."))
  }
}
