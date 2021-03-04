package asynchronousbully

sealed trait Status

/**
  * Normal mode of operation, *ldr* is significant
  */
case object Normal extends Status

/**
  * A new leader is being elected
  */
case object Others extends Status

/**
  * A node object
  * @param id of the node in int (id is equivalent to priority); the lower value is, the priority is higher
  * @param leader is the *ldr* in the paper
  * @param status is either *Normal* or others;
  *               when the status is normal, *ldr* is significant
  *               when the status is others, it denotes a new leader is being elected
  */
case class Node(id: Int, leader: Node, status: Status) {
  def priority: Int = id
}
