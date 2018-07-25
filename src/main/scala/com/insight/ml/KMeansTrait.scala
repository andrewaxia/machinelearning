package com.insight.ml

trait KMeansTrait {
  this: Distance =>
  val k:Int
  val iterMaxTimes:Int
  def run(dataSet: List[Vector[Double]]): List[Cluster[Double]]
}
