package bully

sealed trait Phase

/**
  * Group join phase
  */
trait GroupJoin extends Phase
case class Join(id: Int) extends GroupJoin

case object ElectionRun extends Phase
