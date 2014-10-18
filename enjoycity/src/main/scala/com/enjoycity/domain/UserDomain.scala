package com.enjoycity.domain

import com.novus.salat.annotations._

case class History(
  @Key("_id") id: Long,
  idUser: Long
)

case class User(
  @Key("_id") id: Long,
  userName: String,
  password: String,
  email: String,
  city: Long
)