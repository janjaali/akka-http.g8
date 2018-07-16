package service

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FreeSpec, Matchers}

class RoutesSpec extends FreeSpec with Matchers with ScalatestRouteTest {
  "The server routes" - {
    val serverRoutes = Routes.routes

    "when asked to greet" - {
      val request = Get("/api/rest")

      "should answer with 'hello world!'" in {
        request ~> serverRoutes ~> check {
          status shouldBe StatusCodes.OK
          responseAs[String] shouldBe "hello world!"
        }
      }
    }
  }
}
