package bully

import scala.util.Random
import zio._

sealed trait Message
case object Halt extends Message

object Node {

  trait NodeP {
    def receive(m: Message): IO[Exception, Unit]

    def create[I, O](id: Int = getId) = {
      def process(m: Message) =
        for {
          _ <- receive(m)
        } yield ()
      for {
        queue <- Queue.bounded[Message](requestedCapacity = 32)
        _ <- (for {
            m <- queue.take
            _ <- process(m)
          } yield ()).forever.fork
      } yield new Node(id = Option(id), queue = queue)
    }
  }

  def defaultOps[I, O] =
    (in: I) => {
      println("Default operation ...")
      ().asInstanceOf[O]
    }

  /**
    * Id value is between 1 and N
    * @return
    */
  def getId: Int = Random.nextInt() & Int.MaxValue

}
case class Node[I, O](
    id: Option[Int] = None,
    role: Option[Role] = None,
    status: Status = Down,
    queue: Queue[Message],
    f: I => O = Node.defaultOps[I, O]
) {

  def !(message: Message): UIO[Message] =
    for {
      _ <- queue.offer(message)
    } yield message

}
