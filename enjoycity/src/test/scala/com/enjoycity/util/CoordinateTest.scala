package com.enjoycity.util

import com.enjoycity.domain.Coordinates
import org.scalatest.FlatSpec

class CoordinateTest extends FlatSpec {

  behavior of "CoordinatesUtils"

  it should "return 150-180 km as distance between Timisoara Oradea" in {
    val t = Coordinates(45.7410646, 21.2165898)
    val o = Coordinates(47.0745948, 21.9374442)

    val d = CoordinatesUtils.getDistanceInMeters(t,o)/1000
    assert(d > 150 && d < 200)
  }

}
