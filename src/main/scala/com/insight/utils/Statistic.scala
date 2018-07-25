package com.insight.utils

class Statistic(values: Vector[Double]) {
  def variance(): Double = {
    lazy val valMean = this.mean();
    val vSum = this.values.map(v => {
      val difference = (v - valMean)
      difference * difference
    }).foldLeft(0.0)((a, b) => a + b)
     vSum / this.values.size;
  }
  def stdDev(): Double = {
    lazy val vSum = this.variance();
    Math.sqrt(vSum)
  }

  def mean(): Double = {
    lazy val sum = this.values.foldLeft(0d)((x,y)=>x+y)
     sum / this.values.size
  }



  def min():Double={
    lazy val vMin=this.values.min
    vMin

  }
  def max():Double={
    lazy val vMax=this.values.max
    vMax
  }
}
object Statistic{
  def apply(values: Vector[Double]):Statistic={
    new Statistic(values)
  }
}
