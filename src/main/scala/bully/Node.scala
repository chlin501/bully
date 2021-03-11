package bully

import scala.util.Random
import zio._

sealed trait Message
case object Halt extends Message

object Node {

  trait StatefulNode {
    def receive(s: Status, m: Message): IO[Throwable, Unit]

    def create[I, O](
        id: Int = getId,
        status: Status = Down
    ) = {
      def process(status: Status, message: Message) =
        for {
          _ <- receive(status, message)
        } yield ()
      for {
        queue <- Queue.bounded[Message](requestedCapacity = 32)
        _ <- (for {
            message <- queue.take
            _ <- process(status, message)
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
    queue: Queue[Message],
    f: I => O = Node.defaultOps[I, O]
) {

  def !(message: Message): UIO[Message] =
    for {
      _ <- queue.offer(message)
    } yield message

  def stop: Task[List[_]] =
    for {
      allTasks <- queue.takeAll
      _ <- queue.shutdown
    } yield allTasks

}
