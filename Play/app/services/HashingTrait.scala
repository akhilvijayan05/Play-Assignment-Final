package services

/**
  * Created by knoldus on 9/3/17.
  */
trait  HashingTrait {

  def getHash(str: String) : String
  def checkHash(str: String, strHashed: String): Boolean
}
