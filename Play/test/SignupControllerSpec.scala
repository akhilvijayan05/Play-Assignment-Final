import controllers.SignupController
import org.mockito.Mockito.when
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.mvc.Results
import play.api.test.FakeRequest
import play.api.test.Helpers._
import services.{CacheTrait, HashingTrait, UserDetails}
import org.scalatest.mock.MockitoSugar
import play.api.Configuration

/**
  * Created by knoldus on 8/3/17.
  */
class SignupControllerSpec extends PlaySpec with OneAppPerTest with Results with MockitoSugar {


  "SignupController" should {

    "render the Profile page" in {
      val mockDataService = mock[CacheTrait]
      val hash = mock[HashingTrait]
      val config = mock[Configuration]
      val showObj = new SignupController(hash,mockDataService,config)
      when(mockDataService.getCache("")) thenReturn Some(List[UserDetails]())
      when(hash.getHash("")) thenReturn ""
      val home = showObj.store.apply(FakeRequest())
      status(home) equals 303

    }

  }
}