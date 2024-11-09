package v3

import Order.*

object Orders:

  given Order[Int] with
    override def compare(l: Int, r: Int): Int = l - r

  given Order[String] with
    override def compare(l: String, r: String): Int = l.compareTo(r)

  given [A, B](using oa: Order[A], ob: Order[B]): Order[(A, B)] with
    override def compare(l: (A, B), r: (A, B)): Int = (l, r) match
      case ((la, lb), (ra, rb)) =>
        val asComparison = oa.compare(la, ra)
        if asComparison == 0 then ob.compare(lb, rb)
        else asComparison
