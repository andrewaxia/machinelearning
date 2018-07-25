package com.insight.ml


import scala.collection.mutable.ListBuffer

trait ClusterTrait[T] {
  this: Distance =>
  val centroid: Vector[T]
  val members: ListBuffer[Int] = new ListBuffer[Int]()

  def +=(index: Int): Unit = members.append(index)

  def size: Int = members.size
}
