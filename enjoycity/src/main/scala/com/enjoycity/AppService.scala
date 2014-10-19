package com.enjoycity

import akka.actor.Actor
import com.enjoycity.domain._
import com.enjoycity.logic.{TagLogic, ContentLogic, CategoryLogic}
import com.enjoycity.util.CORSSupport
import com.typesafe.scalalogging.LazyLogging
import spray.json.DefaultJsonProtocol
import spray.routing._
import spray.httpx.SprayJsonSupport._

class AppServiceActor extends Actor with AppService {

  def actorRefFactory = context

  def receive = runRoute(myRoute)
}

// this trait defines our service behavior independently from the service actor
trait AppService extends HttpService with CORSSupport with LazyLogging {

  object MyJsonProtocol extends DefaultJsonProtocol {
    implicit val coordinatesFormat = jsonFormat2(Coordinates)
    implicit val tagFormat = jsonFormat2(Tag)
    implicit val categoryFormat = jsonFormat3(Category)
    implicit val apiCategoryFormat = jsonFormat3(APICategory)
    implicit val apiContentFormat = jsonFormat9(APIContent)
  }

  import MyJsonProtocol._

  val myRoute: Route = cors {
    path("categories") {
      get {
        parameters('withPhotos.as[Boolean]) { withPhotos =>
          val res = withPhotos match {
            case true => {
              logger.info("Get categories with photos")
              CategoryLogic.getAllCategoriesWithPhotos
            }
            case false => {
              logger.info("Get categories without photos")
              CategoryLogic.getAllCategoriesWithoutPhotos
            }
          }

          complete(res)
        }
      }
    } ~
    path("tags") {
      get {
        logger.info("Get tags")
        complete(TagLogic.getTags)
      }
    } ~
    path("content" / IntNumber) { contentId =>
      get {
        logger.info("Get content with id: " + contentId)
        parameters('withPhotos.as[Boolean]) { withPhotos =>
          complete(ContentLogic.getContent(contentId, withPhotos))
        }
      }
    } ~
    path("contents" / IntNumber) { categoryId =>
      get {
        parameters('tagId.as[Option[Long]], 'range.as[Int], 'lat.as[Option[Double]], 'lng.as[Option[Double]], 'cityId.as[Option[Long]]) {
          (tagId, range, lat, lng, cityId) =>
            logger.info("Get contents from category: " + categoryId + " and filtered by tag: " + tagId)
            complete(ContentLogic.getFilteredContents(categoryId, tagId, range, lat, lng, cityId))
        }
      }
    }
  }

}