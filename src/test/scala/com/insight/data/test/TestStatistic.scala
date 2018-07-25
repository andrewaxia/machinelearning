package com.insight.data.test

import org.scalatest._
import breeze.linalg._
import com.insight.utils.Statistic

class TestStatistic extends FlatSpec with Matchers {

  "Cal mean " should "be correct " in {
    val denseVector = DenseVector(1, 2, 3, 4)
    val stats = new Statistic(scala.Vector[Double](1, 2, 3, 4))
    val vmean = stats.mean()
    vmean should be(2.5)
  }

  it should "correctly compute variance " in {
    val stats = new Statistic(scala.Vector(2.0, 2.0, 2.0));
    val variance = stats.variance()
    variance should be(0.0)

  }
  it should "correctly compute stdDev" in {
    val stats = new Statistic(scala.Vector(2.0, 1.0, 3.0))
    val stdDev = stats.stdDev()
  }
  it should "find min value of the vector" in {
    val stats = new Statistic(scala.Vector(2.0, 1.0, 3.0));
    val min = stats.min()
    min should be(1.0)
  }
  it should "find max value of the vector" in {
    val stats = new Statistic(scala.Vector(1.0, 2, 0, 3, 0))
    val max = stats.max()
    max should be(3.0)
  }
}
