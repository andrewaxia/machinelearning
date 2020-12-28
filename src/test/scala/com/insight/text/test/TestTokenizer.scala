package com.insight.ml.test

import com.insight.io.DataLoader
import org.scalatest._
import com.insight.text._

class TestTokenizer extends FlatSpec with Matchers {
  it should "tokenize string to words" in {
    val tokenizer = new Tokenizer(); 
    val dict=tokenizer.addToDict("this is a test"); 
    println(dict); 
    val theMap=tokenizer.tokenizeString("Hello,the world. the world is a test")
    println(theMap)
  }

  it should "tokenize string to chinese words" in {
    val tokenizer=new Tokenizer()
    val tokens=tokenizer.nplTokenize("Lucy is pretty")
    println(tokens)
  }
}
