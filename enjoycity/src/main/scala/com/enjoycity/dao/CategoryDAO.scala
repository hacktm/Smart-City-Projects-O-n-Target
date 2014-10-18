package com.enjoycity.dao

import com.enjoycity.domain.Category
import com.mongodb.casbah.MongoClient
import com.novus.salat.global._
import com.novus.salat.dao._

object CategoryDAO extends SalatDAO[Category, Long](collection = MongoClient()("enjoycity")("category"))
