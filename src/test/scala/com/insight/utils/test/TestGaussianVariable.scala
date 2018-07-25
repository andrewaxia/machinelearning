package com.insight.utils.test
import breeze.stats.MeanAndVariance
import org.scalatest._
import com.insight.utils._
import com.insight.viz._
import breeze.stats.meanAndVariance

class TestGaussianVariable extends FlatSpec with Matchers {
 it should "calculate Gaussian variable correclty" in {
   val gVariable=new GaussianVariable(0,1)
   val xVector=gVariable.getSamples(100)
   val yVector=gVariable.getSamples(100)
   val viz=new DataViz("x",yLabel = "y",xVector.toVector,yVector.toVector)
   viz.drawPoint()
   val xMeanAndVariance=meanAndVariance(xVector)
   println(xMeanAndVariance)
 }
}
