package bully

sealed trait Message
case class Elect(priority: Int) extends Message
