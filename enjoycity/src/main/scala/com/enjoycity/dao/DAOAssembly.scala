package com.enjoycity.dao

import com.typesafe.config.ConfigFactory

object DAOAssembly {

  val config = ConfigFactory.load()
  val mongoHost: String = config.getString("mongo.host")
  val mongoPort: Int = config.getInt("mongo.port")

}
