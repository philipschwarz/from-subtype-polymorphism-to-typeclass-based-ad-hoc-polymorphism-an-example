package v2

trait Order[A]:
  def compare(l: A, r: A): Int

object Order:

  def apply[A](using orderable: Order[A]): Order[A] = orderable

  extension [A](l: A)(using order: Order[A])
    def <(r: A): Boolean = order.compare(l, r) < 0

  extension [A](order: Order[A])
    def reverse: Order[A] = (l: A, r: A) => order.compare(r, l)

  extension [B](order: Order[B])
    def on[A](f: A => B): Order[A] = (l: A, r: A) => order.compare(f(l), f(r))
