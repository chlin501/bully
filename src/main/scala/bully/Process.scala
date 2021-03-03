package bully

import zio._

case class Process[T](queue: Queue[T], role: Role) {}
