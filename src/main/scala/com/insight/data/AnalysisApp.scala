package com.insight.data

import com.insight.io.DataLoader
import com.insight.model.WheatFeature

object AnalysisApp extends App {
  val fileName = "/Users/andrewxia/bigdata/seeds/seeds_dataset.txt";

  def isEmpty(x: String): Boolean = x.isEmpty

  def analyzeData = {
    val dataLoader = new DataLoader()
    val lines = dataLoader.getData(fileName)
    //val mat:Array[Vector[Double]]
    lazy val data = lines.map(l => {
      val fields: Array[String] = l.split("\\s+")
      val feature: WheatFeature = WheatFeature(fields(0).toDouble, fields(1).toDouble, fields(2).toDouble, fields(3).toDouble, fields(4).toDouble, fields(5).toDouble, fields(6).toDouble, fields(7).toDouble)
      Vector(feature.area, feature.perimeter, feature.compactness, feature.kernelLen, feature.kernelWidth, feature.aCoefficient, feature.grooveLen, feature.label)
    })
    data
  }

  def mainEntry: Unit = {
    val size = analyzeData.size
    val sums = analyzeData.toList.transpose.map(l => {
      l.sum
    })
    val means=sums.map(v => v / size)
    means.foreach(mean=>{println(mean)})
  }
  mainEntry
}

