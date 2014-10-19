package com.enjoycity

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import com.enjoycity.domain.CleanupMessage
import com.enjoycity.util.CleanupActor
import spray.can.Http

object Main extends App {
  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("enjoycity")

  // create and start our service actor
  val service = system.actorOf(Props[AppServiceActor], "main-service")
  val cleanupActor = system.actorOf(Props[CleanupActor], "cleanup-actor")
  cleanupActor ! CleanupMessage

  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ! Http.Bind(service, interface = "0.0.0.0", port = 8080)

}