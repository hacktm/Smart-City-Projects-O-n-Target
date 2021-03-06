package com.enjoycity.dao

import com.enjoycity.domain.City
import com.mongodb.casbah.MongoClient
import com.novus.salat.global._
import com.novus.salat.dao._

object CityDAO extends SalatDAO[City, Long](collection =  MongoClient(DAOAssembly.mongoHost, DAOAssembly.mongoPort)("enjoycity")("city"))