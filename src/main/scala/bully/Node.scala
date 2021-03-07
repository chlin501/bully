package bully

import scala.util.Random

sealed trait Message
case object Halt extends Message

object Node {

  /**
    * Id value is between 1 and N
    * @return
    */
  def getId: Int = Random.nextInt() & Int.MaxValue

  def create[I, O](id: Int = getId): Node[I, O] = Node[I, O](id = Option(id))
}
case class Node[I, O](
    id: Option[Int] = None,
    role: Option[Role] = None,
    status: Status = Down,
    f: I => O = (in: I) => { println("start function ..."); ().asInstanceOf[O] }
)
