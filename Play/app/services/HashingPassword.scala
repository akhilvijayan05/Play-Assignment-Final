package services

import javax.inject.Inject

import org.mindrot.jbcrypt.BCrypt
import play.api.cache.CacheApi


/**  * Created by sumit on 4/30/16.  *  Hashing Password Using BCrypt  *  */
class HashingPassword @Inject() extends HashingTrait{
  def getHash(str: String) : String = {
    BCrypt.hashpw(str, BCrypt.gensalt())
  }
  def checkHash(str: String, strHashed: String): Boolean = {
    BCrypt.checkpw(str,strHashed)
  }
}
