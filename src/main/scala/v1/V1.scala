package v1

@main def version_1 =

  assert( Person("John", 25) < Person("Jane", 30) ) 
  
  val people = List( Person("Jane", 30), Person("John", 25), Person("Jim", 18) )
  val peopleByIncreasingAge = List( Person("Jim", 18), Person("John", 25), Person("Jane", 30) )

  assert(people.ordered == peopleByIncreasingAge)