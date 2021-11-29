package com.insight.ml

import java.io.InputStream
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import com.insight.text._
import spire.syntax.semiring
import scala.collection.mutable.ArrayBuffer
import scala.io.BufferedSource
import scala.util.control.Breaks._

class MarkovWriter(val foreword: Integer) {
  private var corpus = new HashMap[String, ArrayBuffer[String]]()
  def learnFrom(stream: InputStream): Unit = {
    val lines = scala.io.Source.fromInputStream(stream).mkString
    val tokenizer = new Tokenizer()
    val tokens = tokenizer.tokenize(lines).toIndexedSeq
    for (i: Int <- 0 until tokens.size - 2) {
      val token: String = tokens(i).trim()
      val next: String = tokens(i + 1).trim()
      if (corpus.contains(token)) {
        corpus(token) = corpus(token) :+ next

      } else {
        corpus(token) = new ArrayBuffer[String]()
      }
    }
    // println(corpus)

  }
  def writeSentence(beginWithWord: String, length: Integer): String = {
    val sentence = new ListBuffer[String]()
    if (corpus contains (beginWithWord)) {
      var current: String = beginWithWord
      sentence += current
      import scala.util.Random
      val random = new Random
      breakable {
        for (i: Int <- 0 until length - 1) {
          val followingwords: ArrayBuffer[String] = corpus(current)
          println(followingwords)
          val size = followingwords.size
          if (size > 0) {
            val rnd = random.nextInt(size)
            val next: String = followingwords(rnd)
            sentence += next
            current = next
          } else { break }
        }
      }
    }
    sentence.mkString

  }

}
