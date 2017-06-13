package com.zibaldone

object FunctionCompositionExample {

  // function
  val upperFirstLetter: String => String = (a: String) => a.headOption.fold("")(_.toUpper + a.tail)

  // method
  val lowerFirstLetter: String => String = (a: String) => a.headOption.fold("")(_.toLower + a.tail)

  // function
  val replaceWithSomething: (String, String, String) => String = (arg: String, toReplace: String, replacement: String) => {
    arg.replace(toReplace, replacement)
  }

  // function curried
  val replaceWithSomethingCurried: (String) => (String, String) => String = (arg: String) => (toReplace: String, replacement: String) => {
    replaceWithSomething(arg, toReplace, replacement)
  }

  // Pre-applied function
  val replaceWithPounds: String => String = (arg: String) => replaceWithSomethingCurried(arg)("£", "Pounds")

  // Pre-applied function
  val replaceWithAt: String => String = (arg: String) => replaceWithSomethingCurried(arg)("at", "@")

  val count: (String, Char) => Int = (arg, toCount) => arg.count(_ == toCount)

  // Currying function above
  val countCurried: (Char) => (String) => Int = (toCount) => (arg) => count(arg, toCount)

  val countAt: String => Int = (arg) => countCurried('@')(arg)

  // compose our function

  val functions: Seq[Function1[String, String]] = upperFirstLetter :: replaceWithPounds :: replaceWithAt :: Nil

  val andThenFunction: Seq[String] => Seq[String] = (arg) => {
    arg.map(functions reduce (_ andThen _))
  }

  val composeFunction: Seq[String] => Seq[String] = (arg) => {
    arg.map(functions.reverse reduce (_ compose _))
  }

  val concatAndCount: Seq[String] => Int = (seq) => {
    countAt(seq.reduce(ConcatTwoStrings))
  }

}

// Function as it extends Function2
object ConcatTwoStrings extends ((String, String) => String) {
  def apply(a: Int, b: Int) = a + b
  def apply(a: String, b: String) = a + " " + b
}

