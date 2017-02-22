package com.zibaldone

import org.scalatest.{ Matchers, WordSpec }
import com.zibaldone.FunctionCompositionExample._

class FunctionCompositionExampleSpec extends WordSpec with Matchers {

  import Fixture._

  "functions" should {
    "beheave as expected" in {

      upperFirstLetter(word1) shouldBe "Marcel"

      lowerFirstLetter(word3) shouldBe "marcel"

      replaceWithSomething(word4, word2, "Cerdan") shouldBe "Marcel Cerdan won the middleweight championship"

      replaceWithSomethingCurried(word4)(word2, "Cerdan") shouldBe "Marcel Cerdan won the middleweight championship"

      replaceWithPounds(word5) shouldBe "he won 5000 Pounds at first"

      replaceWithAt(word5) shouldBe "he won 5000 £ @ first"

      count(word1, 'r') shouldBe 1

      countCurried('r')(word1) shouldBe 1

      (replaceWithAt andThen countAt)(word5) shouldBe 1

      ConcatTwoStrings(word1, word2) shouldBe "marcel cerdan"

      andThenFunction(words1) shouldBe words1Expected

      composeFunction(words1) shouldBe words1Expected

      (andThenFunction andThen concatAndCount)(words1) shouldBe 1
    }

  }

  "composition of inverse functions" should {
    "have no effect regardless of the order" in {
      val inverse1 = upperFirstLetter andThen lowerFirstLetter
      val inverse2 = lowerFirstLetter compose upperFirstLetter

      inverse1(word1) shouldBe word1
      inverse2(word1) shouldBe word1
    }
  }

  "composition examples" should {
    "have no effect regardless of the order" in {
      val composed =


    }
  }

}

object Fixture {

  val word1 = "marcel"
  val word2 = "cerdan"
  val word3 = "Marcel"
  val word4 = "Marcel cerdan won the middleweight championship"

  val word5 = "he won 5000 £ at first"

  val words1 = word1 :: word2 :: word5 :: Nil
  val words1Expected = "Marcel" :: "Cerdan" :: "He won 5000 Pounds @ first" :: Nil


}
