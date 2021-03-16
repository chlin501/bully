package bully

import scala.util.Random
import zio._

object Node {

  case class State(
      phase: Option[Phase] = None,
      status: Option[Status] = None
  ) {
    def update(
        transPhase: Option[Phase] => Option[Phase],
        transStatus: Option[Status] => Option[Status]
    ): IO[Throwable, Unit] = {
      for {
        newState <- IO.effect(
          copy(phase = transPhase(phase), status = transStatus(status))
        )
      } yield newState
    }
  }

  def defaultNode(): StatefulNode =
    new StatefulNode {
      override def receive(
          state: State,
          message: Message
      ): IO[Throwable, Unit] = {
        state.phase match {
          case Some(_phase) =>
            _phase match {
              case Join(id)    => IO.unit
              case ElectionRun => IO.unit
            }
          case None => IO.unit
        }
      }
    }

  trait StatefulNode {
    def receive(status: State, message: Message): IO[Throwable, Unit]

    def create(
        id: Int = getId,
        initial: State = State()
    ): ZIO[Any, Nothing, Node] = {
      def process(state: State, message: Message): ZIO[Any, Throwable, Unit] =
        for {
          _ <- receive(state, message)
        } yield ()
      for {
        queue <- Queue.bounded[Message](requestedCapacity = 32)
        _ <- (for {
            message <- queue.take
            _ <- process(initial, message)
          } yield ()).forever.fork
      } yield new Node(id = Option(id), queue = queue)
    }
  }

  /**
    * Id value is between 1 and N
    * @return
    */
  def getId: Int = Random.nextInt() & Int.MaxValue

}
case class Node(
    id: Option[Int] = None,
    role: Option[Role] = None,
    queue: Queue[Message]
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
