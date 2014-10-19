package com.enjoycity.logic

import com.enjoycity.dao.TagDAO
import com.enjoycity.domain.Tag
import com.mongodb.casbah.commons.MongoDBObject

object TagLogic {

  def getTags: List[Tag] = {
    TagDAO.find(ref = MongoDBObject("_id" -> MongoDBObject("$gte" -> 0))).toList
  }
}
