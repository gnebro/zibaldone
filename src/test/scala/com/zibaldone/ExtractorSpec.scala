package com.zibaldone


import org.scalatest.{Matchers, WordSpec}

class ExtractorSpec extends WordSpec with Matchers {

  "CreditCardExtractor" should {
    "match a 1111-2222-3333-4444 credit card format" in {
      val isAMatch = "1111-2222-3333-4444" match {
        case CreditCardExtractor(p1, p2, p3, p4) if(p1 == "1111" & p2 == "2222" & p3 == "3333" & p4 == "4444" ) => true
        case _ => false
      }
      isAMatch shouldBe true
    }

    "NOT match 1111-2222-3a33-4444 (not numenric)" in {
      val isAMatch = "1111-2222-3a33-4444" match {
        case CreditCardExtractor(_, _, _, _) => true
        case _ => false
      }
      isAMatch shouldBe false
    }

    "NOT match 111-2222-3333-4444 (to short)" in {
      val isAMatch = "111-2222-3333-4444" match {
        case CreditCardExtractor(_, _, _, _) => true
        case _ => false
      }
      isAMatch shouldBe false
    }

    "NOT match 1111-2222-3333- (to short)" in {
      val isAMatch = "1111-2222-3333-" match {
        case CreditCardExtractor(_, _, _, _) => true
        case _ => false
      }
      isAMatch shouldBe false
    }

    "NOT match weuiyriueywruriy (rubish)" in {
      val isAMatch = "weuiyriue    ywruriy" match {
        case CreditCardExtractor(_, _, _, _) => true
        case _ => false
      }
      isAMatch shouldBe false
    }

    "NOT match an empty string" in {
      val isAMatch = "" match {
        case CreditCardExtractor(_, _, _, _) => true
        case _ => false
      }
      isAMatch shouldBe false
    }

    "Compose because extends a function" in {

    }
  }

}
