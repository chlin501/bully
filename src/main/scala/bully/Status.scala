package bully

/**
  * Status transition is Down -> Election -> Reorganization -> Normal
  * Election -> Reorganization during election procedure
  * Reorganization -> Normal after code is distributed to the follower
  * When a node fails and recovers
  */
sealed trait Status

/**
  * The period will be lasted from the instant the node fails to that when an election begins.
  * As soon as node *i* recovers from the failure, node *i* is reset to a fixed state where S(i).s equals "Down."
  */
case object Down extends Status

/**
  * When a node is participating in an election.
  */
case object Election extends Status

/**
  * The node participating in reorganization.
  * A Node in reorganization state knows the id of coordinator, but do not know the task to perform, but do not know the task to perform.
  */
case object Reorganization extends Status

/**
  * A node works on application task
  */
case object Normal extends Status
