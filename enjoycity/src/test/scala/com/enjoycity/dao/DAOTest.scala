package com.enjoycity.dao

import com.enjoycity.domain._
import org.scalatest.FlatSpec

class DAOTest extends FlatSpec {

  it should "add a city in the mongoDB" in {
    val city = City(1, "TM", Coordinates(10.5, 40.5))

    CityDAO.insert(city)

    val cityFromMongo: City = CityDAO.findOneById(1).get
    CityDAO.remove(cityFromMongo)

    assert(cityFromMongo.name.equals("TM"))
  }

  it should "add a category in the mongoDB" in {
    val category1 = Category(1, "Food", Array())

    CategoryDAO.insert(category1)

    val categoryFromMongo = CategoryDAO.findOneById(1).get
    CategoryDAO.remove(categoryFromMongo)

    assert(categoryFromMongo.name.equals("Food"))
  }

  it should "add 2 tags in the mongoDB" in {
    val tag1 = Tag(1, "happy")
    val tag2 = Tag(2, "sad")

    TagDAO.insert(tag1)

    val tagFromMongo = TagDAO.findOneById(1).get
    TagDAO.removeByIds(List(1,2))

    assert(tagFromMongo.value.equals(tag1.value))
  }

  it should "add 2 categories and location in the mongoDB" in {
    val location = Location(1, "KFC", "", 1, List(1), Coordinates(5, 6), "", "", None, Some(Array()))
    val content1 = Content(1, 1, "centru", None,  List(), None)
    val content2 = Content(2, 1, "mall",  None, List(), None)

    LocationDAO.insert(location)
    LocationDAO.children.insert(content1)
    LocationDAO.children.insert(content2)

    val contentByLocation = LocationDAO.children.findByParentId(1)
    assert(contentByLocation.toList.size == 2)

    LocationDAO.children.removeByParentId(1)
    LocationDAO.remove(location)
  }

}
