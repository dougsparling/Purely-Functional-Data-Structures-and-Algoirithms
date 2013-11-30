package chp2

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec

@RunWith(classOf[JUnitRunner])
class StackTest extends FlatSpec {
	"An empty stack" should "support chained cons" in {
		val testStack = Stack.empty
		val threeStack = testStack cons 1 cons 2 cons 3
		
		assert(threeStack.head === Some(3))
		assert(threeStack.tail.get.head === Some(2))
		assert(threeStack.tail.get.tail.get.head === Some(1))
		assert(threeStack.tail.get.tail.get.tail === None)
	}
	
	"An empty stack" should "support 1 cons" in {
		val testStack = Stack.empty
		val threeStack = testStack cons 1
		
		assert(threeStack.head === Some(1))
		assert(threeStack.tail === None)
	}
	
	"An empty stack" should "catenate with an empty stack" in {
		assert(Stack.empty ++ Stack.empty === Stack.empty)
	}
	
	"An empty stack" should "catenate with a non-empty stack" in {
		assert(Stack.empty ++ (Stack.empty cons 1 cons 2) === (Stack.empty cons 1 cons 2))
	}
	
	"A non-empty stack" should "catenate with an empty stack" in {
		assert((Stack.empty cons 1 cons 2) ++ Stack.empty === (Stack.empty cons 1 cons 2))
	}
	
	"A non-empty stack" should "catenate with another non-empty stack using cons" in {
		assert((Stack.empty cons 1 cons 2) ++ (Stack.empty cons 3 cons 4) === (Stack.empty cons 3 cons 4 cons 1 cons 2))
	}
	
	"A non-empty stack" should "catenate with another non-empty stack using ::" in {
		assert((1 :: 2 :: Stack.empty) ++ (3 :: 4 :: Stack.empty) === (1 :: 2 :: 3 :: 4 :: Stack.empty))
	}
	
	"A stack of items" should "support suffixes" in {
	  assert( (1 :: 2 :: 3 :: Stack.empty) :: (2 :: 3 :: Stack.empty) :: (3 :: Stack.empty) :: Stack.empty === (1 :: 2 :: 3 :: Stack.empty).suffixes )
	}
}