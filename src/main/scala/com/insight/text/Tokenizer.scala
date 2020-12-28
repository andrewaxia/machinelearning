package com.insight.text
import java.util.StringTokenizer
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling._;
import edu.stanford.nlp.ie.util._;
import edu.stanford.nlp.pipeline._;
import edu.stanford.nlp.semgraph._;
import edu.stanford.nlp.trees._;
import java.util.Properties
import scala.collection.JavaConversions._

class Tokenizer {
  def addToDict(line: String) = {
    line.split("\\W+").distinct.zipWithIndex.toMap
  }

  def tokenizeString(str: String): Map[String, Int] = {
    var vMap = scala.collection.mutable.Map[String, Int]()
    val tokenizer = new StringTokenizer(str)
    while (tokenizer.hasMoreTokens()) {
      val token = tokenizer.nextToken().trim()
      if (vMap.contains(token)) {
        vMap(token) = vMap(token) + 1
      } else {
        vMap += (token -> 1)
      }
    }
    vMap.toMap
  }
  def nplTokenize(str: String): List[String] = {
    var props = new Properties()
    props.setProperty("annotators", "tokenize,ssplit")
    val pipeline = new StanfordCoreNLP(props)
    val document = new CoreDocument(str)
    pipeline.annotate(document)
    //  for (CoreLabel token : firstSentenceTokens) {
    //   System.out.println(token.word() + "\t" + token.beginPosition() + "\t" + token.endPosition());
    // }
    val firstSentenceTokens = document.sentences().get(0).tokens()
    val tokens = firstSentenceTokens.toList.map(node => node.word())
    tokens
  }
}
