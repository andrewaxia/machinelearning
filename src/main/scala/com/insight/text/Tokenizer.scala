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
import scala.io.Source.fromURL
import org.apache.logging.log4j.scala.Logging
import org.apache.logging.log4j.Level

class Tokenizer extends TokenizerLike with Logging{

  override def tokenize(str: String): List[String] = {
    val tokens = nplTokenize(str)
    tokens
  }

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
  private def nplTokenize(str: String): List[String] = {

    val pipeline = Tokenizer.getMePipeline()
    val document = new CoreDocument(str)
    pipeline.annotate(document)
    //  for (CoreLabel token : firstSentenceTokens) {
    //   System.out.println(token.word() + "\t" + token.beginPosition() + "\t" + token.endPosition());
    // }
    // val firstSentenceTokens = document.sentences().get(0).tokens()
    val rawTokens = document.sentences() flatMap (sentence => {
      sentence.tokens()
    })
    // logger.info(rawTokens)
    println(rawTokens)
    val tokens =
      rawTokens.map(node => node.get(classOf[CoreAnnotations.TextAnnotation]))
    tokens.toList
  }
}
object Tokenizer {

  private var nlpInstance: Option[StanfordCoreNLP] = Option.empty
  private def getNlpProerties(): Properties = {
    var props = new Properties()
    // props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner, parse, depparse,coref")
    // props.setProperty("tokenize.language","zh")
    val reader =
      fromURL(getClass.getResource("/nlplanguage.properties")).bufferedReader()
    props.load(reader)
    props
  }
  private def getMePipeline(): StanfordCoreNLP = new StanfordCoreNLP(
    getNlpProerties()
  )

}
