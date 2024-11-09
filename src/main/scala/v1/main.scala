package v1

@main def main =
  assert(order(List(4, 1, 3, 5, 2)) == List(1, 2, 3, 4, 5))
  assert(List(4, 1, 3, 5, 2).ordered == List(1, 2, 3, 4, 5))
