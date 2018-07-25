package com.insight.ml

import com.insight.utils.Statistic

class Cluster[T <% Double](val centroid: Vector[Double]) extends ClusterTrait[Double] with EuclideanDistance {

  def calibrateCentroid(dataSet: List[Vector[T]]): Cluster[T] = {
    val sums = this.members.map(dataSet(_).map(_.toDouble)).toList.transpose.map(_.sum)
    val means = sums.map(s => s / this.members.size).toVector
    Cluster[T](means)
  }

  def stdDev(dataSet: List[Vector[Double]], distance1: Distance): Double = {
    val data = this.members.map(dataSet(_)).map(distance(this.centroid, _)).toVector
    val stats = Statistic(data)
    stats.stdDev()
  }
}

object Cluster {
  def apply[T <% Double](c: Vector[Double]): Cluster[T] = {
    new Cluster[T](c)
  }
}
