package com.insight.ml.test

import com.insight.io.DataLoader
import org.scalatest._
import com.insight.ml.{EuclideanDistance, _}

import scala.io.Source

class TestKMeans extends FlatSpec with Matchers with EuclideanDistance {
  it should "behave correctly for Kmeans  class" in {
    val data = Source.fromURL(this.getClass().getResource("/bezdekIris.data")).getLines()
    val dataSet = data.filter(l => {
      !l.trim().isEmpty
    }).map(l => {
      val fields = l.split(",")
      Vector[Double](fields(0).toDouble, fields(1).toDouble, fields(2).toDouble, fields(3).toDouble)
    }).foldLeft(List[Vector[Double]]())((b, v) => v :: b)
    //    dataSet.zipWithIndex.foreach(println(_))
    val kmeans = new KMeans(3, 1000)
    val clusters = kmeans.run(dataSet = dataSet);
    val allObservations = clusters.foldLeft(0)((s, c) => {
      s + c.members.size
    })
    clusters.foreach(c => {
      println()
      println("Centroid is : "+c.centroid)
      c.members.map(dataSet(_)).foreach(println(_))
    })
    allObservations should be(150) //should cluster all the obervations
  }
}
