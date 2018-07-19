package service

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FreeSpec, Matchers}

class ApiRoutesSpec extends FreeSpec with Matchers with ScalatestRouteTest {
  "The server routes" - {
    val helloWorldRoute = new HelloWorldRoute().route
    val serverRoutes = ApiRoutes.routes(helloWorldRoute)

    "when asked to greet" - {
      val request = Get("/api/rest/hello-world")

      "should answer with 'hello world!'" in {
        request ~> serverRoutes ~> check {
          status shouldBe StatusCodes.OK
          responseAs[String] shouldBe "Hello World!"
        }
      }
    }
  }
}
