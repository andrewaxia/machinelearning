package com.insight.ml

trait Hmmlike {
  def infer(observables: Vector[Observable]): Vector[State]
}
