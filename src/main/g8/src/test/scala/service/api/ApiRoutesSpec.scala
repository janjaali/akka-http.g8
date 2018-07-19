package service.api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FreeSpec, Matchers}

class ApiRoutesSpec extends FreeSpec with Matchers with ScalatestRouteTest {
  val helloRoute: Route = pathPrefix("hello") {
    complete("hello!")
  }

  val worldRoute: Route = pathPrefix("world") {
    complete("world!")
  }

  "The ApiRoutes" - {
    val apiRoutes = new ApiRoutes(helloRoute, worldRoute)

    "when requested the first route (/api/rest/hello)" - {
      val request = Get("/api/rest/hello")

      "should respond with 'hello!'" in {
        request ~> apiRoutes.api ~> check {
          status shouldBe StatusCodes.OK
          responseAs[String] shouldBe "hello!"
        }
      }
    }

    "when requested the second route (/api/rest/world)" - {
      val request = Get("/api/rest/world")

      "should respond with 'world!'" in {
        request ~> apiRoutes.api ~> check {
          status shouldBe StatusCodes.OK
          responseAs[String] shouldBe "world!"
        }
      }
    }
  }
}
