package com.zibaldone

object FoldRightExample {

  /**
    * Given a list: Seq(1,2,3)
      which represents the number 123, write a function to increment it
      by one without converting types.
    */

  def increment(s: Seq[Int]): Seq[Int] = {
    val r = s.foldRight((1, Seq.empty[Int]))((i: Int, acc: (Int,Seq[Int])) => {
      if(i + acc._1 > 9) (1, 0 +: acc._2)
      else (0, (i + acc._1) +: acc._2)
    })

    r match {
      case (_, Nil) => Nil
      case (1, acc) => 1 +: acc
      case (_, acc) => acc
    }
  }
}
