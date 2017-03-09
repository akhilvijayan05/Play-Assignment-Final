package Services

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.mvc.Results
import services.{CacheService, CacheTrait, HashingTrait, UserDetails}
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import play.api.cache.CacheApi

/**
  * Created by knoldus on 9/3/17.
  */
class ServiceSpec extends PlaySpec with MockitoSugar with Results {

  "CacheService should" should {

    "check existence of  user in cache" in {

      val cache = mock[CacheApi]
      val serviceObject = new CacheService(cache)
      when(cache.get[List[UserDetails]]("")) thenReturn Some(List[UserDetails]())
      serviceObject.getCache("") mustBe  Some(List[UserDetails]())
    }
  }


}

