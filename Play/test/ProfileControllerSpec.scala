import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.mvc.Results
import play.api.test.FakeRequest
import play.api.test.Helpers._
import org.scalatest._

import scala.concurrent.Future
import play.api.mvc._
import controllers.ProfileController
import services.{CacheTrait, UserDetails}
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._


/**
  * Created by knoldus on 8/3/17.
  */
class ProfileControllerSpec extends PlaySpec with OneAppPerTest with Results with MockitoSugar{
  "Example Page#index" should {
    "should be valid" in {
      val customCache = mock[CacheTrait]
      val showObj = new ProfileController(customCache)
      val home = showObj.default().apply(FakeRequest())
      status(home) mustBe OK
  }
  }
}
