package com.insight.ml.test

import org.scalatest._

import scala.io.Source
import com.insight.ml.MarkovWriter

class TestMarkovAuthor extends FlatSpec with Matchers {
  it should "write a sentence " in {
    val mwriter: MarkovWriter = new MarkovWriter(1)
    mwriter.learnFrom(getClass().getResourceAsStream("/reform.txt"))
    mwriter.learnFrom(getClass().getResourceAsStream("/ruralwork.txt"))
    val sentence:String=mwriter.writeSentence("加强",30)
    println(sentence)

  }

}
