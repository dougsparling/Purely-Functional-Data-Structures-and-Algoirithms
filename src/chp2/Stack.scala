package chp2

import scala.None

trait Stack[+T] {
	def isEmpty: Boolean
	def cons[Q >: T](a: Q): Stack[Q]
	def ::[T](a: T) = cons(a)
	def head: Option[T]
	def tail: Option[Stack[T]]
	def ++[Q >: T](s: Stack[Q]): Stack[Q]
	def suffixes: Stack[Stack[T]]
}

object Stack {
  	def empty = EmptyStack
}

final case object EmptyStack extends Stack[Nothing] { // [Nothing] ?
	def isEmpty = true
	def cons[T](a: T): TupleStack[T] = TupleStack[T](a, EmptyStack)
	def ++[Q](s: Stack[Q]) = s
	val head = None
	val tail = None
	val suffixes = this
}

final case class TupleStack[+T](val a: T, val rest: Stack[T]) extends Stack[T] {
	def isEmpty = false
	def cons[Q >: T](a: Q) = TupleStack(a, this)
	def head = Some(a)
	def tail = this match {
		case TupleStack(a, s @ TupleStack(_, _)) => Some(s)
		case TupleStack(a, EmptyStack) => None
	}
	def ++[Q >: T](s: Stack[Q]) = rest match {
	  case moreStuff @ TupleStack(_, _) => (rest ++ s) cons a 
	  case EmptyStack => TupleStack(a, s)
	}
	def suffixes = rest.suffixes cons this
}