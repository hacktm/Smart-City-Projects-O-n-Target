package com.enjoycity.domain

import com.novus.salat.annotations._
import org.joda.time.DateTime

case class Coordinates(
  lat: Double,
  lng: Double
)

case class City(
  @Key("_id") id: Long,
  name: String,
  coordinates: Coordinates
)

case class Tag(
  @Key("_id") id: Long,
  value: String
)

case class Category(
  @Key("_id") id: Long,
  name: String,
  photo: Array[Byte]
)

case class Location(
  @Key("_id") id: Long,
  name: String,
  description: String,
  categoryId: Long,
  tags: List[Long],
  coordinates: Coordinates,
  email: String,
  phoneNumber: String,
  rating: Option[Double] = None,
  photo: Option[Array[Byte]] = None
)

case class Content(
  @Key("_id") id: Long,
  idLocation: Long,
  description: String,
  coordinates: Option[Coordinates],
  tags: List[Long],
  expirationDate: Option[DateTime],
  photo: Option[Array[Byte]] = None
)