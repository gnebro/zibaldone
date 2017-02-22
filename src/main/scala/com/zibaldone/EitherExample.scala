package com.zibaldone

import scala.util.Random


object EitherExample {

  val eitherRight: Either[String, String] = Right("I'm the right branch")
  val eitherLeft: Either[String, String] = Left("I'm the left branch")
  val eitherRightInt: Either[String, Int] = Right(1)
  val eitherLeftString: Either[String, Int] = Left("I'm the left branch (right is an int)")

  val listOfEither: List[Either[String, Int]] = List.fill(10)(randomEvenGen)

  def randomEvenGen: Either[String, Int] = {
    Random.nextInt(10) match {
      case even if(even % 2 == 0) => Right(even)
      case odd => Left(s"$odd is not an even number")
    }
  }


}
