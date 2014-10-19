package com.enjoycity.domain

case class APICategory(
  id: Long,
  name: String,
  photo: Option[Array[Byte]]
)

case class APIContent(
  name: String,
  locationDescription: String,
  description: String,
  coordinates: Coordinates,
  tags: List[String],
  email: String,
  phoneNumber: String,
  rating: Option[Double] = None,
  photo: Option[Array[Byte]] = None
)