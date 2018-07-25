package com.insight.ml

trait Distance {
  def distance(x: scala.Vector[Double], y: scala.Vector[Double]): Double
}
