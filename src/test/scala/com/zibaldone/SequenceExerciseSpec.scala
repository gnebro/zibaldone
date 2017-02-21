package com.zibaldone

import com.zibaldone.FoldRightExample.increment
import org.scalatest._

class FoldRightExampleSpec extends WordSpec with Matchers {

  "The increment function" should {
    "return an empty sequence if applied to an empty sequence" in {
      val result = increment(Seq.empty[Int])
      result shouldBe Seq.empty[Int]
    }

    "return Seq(1) from Seq(0)" in {
      val result = increment(Seq(0))
      result shouldBe Seq(1)
    }

    "return Seq(1, 2, 4) from Seq(1, 2, 3)" in {
      val result = increment(Seq(1,2,3))
      result shouldBe Seq(1,2,4)
    }

    "return Seq(1, 0) from Seq(9)" in {
      val result = increment(Seq(9))
      result shouldBe Seq(1, 0)
    }

    "return Seq(1,0,0,0,0,0) from Seq(9,9,9,9,9)" in {
      val result = increment(Seq(9,9,9,9,9))
      result shouldBe Seq(1,0,0,0,0,0)
    }
  }

}
