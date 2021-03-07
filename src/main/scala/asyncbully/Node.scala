package asyncbully

import scala.util.Random

sealed trait Status

/**
  * Normal mode of operation, *ldr* is significant
  */
case object Norm extends Status

/**
  * When node*<sub>i<sub>* detects failure of its leader. It sets its *status* to Elec, denoting its in the stage 1 of
  * organizing election.
  */
case object Elec1 extends Status

/**
  * If none of lesser(i) are operational, node *i* set its status to Elec2, indicating it's in the stage 2 of
  * organizing election.
  */
case object Elec2 extends Status

case object Wait extends Status

/**
  * For the node *i* at the stage 2 to send to the node in great(*i*)
  */
sealed trait Message
case object Halt extends Message

/**
  * The node that receives Halt message replies Ack (and set its status to Wait)
  */
case object Ack extends Message

/**
  * A node object
  * @param id of the node in int (id is equivalent to priority); the lower value is, the priority is higher
  * @param leader is the *ldr* in the paper
  * @param status is either *Norm* or others;
  *               when the status is normal, *ldr* is significant
  *               when the status is others, it denotes a new leader is being elected
  */
object Node {

  def getId = Random.nextInt & Int.MaxValue

  def create(id: Int = getId): Node = Node(id = id)

}
case class Node(
    id: Int,
    leader: Option[Node] = None,
    status: Status = Norm
) {

  /**
    * The lower value, the higher priority.
    * @return id value in positive
    */
  def priority: Int = id
}
