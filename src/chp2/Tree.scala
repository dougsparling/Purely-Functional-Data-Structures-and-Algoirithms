package chp2

import scala.math.Ordered

sealed abstract case class Tree[A] extends Set[A] {
  	def insert(x: A): Tree[A]
	def member(x: A): Boolean
}

case class ElementTree[A](left: Tree[A], element: A, right: Tree[A])(implicit ordering: Ordering[A]) extends Tree[A] {
	def insert(x: A): Tree[A] = x match {
		case y if ordering.lt(y, element) => ElementTree[A](left.insert(y), y, right)(ordering)
		case y if ordering.gt(y, element) => ElementTree[A](left, element, right.insert(y))
		case _ => this
	}
	
	def member(x: A): Boolean = x match {
	  case y if ordering.lt(y, element) => left.member(y)
	  case y if ordering.gt(y, element) => right.member(x)
	  case _ => true
	}
}

case class EmptyTree[A]()(implicit ordering: Ordering[A]) extends Tree[A] {
  	def insert(x: A) = ElementTree[A](EmptyTree[A], x, new EmptyTree[A])(ordering)
	def member(x: A) = false
}

object Tree {
  
}

