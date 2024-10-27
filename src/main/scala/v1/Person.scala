package v1

case class Person(name: String, age: Int) extends Orderable[Person]:
  override def compare(other: Person): Int = 
    this.age - other.age
