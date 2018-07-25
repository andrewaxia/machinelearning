package com.insight.utils

import breeze.stats.distributions._

class GaussianVariable(mean: Double, sigma: Double) {
  val gaussian = new Gaussian(mean, sigma)

  def getSingleSample: Double = {
    gaussian.draw()
  }

  def getSamples(n: Int): IndexedSeq[Double] = {
    gaussian.sample(n)
  }

}
