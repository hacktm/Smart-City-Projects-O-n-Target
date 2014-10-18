package com.enjoycity.dao

import com.enjoycity.domain.Tag
import com.mongodb.casbah.MongoClient
import com.novus.salat.global._
import com.novus.salat.dao._

object TagDAO extends SalatDAO[Tag, Long](collection = MongoClient()("enjoycity")("tag"))