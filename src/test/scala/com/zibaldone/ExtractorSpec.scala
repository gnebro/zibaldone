package com.zibaldone


import org.scalatest.{Matchers, WordSpec}

class ExtractorSpec extends WordSpec with Matchers {

  import ExtractorSpecFixture._

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
  }

  "PositiveNumberExtractor" should {
    "match a positive number" in {
      val isAMatch = 10 match {
        case PositiveNumberExtractor(a) if (a == "Positive") => true
        case _ => false
      }
      isAMatch shouldBe true
    }

    "NOT match a negative number" in {
      val isAMatch = -1 match {
        case PositiveNumberExtractor(_) => true
        case _ => false
      }
      isAMatch shouldBe false
    }
  }

  "EmailExctractor" should {
    "match an email like" in {
      val isAMatch = "user@domain" match {
        case EmailExtractor(a) if(a._1 == "user" && a._2 == "domain") => true
        case _ => false
      }
      isAMatch shouldBe true
    }

    "NOT match non email" in {
      val isAMatch = "userdomain" match {
        case EmailExtractor(a) => true
        case _ => false
      }
      isAMatch shouldBe false
    }

    "NOT match an invalid email" in {
      val isAMatch = "user@d@omain" match {
        case EmailExtractor(a) => true
        case _ => false
      }
      isAMatch shouldBe false
    }
  }

  "DomainExctractor" should {
    "match a domain like" in {
      val isAMatch = "amazon.co.uk" match {
        case DomainExtractor(a @ _*) if(a == List("amazon", "co", "uk")) => true
        case _ => false
      }
      isAMatch shouldBe true
    }

    "NOT match non domain" in {
      val isAMatch = "domain" match {
        case DomainExtractor(_*) => true
        case _ => false
      }
      isAMatch shouldBe false
    }
  }

  "EmailExtractor and DomainExctractor" should {
    "play together in pattern matching" in {
      val isAMatch = "Fidel.LaBarba@my.domain.co.uk" match {
        case EmailExtractor(user, DomainExtractor(a @ _*)) if(user == "Fidel.LaBarba" && a == List("my", "domain", "co", "uk")) => true
        case _ => false
      }
      isAMatch shouldBe true
    }

    "be used to filter a list of emails and find a user in similar domain" in {
      val filtered = EmailList.filter {
        case EmailExtractor("Fidel.LaBarba", DomainExtractor("my", _*)) => true
        case _ => false
      }
      filtered shouldBe "Fidel.LaBarba@my.domain.co.uk" :: "Fidel.LaBarba@my.domain.com" :: Nil
    }
  }


}

object ExtractorSpecFixture {
  val EmailList: List[String] = "Fidel.LaBarba@my.domain.co.uk" :: "Harry.Greb@my.domain.co.uk" :: "Fidel.LaBarba@me.domain.co.uk" :: "Fidel.LaBarba@mm.domain.co.uk" ::
    "Fidel.LaBarba@my.domain.com" :: Nil
}
