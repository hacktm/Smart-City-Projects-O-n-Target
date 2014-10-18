package com.enjoycity.domain

case class APICategory(
  id: Long,
  name: String,
  photo: Option[Array[Byte]]
)
