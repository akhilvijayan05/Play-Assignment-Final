import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.cache.CacheApi
import play.api.mvc.Results
import services.{CacheService, HashingPassword, UserDetails}

/**
  * Created by knoldus on 9/3/17.
  */
class ServiceSpec extends PlaySpec with OneAppPerTest with MockitoSugar with Results {

  "CacheService should" should {

    "check existence of  user in cache" in {

      val cache = mock[CacheApi]
      val serviceObject = new CacheService(cache)
      val user = UserDetails("aa", "aa", "aa", "aa", "aa", "aa", 0, "", 20, "", false, false)
      when(cache.get[List[UserDetails]]("")) thenReturn Some(List(user))
      serviceObject.getCache("") mustBe  Some(List(user))
    }
  }
//
//  "CacheService should" should {
//
//    "set  user in cache" in {
//
//      val cache = mock[CacheApi]
//      val serviceObject = new CacheService(cache)
//      val user = UserDetails("aa", "aa", "aa", "aa", "aa", "aa", 0, "", 20, "", false, false)
//      cache.set("aa", user)
//      when(serviceObject.setCache("aa",user)) thenReturn true
//    }
//  }
//"CacheService should" should {
//
//    "remove  user in cache" in {
//
//      val cache = mock[CacheApi]
//      val serviceObject = new CacheService(cache)
//      val user = UserDetails("aa", "aa", "aa", "aa", "aa", "aa", 0, "", 20, "", false, false)
//      cache.remove("aa")
//      cache.set("aa", user)
//      when(serviceObject.removeCache(user)) thenReturn true
//    }
//  }

//  "HashingService should" should{
//
//    "hash user password" in{
//
//      val serviceObject = new HashingPassword
//      serviceObject.getHash("aa") mustBe ""
//    }
//  }

//  "HashingService should" should{
//
//    "check user password" in{
//
//      val serviceObject = new HashingPassword
//      serviceObject.checkHash("aa","aa") mustBe true
//    }
//  }


}

