package bully

import scala.util.Random
import zio._

object Node {

  def getId: Int = Random.nextInt()

  def apply[I, O](id: Int = getId): Node[I, O] = Node[I, O](id = id)
}
case class Node[I, O](
    id: Option[Int] = None,
    role: Option[Role] = None,
    f: I => O
)
