package com.insight.io

trait IORead {
  def read(fileName: String): Iterator[String]
}
