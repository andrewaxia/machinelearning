package com.insight.utils.test

import com.insight.io.CSVWriter
import org.scalatest.{FlatSpec, Matchers}

class TestCSVWriter extends FlatSpec with Matchers {
  it should "write list to csvfile" in {
    import com.insight.utils._
    val writer = new CSVWriter(",")
    val dataSet = List(Vector(1d, 2d), Vector(3d, 4d))
    writer.write(dataSet, "test.csv")
  }

}
