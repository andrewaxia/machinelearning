package com.insight.ml

import breeze.linalg.DenseMatrix
import breeze.linalg.DenseVector
import breeze.linalg.Matrix
import breeze.linalg.argmax
import breeze.numerics._

case class State(name: String)
case class Observable(name: String)

class Hmm(
    val states: Vector[State],
    val initV: Vector[Double],
    val transition: DenseMatrix[Double],
    val emission: DenseMatrix[Double],
    val allObservables:Vector[Observable]
) extends Hmmlike {
  // https://en.wikipedia.org/wiki/Viterbi_algorithm#Example？
  private def viterbi(
      observables: Vector[Observable],
  ): Vector[State] = {
    val trellis = DenseMatrix.zeros[Double](states.length, observables.length)
    val pointers=Matrix.zeros[Int](states.length,observables.length)
    for (s <- 0 to states.length - 1) {
      trellis(s, 0) = initV(s) * emission(s,allObservables.indexOf(observables(0)))
    }
    for (obs <- 1 to observables.length - 1) {
      for (s <- 0 to states.length - 1) {
        val probs =for (k <- 0 to states.length - 1) yield trellis(k, obs - 1) * transition(k, s) * emission(s, allObservables.indexOf(observables(obs)));
        val probVec = DenseVector[Double](probs.toArray)
        val mindex = argmax(probVec)
        trellis(s, obs) =trellis(mindex, obs - 1) * transition(mindex, s) * emission(s, allObservables.indexOf(observables(obs)))
        pointers(s, obs)= mindex 
      }
    }
    var bestPath: Vector[State] = Vector()
    var k=argmax(trellis(::, observables.length - 1 ))
    for (obs <- observables.length - 1 to 0 by -1) {
        bestPath =states(k)+:bestPath
        k=pointers(k,obs)
    }
    bestPath
  }
  private def argMaxx(vec:Vector[Double]):Int={
     vec.zipWithIndex.maxBy(_._1)._2
  }
  override def infer(
      observables: Vector[Observable]
  ): Vector[State] = viterbi(observables)

}
