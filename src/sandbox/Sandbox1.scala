package sandbox

class Sandbox1 {
  abstract sealed class Animal
  case class Feline extends Animal
  case class Canine extends Animal
  case class Cat extends Feline
  case class Dog extends Canine
  
  val f1 = { x: Feline => new Canine }
  
  // control case
  val f2: (Feline => Canine) = f1
  
  // supertype of function parameter
  val f3: (Cat => Canine) = f1
  
  // subtype of function parameter (won't compile)
  // val f4: (Animal => Canine) = f1
  
  // subtype of return type (won't compile)
  //val f5: (Feline => Dog) = f1
  
  // supertype of return type 
  val f6: (Feline => Animal) = f1
  
}