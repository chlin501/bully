package asynchronousbully

sealed trait Message

/**
  * Election message: Sent to announce election.
  * @param priority
  */
case class Elect(priority: Int) extends Message

/**
  * Answer (Alive) message: Responds to the Election message.
  */
case object Answer extends Message

/**
  * Coordinator (Victory) Message: Sent by winner of the election to announce victory.
  */
case object Coordinator extends Message
