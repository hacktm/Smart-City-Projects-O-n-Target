package com.enjoycity.dao

import com.enjoycity.domain._
import org.scalatest.FlatSpec



class DAOTest extends FlatSpec {

  it should "add a city in the mongo db" in {
    val city = City(1, "TM", Coordinates(10.5, 40.5))

    CityDAO.insert(city)

    val cityFromMongo: City = CityDAO.findOneById(1).get
    CityDAO.remove(cityFromMongo)

    assert(cityFromMongo.name.equals("TM"))
  }

  it should "add a category in the mongo db" in {
    val category = Category(1, "Food", Array())

    CategoryDAO.insert(category)

    val categoryFromMongo = CategoryDAO.findOneById(1).get
    //CategoryDAO.remove(categoryFromMongy)

    assert(categoryFromMongo.name.equals("Food"))
  }

  it should "add a category and location in the mongo db" in {
    val location = Location(1, "KFC", "", 1, List(1), Coordinates(5, 6), "", "", None, Array())
    val content1 = Content(1, 1, "centru", List(), None)
    val content2 = Content(2, 1, "mall", List(), None)

    LocationDAO.insert(location)
    LocationDAO.children.insert(content1)
    LocationDAO.children.insert(content2)

    val contentByLocation = LocationDAO.children.findByParentId(1)
    assert(contentByLocation.toList.size == 2)

    LocationDAO.children.removeByParentId(1)
    LocationDAO.remove(location)
  }

}
