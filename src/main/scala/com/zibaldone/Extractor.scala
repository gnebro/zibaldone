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
  * length extractor
  */
object LengthExtractor {

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
