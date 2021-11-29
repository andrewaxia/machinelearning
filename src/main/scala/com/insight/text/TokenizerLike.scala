package com.insight.text

trait TokenizerLike {
  def tokenize(str: String): List[String]

}
