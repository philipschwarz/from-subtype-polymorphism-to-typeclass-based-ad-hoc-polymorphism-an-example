package v1

trait Orderable[A]:
  def compare(other: A): Int

object Orderable:
  extension [A](l: Orderable[A])
    def <(r: A): Boolean = l.compare(r) < 0
