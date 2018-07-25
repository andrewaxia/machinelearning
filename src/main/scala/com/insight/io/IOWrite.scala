package com.insight.io

trait IOWrite {
  def write(dataSet: List[Vector[Double]], fileName: String): Unit
}
