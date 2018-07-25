package com.insight.io

class CSVReader extends IORead {
  override def read(fileName: String): Iterator[String] = {
    scala.io.Source.fromFile(fileName).getLines()
  }
}
