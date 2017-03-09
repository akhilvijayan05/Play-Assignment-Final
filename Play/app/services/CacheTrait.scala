package services

import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 7/3/17.
  */
trait CacheTrait {

  def setCache(value:String,newObject:UserDetails):Boolean
  def getCache(value:String):Option[List[UserDetails]]
  def removeCache(value:UserDetails):Boolean
}
