package com.enjoycity.dao

import com.enjoycity.domain.{History, User}
import com.mongodb.casbah.MongoClient
import com.novus.salat.global._
import com.novus.salat.dao._

object UserDAO extends SalatDAO[User, Long](collection = MongoClient()("enjoycity")("user")) {

  val children = new ChildCollection[History, Long](collection = MongoClient()("enjoycity")("history"),
    parentIdField = "idUser") {}

}