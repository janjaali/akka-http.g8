package service

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object Server extends LazyLogging {
  def main(args: Array[String]): Unit = {
    logger.info("Starting $project_name$ service.")

    val config = ConfigFactory.load()

    implicit val system: ActorSystem = ActorSystem("$project_name$")
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher
    implicit val materializer: ActorMaterializer = ActorMaterializer.create(system)

    val host = "0.0.0.0"
    val port = config.getInt("port")

    Http().bindAndHandle(Routes.routes, host, port)
      .onComplete {
        case Success(_) => logger.info(s"Server started on http://\$host:\$port/")
        case Failure(e) =>
          logger.error("Server startup failed", e)
          System.exit(1)
      }
  }
}
