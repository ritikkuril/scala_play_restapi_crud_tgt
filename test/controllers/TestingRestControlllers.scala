package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers.{GET, _}
import controllers.RestController

class TestingRestControlllers extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "RestController GET" should {

    "render the all page from the router" in {
      val request = FakeRequest(GET, "/all")
      val home = route(app, request).get


      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include(" [{\"id\":1,\"company_id\":\"tgt\",\"employee_id\":\"t1\",\"details\":\"RITIK\"},{\"id\":2,\"company_id\":\"apple\",\"employee_id\":\"a1\",\"details\":\"Aman\"},{\"id\":3,\"company_id\":\"tgt\",\"employee_id\":\"t2\",\"details\":\"Ayush\"},{\"id\":4,\"company_id\":\"tgt\",\"employee_id\":\"t3\",\"details\":\"priya\"},{\"id\":6,\"company_id\":\"apple\",\"employee_id\":\"a2\",\"details\":\"kretos\"}]")

    }
  }

    "RestController GET" should {

      "render the find page from the router" in {
        val request = FakeRequest(GET, "/getuser/tgt/t1")
        val home = route(app, request).get


        status(home) mustBe OK
        contentType(home) mustBe Some("text/html")
        contentAsString(home) must include(" [{\"id\":1,\"company_id\":\"tgt\",\"employee_id\":\"t1\",\"details\":\"RITIK\"}")

      }
    }

  "RestController DELETE" should {

    "render the delete page from the router" in {
      val request = FakeRequest(DELETE, "/del/tgt/t1")
      val home = route(app, request).get


      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("DELETED")

    }
  }


  "RestController UPDATE" should {

    "render the delete page from the router" in {
      val request = FakeRequest(PUT, "/updetail/tgt/t1/PLP")
      val home = route(app, request).get


      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("UPDATED")

    }
  }


  "RestController POST" should {

    "render the delete page from the router" in {
      val request = FakeRequest(POST, "/uinsert/tgt/t1/PLP")
      val home = route(app, request).get


      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("UPDATED")

    }
  }


}
