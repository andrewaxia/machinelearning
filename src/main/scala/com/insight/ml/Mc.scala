package com.insight.ml

import breeze.linalg._
import breeze.numerics._

class Mc(states: Vector[State], transition: DenseMatrix[Double]) {

  private def randomWork(): Map[State, Double] = {
    Map()
  }
  def getStationaryProb(): Map[State, Double] = {
    Map()
  }
  //s0->s1->s2...->sn   sn=p^n*s0
  def infer(init: DenseVector[Double], steps: Int): Map[State, Double] = {
    Map()
  }
  def mcmc():Unit={
    
  }

}
