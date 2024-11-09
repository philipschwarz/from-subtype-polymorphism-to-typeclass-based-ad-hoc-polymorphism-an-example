package v2

import Order.*

def order[A: Order](as: List[A]): List[A] = as match
  case Nil => Nil
  case head :: tail =>
    val (smaller, larger) = tail.partition(_ < head)
    order(smaller) ++ List(head) ++ order(larger)

extension [A: Order](as: List[A])
  def ordered: List[A] = order(as)    