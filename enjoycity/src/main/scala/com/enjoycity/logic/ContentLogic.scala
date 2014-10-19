package com.enjoycity.logic

import com.enjoycity.dao.{CityDAO, TagDAO, LocationDAO}
import com.enjoycity.domain.{Coordinates, APIContent}
import com.enjoycity.util.CoordinatesUtils
import com.mongodb.casbah.commons.MongoDBObject

object ContentLogic {

  def getContent(id: Long, withPhotos: Boolean): APIContent = {
    val content = LocationDAO.children.findOneById(id).get
    val location = LocationDAO.findOneById(content.idLocation).get

    val tagIds: List[Long] = content.tags ++ location.tags
    val tags = tagIds map { id =>
      TagDAO.findOneById(id).get.value
    }

    withPhotos match {
      case true => {content.photo match {
        case Some(photo) => APIContent(location.name, location.description, content.description, location.coordinates, tags, location.email, location.phoneNumber, location.rating, Some(photo))
        case None => APIContent(location.name, location.description, content.description, location.coordinates, tags, location.email, location.phoneNumber, location.rating, location.photo)
      }}
      case false => APIContent(location.name, location.description, content.description, location.coordinates, tags, location.email, location.phoneNumber, location.rating, None)
    }
  }

  def getFilteredContents(categoryId: Long, tagId: Option[Long], range: Int, lat: Option[Double], lng: Option[Double], cityId: Option[Long]): List[APIContent] = {
    val locations = LocationDAO.find(ref = MongoDBObject("categoryId" -> categoryId))

    val coordinates: Option[Coordinates] = (lat, lng) match  {
      case (Some(l1), Some(l2)) => Some(Coordinates(l1, l2))
      case _ => {
        cityId match {
          case Some(id) => Some(CityDAO.findOneById(id).get.coordinates)
          case _ => None
        }
      }
    }

    val apiContents: List[APIContent] = (for {
      location <- locations
      contents = LocationDAO.children.findByParentId(location.id)
      content <- contents
      tagIds: List[Long] = content.tags ++ location.tags
      tags = tagIds map { id =>
        TagDAO.findOneById(id).get.value
      }
      photo = content.photo match {
        case Some(contentPhoto) => Some(contentPhoto)
        case None => location.photo
      }
    } yield APIContent(location.name, location.description, content.description, location.coordinates, tags, location.email, location.phoneNumber, location.rating, photo)).toList

    val apiContentsFilteredByTags = tagId match {
      case Some(id) =>
        val tag = TagDAO.findOneById(id).get
        apiContents.filter(content => content.tags.contains(tag.value))
      case None => apiContents
    }

    coordinates match {
      case Some(coord) => {
        apiContentsFilteredByTags.filter(content => CoordinatesUtils.getDistanceInMeters(content.coordinates, coord) <= range) sortBy (element => CoordinatesUtils.getDistanceInMeters(element.coordinates, coord))
      }
      case None => apiContentsFilteredByTags
    }
  }

}
