import controllers.FrontController
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.mvc.{Result, Results}
import play.api.test.FakeRequest
import play.api.test.Helpers._

import scala.concurrent.Future


/**
  * Created by knoldus on 8/3/17.
  */
class FrontControllerSpec extends PlaySpec with OneAppPerTest with Results{

  "Front Conroller#front" should {
    "should be valid" in {
      val controller=new FrontController
      val home = controller.front().apply(FakeRequest())
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Knoldus")
      }
  }
}
