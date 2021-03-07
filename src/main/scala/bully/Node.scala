package bully

import scala.util.Random

/**
  * Status transition is Election -> Reorganization -> Normal
  * Election -> Reorganization during election procedure
  * Reorganization -> Normal after code is distributed to the follower
  * When problem raises, status is reset to Election (and the election procedure gets restarted)
  */
sealed trait Status
case object Election extends Status
case object Reorganization extends Status
case object Normal extends Status

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
    status: Option[Status] = None,
    f: I => O = (in: I) => { println("start function ..."); ().asInstanceOf[O] }
)
