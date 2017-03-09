package controllers

import javax.inject.Inject

import play.api.cache.CacheApi
import play.api.mvc.{Action, Controller}
import services.{CacheTrait, HashingPassword, UserDetails}

/**
  * Created by knoldus on 7/3/17.
  */
class MaintenanceController @Inject()(cache: CacheApi, cacheService: CacheTrait) extends Controller {

  def default = Action {implicit request=>

    val maybeUser: List[UserDetails] = cacheService.getCache("cache").toList.flatten
    request.session.get("username").map { user =>
      Ok(views.html.maintenance(user,maybeUser))
    }.getOrElse {
      Ok(views.html.maintenance("",maybeUser))
  }
  }

  def suspendUser(user: String) = Action {implicit request=>

    val maybeUser: List[UserDetails] = cacheService.getCache("cache").toList.flatten
    def iterate(ls:List[UserDetails]):UserDetails= {
      ls match {
        case head :: tail if (user.equals(head.username)) => head
        case head :: Nil if (user.equals(head.username)) => head
        case head :: tail=>iterate(tail)
        case Nil=>null
      }
    }
    val result=iterate(maybeUser)
    //cacheService.removeCache(user)
    if(result!=null){
      //println(result)
      cacheService.removeCache(result)
      //cacheService.setCache("cache",result.copy(isSuspend = true))

    }
    println(""+cacheService.getCache("cache").toList.flatten)
    request.session.get("username").map { user =>
      //Ok("Success")
      Ok(views.html.maintenance(user,cacheService.getCache("cache").toList.flatten))
    }.getOrElse {
      Ok(views.html.maintenance("",cacheService.getCache("cache").toList.flatten))
    }
    //Ok("")
  }
  def resumeUser(user: String) = Action {implicit request=>

    val maybeUser: List[UserDetails] = cacheService.getCache("cache").toList.flatten

    def iterate(ls:List[UserDetails]):UserDetails= {
      ls match {
        case head :: tail if (user.equals(head.username)) => head
        case head :: Nil if (user.equals(head.username)) => head
        case head :: tail=>iterate(tail)
        case Nil=>null
      }
    }
    val result=iterate(maybeUser)

    if(result!=null){
      cacheService.removeCache(result)
      //cacheService.setCache("cache",result.copy(isSuspend = false))
    }
    println(""+cacheService.getCache("cache").toList.flatten)
    request.session.get("username").map { user =>
      Ok(views.html.maintenance(user,cacheService.getCache("cache").toList.flatten))
    }.getOrElse {
      Ok(views.html.maintenance(user,cacheService.getCache("cache").toList.flatten))
    }
    //Ok(views.html.maintenance(maybeUser))
    //cacheService.setCache("cache",user)
    //Ok("")
  }

}