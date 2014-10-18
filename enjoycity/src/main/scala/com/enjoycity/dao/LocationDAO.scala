package com.enjoycity.dao

import com.enjoycity.domain.{Content, Location}
import com.mongodb.casbah.MongoClient
import com.novus.salat.global._
import com.novus.salat.dao._

object LocationDAO extends SalatDAO[Location, Long](collection = MongoClient()("enjoycity")("location")) {

  val children = new ChildCollection[Content, Long](collection = MongoClient()("enjoycity")("content"),
    parentIdField = "idLocation") {}

}
