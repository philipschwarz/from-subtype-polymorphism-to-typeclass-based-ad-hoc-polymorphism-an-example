package v2

import Order.*
import Orders.given

@main def main =

  //////////////////////////////////////////////////////////////////////

  val ints = List(4, 1, 3, 5, 2)
  val ascendingInts = List(1, 2, 3, 4, 5)
  
  val order: Order[Int]  = summon[Order[Int]]
  
  val ascendingIntOrder = Order[Int]

  assert(ints.ordered == ascendingInts)
  assert(ints.ordered(using ascendingIntOrder) == ascendingInts)
  assert(ints.ordered(using Order[Int]) == ascendingInts)
  assert(ints.ordered(using _ - _) == ascendingInts)

  val descendingIntOrder = ascendingIntOrder.reverse
  
  assert(ints.ordered(using descendingIntOrder) == ascendingInts.reverse)
  assert(ints.ordered(using Order[Int].reverse) == ascendingInts.reverse)
  assert(ints.ordered(using (n1, n2) => n2 - n1) == ascendingInts.reverse)

  //////////////////////////////////////////////////////////////////////

  val names = List("John", "Jane", "Jim")
  val ascendingNames = List("Jane", "Jim", "John")

  val ascendingStringOrder = Order[String]

  assert(names.ordered == ascendingNames)
  assert(names.ordered(using ascendingStringOrder) == ascendingNames)
  assert(names.ordered(using Order[String]) == ascendingNames)
  assert(names.ordered(using _ compareTo _) == ascendingNames)
  
  val descendingStringOrder = ascendingStringOrder.reverse

  assert(names.ordered(using descendingStringOrder) == ascendingNames.reverse)
  assert(names.ordered(using Order[String].reverse) == ascendingNames.reverse)
  assert(names.ordered(using (s1,s2) => s2 compareTo s1) == ascendingNames.reverse)

  //////////////////////////////////////////////////////////////////////

  val people = List(Person("Jane", 30), Person("John", 25), Person("Jim", 18))
  val peopleByAge = List(Person("Jim", 18), Person("John", 25), Person("Jane", 30))

  given ageOrder: Order[Person] = ascendingIntOrder.on(_.age)

  assert(Person("John", 25) < Person("Jane", 30))
  
  assert(people.ordered == peopleByAge)
  assert(people.ordered(using ageOrder) == peopleByAge)
  assert(people.ordered(using Order[Int].on[Person](_.age)) == peopleByAge)
  assert(people.ordered(using (p1,p2) => p1.age - p2.age) == peopleByAge)


  assert(people.ordered(using ageOrder.reverse) == peopleByAge.reverse)
  assert(people.ordered(using Order[Int].on[Person](_.age).reverse) == peopleByAge.reverse)
  assert(people.ordered(using (p1, p2) => p2.age - p1.age) == peopleByAge.reverse)
  
  //////////////////////////////////////////////////////////////////////

  val peopleByName = List(Person("Jane", 30), Person("Jim", 18), Person("John", 25))

  val lexicographicStringOrder = Order[String]
  val nameOrder: Order[Person] = lexicographicStringOrder.on(_.name)

  assert(people.ordered(using nameOrder) == peopleByName)
  assert(people.ordered(using Order[String].on(_.name)) == peopleByName)
  assert(people.ordered(using (p1, p2) => p1.name.compareTo(p2.name)) == peopleByName)

  assert(people.ordered(using nameOrder.reverse) == peopleByName.reverse)
  assert(people.ordered(using Order[String].on[Person](_.name).reverse) == peopleByName.reverse)
  assert(people.ordered(using (p1, p2) => p2.name.compareTo(p1.name)) == peopleByName.reverse)

  //////////////////////////////////////////////////////////////////////
  
  val morePeople = List(Person("John", 25), Person("Jane", 30), Person("Jack", 25), Person("Jim", 18))
  val morePeopleByAgeAndThenName = List(Person("Jim", 18), Person("Jack", 25), Person("John", 25), Person("Jane", 30))
  
  val ageAndThenNameOrder: Order[Person] = Order[(Int,String)].on(p => (p.age, p.name))

  assert(morePeople.ordered(using ageAndThenNameOrder) == morePeopleByAgeAndThenName)