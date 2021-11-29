package com.insight.ml.test

import org.scalatest._
import com.insight.ml._
import breeze.linalg.DenseVector
import breeze.linalg.DenseMatrix
import breeze.linalg.argmax
import breeze.linalg.max
import breeze.numerics._

import scala.io.Source

class TestHmm extends FlatSpec with Matchers {

  it should "behave correctly for Hmm class for densevector" in {
    val states = DenseVector[Int](1, 2, 3, 4, 5, 10, 6, 6)
    println(argmax(states))
    assert(argmax(states) == 5)
    println(max(states))

  }
//   obs = ("normal", "cold", "dizzy")
// states = ("Healthy", "Fever")
// start_p = {"Healthy": 0.6, "Fever": 0.4}
// trans_p = {
//     "Healthy": {"Healthy": 0.7, "Fever": 0.3},
//     "Fever": {"Healthy": 0.4, "Fever": 0.6},
// }
// emit_p = {
//     "Healthy": {"normal": 0.5, "cold": 0.4, "dizzy": 0.1},
//     "Fever": {"normal": 0.1, "cold": 0.3, "dizzy": 0.6},
// }
//expected output:healthy,healthy,fever
  it should "infer the hidden states correclty with Hmm Algorithm" in {
    val states =
      Vector[State](State("healthy"), State("fever"))
    val allObservables = Vector[Observable](
      Observable("normal"),
      Observable("cold"),
      Observable("dizzy")
    )
    val observables = allObservables;
    val transitionMat = DenseMatrix((0.7, 0.3), (0.4, 0.6))
    val emmisionMat = DenseMatrix((0.5, 0.4, 0.1), (0.1, 0.3, 0.6))
    val initV = Vector(0.6, 0.4)
    val hmm = new Hmm(states, initV, transitionMat, emmisionMat, allObservables)
    val inferedValues = hmm.infer(observables)
    assert(inferedValues(0).name == "healthy")
    assert(inferedValues(1)==State("healthy"))
    assert(inferedValues(2).name == "fever")
    println(observables)
    println(inferedValues)
  }
}
