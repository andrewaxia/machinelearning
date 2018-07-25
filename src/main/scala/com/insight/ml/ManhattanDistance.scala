package com.insight.ml

trait ManhattanDistance[T] extends Distance {
  override def distance(x: Vector[Double], y: Vector[Double]): Double = {
    val dist = (x, y).zipped.foldLeft(0.0)((s, t) => s + Math.abs(t._1 - t._2))
    dist
  }
}
