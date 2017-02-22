package com.zibaldone

import org.scalatest.{ Matchers, WordSpec }
import com.zibaldone.EitherExample._

class EitherExampleSpec extends WordSpec with Matchers {

  "map on a left projection of a Right either" should {
    "have no effect" in {
      val result = eitherRightInt.left.map(_ + "mapped")
      result shouldBe eitherRightInt
    }
    "but mapping normaly on the right projection" in {
      val result = eitherRightInt.right.map(_ + "mapped")
      result shouldBe Right("1mapped")
    }
  }

  "map on a right projection of a Left either" should {
    "have no effect" in {
      val result = eitherLeftString.right.map(_ + "mapped")
      result shouldBe eitherLeftString
    }
    "but mapping normaly on the right projection" in {
      val result = eitherLeftString.left.map(_ + " mapped")
      result shouldBe Left("I'm the left branch (right is an int) mapped")
    }
  }

  "for comprehension" should {
    "be right biased" in {
      val result = for {
        l <- eitherLeft.left
        r <- eitherRight.right
      } yield r + l


      println("@@@@@@@@@@@ ===> " + result)
    }

  }

}
