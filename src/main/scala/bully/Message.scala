package bully

sealed trait Message
case object Halt extends Message
