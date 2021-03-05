package bully

sealed trait Status

/**
  * The period will be lasted from the instant the node fails to that when an election begins.
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
