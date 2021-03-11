package bully

import org.scalatest._
import flatspec._
import matchers._

class NodeSpec extends AnyFlatSpec with should.Matchers {

  import Node._
  import zio._

  "Id" should "be between 1 and N" in {
    val id = getId
    assert(1 <= id && id <= Int.MaxValue)
  }

  "Node" should "receive message" in {
    val stateful = new StatefulNode {
      override def receive(
          status: bully.Status,
          message: Message
      ): IO[Throwable, Unit] =
        message match {
          case Halt =>
            assertResult(Halt)(message)
            IO.unit
        }
    }
    val node = stateful.create()
    val result = for {
      n <- node
      _ <- n ! Halt
    } yield n
    val runtime = Runtime.default
    runtime.unsafeRun(result.onExit { exit => URIO(exit.map(_.stop)) })
  }

}
