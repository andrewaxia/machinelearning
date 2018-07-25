package com.insight.ml.test
import org.scalatest._
import com.insight.ml._

import scala.io.Source

class TestCluster extends FlatSpec with Matchers {

  it should "behave correctly for Cluster class" in {
   val dataSource=Source.fromURL(this.getClass().getResource("/bezdekIris.data")).getLines()
    val data = dataSource.filter(l => {
      !l.trim().isEmpty
    }).map(l => {
      val fields = l.split(",")
       fields.map(f=> f match {
         case "Iris-virginica" => "2"
         case "Iris-versicolor" =>"1"
         case "Iris-setosa" => "0"
         case _ => f
       })
    })
    val dataSet = data.map(a=>a.map((_.toDouble))).map(_.toVector).toArray
    val cluster = Cluster[Double](dataSet(0))
    println(cluster.centroid)
  }
}
