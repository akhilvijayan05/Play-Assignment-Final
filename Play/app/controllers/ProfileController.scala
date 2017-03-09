package controllers

import javax.inject.Inject

import play.api.cache
import play.api.cache.CacheApi
import play.api.data._
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services._


class ProfileController @Inject() (cache: CacheApi,cacheService:CacheTrait) extends Controller {

  def default=Action{implicit request=>

    val message=request.flash.get("success").getOrElse("Welcome!")
    request.session.get("username").map { user =>
      val maybeUser: List[UserDetails] = cacheService.getCache("cache").toList.flatten
      //Console.println(maybeUser)
      def iterate(ls:List[UserDetails]):UserDetails= {
        ls match {
          case head :: tail if (user.equals(head.username)) => head
          case head :: tail=>iterate(tail)
          case Nil=>null
        }
      }
      val result=iterate(maybeUser)
      if(result!=null){
        if(result.isAdmin==false)
          Ok(views.html.profile("",message,result.firstname,result.middlename,result.lastname,user,result.mobile,result.gender,result.age,result.hobbies))
        else
          Ok(views.html.profile("Maintenance",message,result.firstname,result.middlename,result.lastname,user,result.mobile,result.gender,result.age,result.hobbies))
      }
        else
        Ok(views.html.profile("","","","","","",0,"",0,""))
      /*val maybeUser: Option[UserDetails] = cacheService.getcache("cache")
Console.println(maybeUser)
      maybeUser match {
        case Some(result) if (user.equals(result.username) && result.isAdmin==false)=>Ok(views.html.profile("",message,result.firstname,result.middlename,result.lastname,user,result.mobile,result.gender,result.age,result.hobbies))
        case Some(result) if (user.equals(result.username) && result.isAdmin==true)=>Ok(views.html.profile("Maintenance",message,result.firstname,result.middlename,result.lastname,user,result.mobile,result.gender,result.age,result.hobbies))
        case None =>Ok(views.html.profile("","","","","","",0,"",0,""))
      }*/
    }.getOrElse {
      Ok(views.html.profile("","","","","","",0,"",0,""))
    }
  }
}
