package com.insight.io

class DataLoader {
  def getData(file: String): Iterator[String] = {
    val fileContent = scala.io.Source.fromFile(file)
    fileContent.getLines()
  }
}

object DataLoader {
  def apply: DataLoader = new DataLoader()
}
