package v1

def order(ns: List[Int]): List[Int] = ns match
  case Nil => Nil
  case head :: tail =>
    val (smaller, larger) = tail.partition(_ < head)
    order(smaller) ++ List(head) ++ order(larger)

extension (ns: List[Int])
  def ordered: List[Int] = order(ns)