package bully

import scala.util.Random
import zio._

object Node {
  def id: Int = Random.nextInt() & Int.MaxValue
  def apply(nodeId: Int = id): Node = Node(nodeId = nodeId)
}
case class Node(nodeId: Int)
