import controllers.{SigninController, routes}
import org.mockito.Mockito.when
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.mvc.Results
import play.api.test.FakeRequest
import play.api.test.Helpers._
import org.scalatest.mock.MockitoSugar
import services.{CacheTrait, HashingTrait, UserDetails}


class SigninControllerSpec extends PlaySpec with OneAppPerTest with Results with MockitoSugar{


  "SigninController" should {
    "render the Profile page" in {
      val cache = mock[CacheTrait]
      val hash = mock[HashingTrait]
      val showObj = new SigninController(hash,cache)
      val user = UserDetails("aa", "aa", "aa", "aa", "aa", "aa", 0, "", 20, "", false, false)
      when(cache.getCache("")) thenReturn Some(List(user))
      when(hash.checkHash("","")) thenReturn true
      val home = showObj.check.apply(FakeRequest())
      status(home) equals 303
    }

  }
}
