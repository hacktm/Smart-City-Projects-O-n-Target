package com.enjoycity.util

import com.enjoycity.dao.{LocationDAO, CityDAO, CategoryDAO, TagDAO}
import com.enjoycity.domain._

object PopulateDB extends App {

  val category1 = Category(1, "Food", Array())
  val category2 = Category(2, "Art", Array())
  val category3 = Category(3, "Travel", Array())
  val category4 = Category(4, "Beauty", Array())
  val category5 = Category(5, "Cinema", Array())
  val category6 = Category(6, "Cultural", Array())
  val category7 = Category(7, "NewInCity", Array())
  val category8 = Category(8, "Party", Array())
  val category9 = Category(9, "Sport", Array())
  //val category10 = Category(10, "Wellness", Array())

  CategoryDAO.insert(category1)
  CategoryDAO.insert(category2)
  CategoryDAO.insert(category3)
  CategoryDAO.insert(category4)
  CategoryDAO.insert(category5)
  CategoryDAO.insert(category6)
  CategoryDAO.insert(category7)
  CategoryDAO.insert(category8)
  CategoryDAO.insert(category9)
  //CategoryDAO.insert(category10)

  // =========================================================

  val tag1 = Tag(1, "happy")
  val tag2 = Tag(2, "sad")
  val tag3 = Tag(3, "inlove")
  val tag4 = Tag(4, "alone")
  val tag5 = Tag(5, "excited")
  val tag6 = Tag(6, "tired")
  val tag7 = Tag(7, "party")
  val tag8 = Tag(8, "chill")
  val tag9 = Tag(9, "hungry")
  val tag10 = Tag(10, "energetic")
  val tag11 = Tag(11, "lazy")
  val tag12 = Tag(12, "angry")
  val tag13 = Tag(13, "bored")
  val tag14 = Tag(14, "flirty")
  val tag15 = Tag(15, "hopeful")
  val tag16 = Tag(16, "hot")

  TagDAO.insert(tag1)
  TagDAO.insert(tag2)
  TagDAO.insert(tag3)
  TagDAO.insert(tag4)
  TagDAO.insert(tag5)
  TagDAO.insert(tag6)
  TagDAO.insert(tag7)
  TagDAO.insert(tag8)
  TagDAO.insert(tag9)
  TagDAO.insert(tag10)
  TagDAO.insert(tag11)
  TagDAO.insert(tag12)
  TagDAO.insert(tag13)
  TagDAO.insert(tag14)
  TagDAO.insert(tag15)
  TagDAO.insert(tag16)

  // =========================================================

  val city1 = City(1, "Timisoara", Coordinates(45.7410646, 21.2165898))
  val city2 = City(2, "Oradea", Coordinates(47.0745948, 21.9374442))

  CityDAO.insert(city1)
  CityDAO.insert(city2)

  // =========================================================

  val location1 = Location(1, "KFC", "fast food", 1, List(1,9), Coordinates(45.765668, 21.228356), "kfc@ltg.com", "0752564789", Some(3.6), None)
  val location2 = Location(2, "CasaDeSole", "restaurant", 1, List(3, 8, 9), Coordinates(45.744455, 21.221474), "mc@ltg.com", "0789212365", Some(4.3), None)

  val location3 = Location(3, "Banat Village Museum", "museum", 1, List(8, 11, 13), Coordinates(45.7410646, 21.2165899), "bm@ltg.com", "0359987456", Some(4.0), None)
  val location4 = Location(4, "Muzeul satului", "muzeu", 1, List(8, 11), Coordinates(45.7410646, 21.2165899), "kfc@asd.com", "0289654123", Some(3.3), None)

  LocationDAO.insert(location1)
  LocationDAO.insert(location2)
  LocationDAO.insert(location3)
  LocationDAO.insert(location4)

  // =========================================================

  val content1 = Content(1, 1, "mall", None, Nil, None)
  val content2 = Content(2, 1, "centru", Some(Coordinates(45.752466, 21.224703)), Nil, None)

  val content3 = Content(3, 2, "meniul zilei: pizza 30 lej, supa: ...", None, Nil, None)

  val content4 = Content(4, 3, "expozitie: 1945 dupa razboi", None, List(2), None)
  val content5 = Content(5, 4, "omul din sat pana 12 noiembrie", None, Nil, None)

  LocationDAO.children.insert(content1)
  LocationDAO.children.insert(content2)

  LocationDAO.children.insert(content3)

  LocationDAO.children.insert(content4)
  LocationDAO.children.insert(content5)

}
