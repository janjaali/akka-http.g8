package service

import akka.http.scaladsl.server.{Directives, Route}

class HelloWorldRoute extends Directives {
  val route: Route = {
    pathPrefix("hello-world") {
      complete("Hello World!")
    }
  }
}
