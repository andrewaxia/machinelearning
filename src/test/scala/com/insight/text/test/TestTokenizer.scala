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
    val tokens=tokenizer.tokenize("国务院日前发出紧急通知，要求各地切实落实保证市场供应的各项政策，维护副食品价格稳定。必须重视国务院的维护价格稳定的通知。")
    println(tokens)
    val enTokens=tokenizer.tokenize("Rebecca is pretty girl, and she is smart")
    println(enTokens)
  }
}
