package chp2

trait Set[T] {
	def insert(x: T): Set[T]
	def member(x: T): Boolean
}