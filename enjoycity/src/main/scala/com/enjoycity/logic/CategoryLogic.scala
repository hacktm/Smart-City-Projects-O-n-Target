package com.enjoycity.logic

import com.enjoycity.dao.CategoryDAO
import com.enjoycity.domain.APICategory
import com.mongodb.casbah.commons.MongoDBObject

object CategoryLogic {

  def getAllCategoriesWithPhotos: List[APICategory] = {
    CategoryDAO.find(ref = MongoDBObject("_id" -> MongoDBObject("$gte" -> 0))).toList map { category =>
      APICategory(category.id, category.name, Some(category.photo))
    }
  }

  def getAllCategoriesWithoutPhotos: List[APICategory] = {
    val categories = CategoryDAO.find(ref = MongoDBObject("_id" -> MongoDBObject("$gte" -> 0))).toList

    categories map { category =>
      APICategory(category.id, category.name, None)
    }
  }

}
