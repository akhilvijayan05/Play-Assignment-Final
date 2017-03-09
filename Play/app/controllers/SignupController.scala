package controllers

import javax.inject.Inject

import play.api.Configuration
import play.api.cache._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.{Constraint, Invalid, Valid, ValidationError}
import play.api.mvc.{Action, Controller}
import services._

import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 4/3/17.
  */
class SignupController @Inject() (hashing:HashingTrait,cacheService: CacheTrait,configuration: Configuration) extends Controller {

  val userForm = Form(
    mapping(
      "firstname" -> nonEmptyText,
      "middlename" -> text,
      "lastname" -> nonEmptyText,
      "username" -> text,
      "password" -> text,
      "repassword" -> text,
      "mobile" -> longNumber,
      "gender" -> text,
      "age" -> number(min=18 ,max=75),
      "hobbies" -> text,
      "isAdmin" ->boolean,
      "isSuspend"->boolean
    )(UserDetails.apply)(UserDetails.unapply)
  )

  def default = Action {

    Console.println()
  Ok(views.html.signup(""))
  }
  def store = Action {implicit request =>

    //val mobile=userForm.bindFromRequest.get

    //mobile.age.toS
    userForm.bindFromRequest.fold(
      formWithErrors => {

        Ok(views.html.signup("Please check you input !!"))
      },
        value => {

          val maybeUser: List[UserDetails] = cacheService.getCache("cache").toList.flatten
          def iterate(ls:List[UserDetails]):UserDetails= {
            ls match {
              case head :: tail if (value.username.equals(head.username)) => head
              case head :: Nil if (value.username.equals(head.username)) => head
              case head :: tail=>iterate(tail)
              case Nil=>null
            }
          }
          val result=iterate(maybeUser)
          if(value.mobile.toString.length==10 && value.password.equals(value.repassword)&& result==null)
            {
              val config=configuration.getString("type")
              config match{
                case Some(check) if check.equals("Admin")=>{
                  val newObject=value.copy(password = hashing.getHash(value.password),repassword = hashing.getHash(value.repassword),isAdmin = true)
                  cacheService.setCache("cache", newObject)
                  Redirect(routes.ProfileController.default).withSession("username" -> value.username).flashing("success"->"Successfull logged in. Your details are...")
                }
                case Some(check) if check.equals("Normal")=>{

                  val newObject=value.copy(password = hashing.getHash(value.password),repassword = hashing.getHash(value.repassword))

                  cacheService.setCache("cache", newObject)

                  Redirect(routes.ProfileController.default).withSession("username" -> value.username).flashing("success"->"Successfull logged in. Your details are...")
                }
                case None=>Ok("")
              }

            }
          else
            {
              Ok(views.html.signup("Please check you input !!"))
            }
        }
    )
  }
}
