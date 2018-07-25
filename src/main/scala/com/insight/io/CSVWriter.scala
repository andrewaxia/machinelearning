package com.insight.io

import java.io._

class CSVWriter(fieldSep: String) extends IOWrite {

  override def write(dataSet: List[Vector[Double]], fileName: String): Unit = {
    val printWriter = new PrintWriter(new File(fileName)) {
      val data = dataSet.map(l => {
        l.map(_.toString).mkString(sep = fieldSep)
      }).mkString(scala.sys.props("line.separator"))
      try {
        write(data)
      }
      finally {
        close()
      }
    }
  }
}
