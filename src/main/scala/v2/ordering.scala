package v2

def order[A <: Orderable[A]](as: List[A]): List[A] = as match
  case Nil => Nil
  case head :: tail =>
    val (smaller, larger) = tail.partition(_ < head)
    order(smaller) ++ List(head) ++ order(larger)

extension [A <: Orderable[A]](as: List[A])
  def ordered: List[A] = order(as)
