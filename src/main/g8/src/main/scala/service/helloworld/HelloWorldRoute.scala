package service.helloworld

import akka.http.scaladsl.server.{Directives, Route}

class HelloWorldRoute extends Directives {
  val route: Route = {
    path("hello-world") {
      get {
        complete("Hello World!")
      }
    }
  }
}
