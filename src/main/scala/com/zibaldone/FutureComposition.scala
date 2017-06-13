package com.zibaldone

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object FutureComposition {

  def f1: Future[Unit] = ???
  def f2: Future[Unit] = ???
  def f3: Future[Unit] = ???
  def f4: Future[Unit] = ???

  // Write code to execute them when:


  // *************************************
  // there are no dependencies between the functions
  // *************************************

  // Note: the order in this case doesn't really matter and there would
  // be an option to use Future.sequence but in this case we have just 4 futures
  // so even the basic solution seems ok to me

  val f1Result: Future[Unit] = f1
  val f2Result: Future[Unit] = f2
  val f3Result: Future[Unit] = f3
  val f4Result: Future[Unit] = f4

  // *************************************
  // f4 depends on f3 which depends on f2 which depends on f1
  // *************************************

  val result1: Future[Unit] = for {
    _ <- f1
    _ <- f2
    _ <- f3
    r <- f4
  } yield r

  // *************************************
  // f4 depends on f3 and f2, and f3 and f2 both depend on f1
  // *************************************

  val result2: Future[Unit] = for {
    _ <- f1
    _ <- Future.sequence(f2 :: f3 :: Nil)
    r <- f4
  } yield r

}
