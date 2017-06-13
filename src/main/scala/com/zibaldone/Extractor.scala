package com.zibaldone

import scala.collection.JavaConversions._

/**
  * This is an extractor for credit card assumed to have the following format:
  * 1111-2222-3333-4444
  */
object CreditCardExtractor {

  val Separator = "-"

  def apply(part1: String, part2: String, part3: String, part4: String): String = {
    part1+Separator+part2+Separator+part3+Separator+part4
  }

 def unapply(card: String): Option[(String, String, String, String)] = {
   val parts = card.split(Separator)
   if(parts.length == 4
     && parts.forall(p => p.length == 4 && p.forall(_.isDigit))){
     Some((parts(0), parts(1), parts(2), parts(3)))
   } else None
 }
}

/**
  * positive number extractor.
  * An expractor is just a function called unapply from a type A to an Option[B]
  * No relationship between A and B
  */
object PositiveNumberExtractor {

  def unapply(i: Int): Option[String] = {
    if(i < 0) None
    else Some("Positive")
  }

}

/**
  * This simple extractor, extracts the two parts of an email, the one one
  * the left of '@' (user) and the one on the right (domain)
  */
object EmailExtractor {

  def unapply(email: String): Option[(String, String)] = {
    email.split("@").toList match {
      case seq @ user :: domain :: Nil => Some((user, domain))
      case _ => None
    }
  }
}

/**
  * This extractor, extract the various part of the domain,
  * assuming they are all separated by '.'  and uses unapplySeq
  * returning an Option[List[_]]
  */
object DomainExtractor {

  def unapplySeq(domain: String): Option[List[String]] = {

    domain.split("""\.""").toList match {
      case a @ h :: t if(a.size > 1) => Some(a)
      case a => None
    }
  }
}
