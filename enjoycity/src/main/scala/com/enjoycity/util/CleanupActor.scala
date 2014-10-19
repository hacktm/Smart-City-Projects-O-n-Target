package com.enjoycity.util

import akka.actor.Actor
import com.enjoycity.dao.LocationDAO
import com.enjoycity.domain.{Content, CleanupMessage}
import com.mongodb.casbah.commons.MongoDBObject
import com.typesafe.scalalogging.LazyLogging
import org.joda.time.DateTime
import scala.concurrent.duration._

class CleanupActor extends Actor with LazyLogging {
  import context._

  override def receive: Receive = {
    case CleanupMessage => {
      logger.info("Start cleanup")
      val contents: List[Content] = LocationDAO.children.find(ref = MongoDBObject("_id" -> MongoDBObject("$gte" -> 0))).toList

      contents foreach(content => {
        content.expirationDate match {
          case Some(date) => {
            if (date.isBefore(DateTime.now())) {
              logger.info("Removing content: " + content.description)
              LocationDAO.children.remove(content)
            }
          }
          case None =>;
        }
      })

      context.system.scheduler.scheduleOnce(1 hour, self, CleanupMessage)
    }
    case _ => {
      context.system.scheduler.scheduleOnce(1 hour, self, CleanupMessage)
      logger.info("Cleanup actor received wrong message")
    }
  }
}
