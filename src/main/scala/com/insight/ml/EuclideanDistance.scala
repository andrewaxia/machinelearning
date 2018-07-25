package com.insight.ml

trait  EuclideanDistance extends Distance {
  override def distance(x: Vector[Double], y: Vector[Double]): Double = {
    val dist = Math.sqrt((x, y).zipped.foldLeft(0.0)((s, t) => {
      val d = t._1 - t._2;
      s + d * d
    }))
    dist
  }
}
