import controllers.routes
import org.mockito.Mockito.when
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.mvc.Results
import play.api.test.FakeRequest
import play.api.test.Helpers._
import org.scalatest.mock.MockitoSugar
import services.{CacheTrait, HashingTrait, UserDetails}


class SigninSpec extends PlaySpec with OneAppPerTest with Results with MockitoSugar{

  "Signin Page#default" should {
    "should be valid" in {
      val logRoute=route(app,FakeRequest(GET,"/signin")).get
      contentType(logRoute) mustBe Some("text/html")
      status(logRoute) mustBe OK
    }
  }
//  "SigninController" should {
//
//    "render the Profile page" in {
//      val mockDataService = mock[CacheTrait]
//      when(mockDataService.getCache("")) thenReturn Some(List[UserDetails]())
//      when(mockDataService.removeCache("")) thenReturn Some(List[UserDetails]())
//      val mockService = mock[HashingTrait]
//      when(mockService.checkHash("","")) thenReturn true
//      val home = route(app, FakeRequest(POST, "/signin")).get
//      status(home) equals 303
//    }
//
//  }
}
