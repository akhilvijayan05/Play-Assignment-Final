import controllers.FrontController
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.mvc.{Result, Results}
import play.api.test.FakeRequest
import play.api.test.Helpers._

import scala.concurrent.Future


/**
  * Created by knoldus on 8/3/17.
  */
class FrontSpec extends PlaySpec with OneAppPerTest with Results{

  "Front Conroller#front" should {
    "should be valid" in {
      val controller=new FrontController
      val result: Future[Result] = controller.front().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText mustBe "text/html"
      }
  }
}
