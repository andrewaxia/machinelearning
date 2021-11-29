package com.insight.data.test

import com.insight.viz._
import org.scalatest.{FlatSpec, Matchers}

class TestDataViz extends FlatSpec with Matchers {
  it should "draw 2d image correctly " in {
    val dataViz=new DataViz("xAxis",yLabel = "yAxis",Vector[Double](1,2,3,4),Vector[Double](1,3,2,4))
    // dataViz.drawLine()
  }

}
