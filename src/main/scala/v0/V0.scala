package v0

def order(ns: List[Int]): List[Int] = ns match
  case Nil => Nil
  case head :: tail =>
    val (smaller, larger) = tail.partition(_ < head)
    order(smaller) ++ List(head) ++ order(larger)

extension (ns: List[Int])
  def ordered: List[Int] = order(ns)

@main def version_0 =
  assert(order(List(4, 1, 3, 5, 2)) == List(1, 2, 3, 4, 5))
  assert(List(4, 1, 3, 5, 2).ordered == List(1, 2, 3, 4, 5))
