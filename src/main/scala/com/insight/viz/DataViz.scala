package com.insight.viz

import breeze.plot._

class DataViz(val xLabel: String, val yLabel: String, val vectorX: Vector[Double], val vectorY: Vector[Double]) {
  def drawLine(): Unit = {
    val figure = Figure();
    val p = figure.subplot(0);
    p += plot(this.vectorX, this.vectorY)
    p.xlabel = this.xLabel
    p.ylabel = this.yLabel
    figure.saveas("test.png")
    // scala.io.StdIn.readLine()
  }
  def drawPoint():Unit={
    val figure = Figure();
    val p = figure.subplot(0);
    p += plot(this.vectorX, this.vectorY,'.')
    p.xlabel = this.xLabel
    p.ylabel = this.yLabel
    figure.saveas("test.png")
    // scala.io.StdIn.readLine()
  }
  def drawHist():Unit={

  }
}
