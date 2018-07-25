package com.insight.data

abstract class Device {

}
case class Phone(model:String) extends Device{

}
case class Computer(model:String) extends  Device{

}
case class User(val name:String,val age:Int)

object MainEntry extends App {
  val numbersPattern="[0-9]".r
  goIdle(new Phone("ibm"))
  val users=List(new User("andrewxia",age = 12),new User(name="sohu",age=16),User(name="nnni",age=12))
  numbersPattern.findFirstMatchIn("hellsgood23,adfa") match {
    case Some(_) =>{
      println("it has nubmers")
    }
    case None =>{
      println("none")
    }
  }
   val filteredUser=for(user<-users if user.age>12) yield user

    def goIdle(device:Device): Unit ={
      device match {
        case p:Phone=>{
         println("phone model is "+p.model)
        }
        case c:Computer=>{
          println("computer model is "+c.model)
        }
      }
    }
  println(filteredUser)
}