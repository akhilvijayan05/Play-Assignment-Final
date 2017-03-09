import controllers.MaintenanceController
import org.mockito.Mockito.when
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.mvc.Results
import play.api.test.FakeRequest
import play.api.test.Helpers._
import org.scalatest.mock.MockitoSugar
import services.{CacheTrait, HashingTrait, UserDetails}


/**
  * Created by knoldus on 9/3/17.
  */
class MaintenanceControllerSpec extends PlaySpec with OneAppPerTest with Results with MockitoSugar {

  "Maintenance Controller#default" should {
    "should be valid" in {
      val customCache = mock[CacheTrait]
      when(customCache.getCache("")) thenReturn Some(List[UserDetails]())
      val showObj = new MaintenanceController(customCache)
      val home = showObj.suspendUser("").apply(FakeRequest())
      status(home) mustBe OK
    }
  }
}
