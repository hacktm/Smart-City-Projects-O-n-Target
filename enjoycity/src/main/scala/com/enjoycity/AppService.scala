package com.enjoycity

import akka.actor.Actor
import com.enjoycity.dao.CategoryDAO
import com.enjoycity.domain.{APICategory, Category}
import com.enjoycity.logic.CategoryLogic
import com.mongodb.casbah.commons.MongoDBObject
import com.sun.xml.internal.bind.v2.model.core.ID
import spray.json.DefaultJsonProtocol
import spray.routing._
import spray.http._
import spray.httpx.SprayJsonSupport._
import spray.util._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class AppServiceActor extends Actor with AppService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}

// this trait defines our service behavior independently from the service actor
trait AppService extends HttpService {

  case class Person(name: String, firstName: String, age: Int)

  object MyJsonProtocol extends DefaultJsonProtocol {
    implicit val categoryFormat = jsonFormat3(Category)
    implicit val apiCategoryFormat = jsonFormat3(APICategory)
  }

  import MyJsonProtocol._

  val myRoute: Route =
    path("categories" / IntNumber) { orderId =>
        get {
          val category = CategoryDAO.findOneById(orderId).get
          complete(category)
        }
    } ~
    path("categories") {
      get {
        parameters('withPhotos.as[Boolean]) { withPhotos =>
          val res = withPhotos match {
            case true => CategoryLogic.getAllCategoriesWithPhotos
            case false => CategoryLogic.getAllCategoriesWithoutPhotos
          }
          complete(res)
        }
      }
    }
    path("test") {
      get {
        parameters('color, 'backgroundColor) { (color, backgroundColor) =>
          complete(s"The color is '$color' and the background is '$backgroundColor'")
        }
      }
    }
}