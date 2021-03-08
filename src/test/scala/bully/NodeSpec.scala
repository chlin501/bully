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
    val nodeP = new NodeP {
      override def receive(message: Message): IO[Throwable, Unit] =
        message match {
          case Halt =>
            assertResult(Halt)(message)
            IO.unit
        }
    }
    val node = nodeP.create()
    val result = for {
      n <- node
      _ <- n ! Halt
    } yield ()
    val runtime = Runtime.default
    runtime.unsafeRun(result)
  }

}
