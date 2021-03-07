package bully

import org.scalatest._
import flatspec._
import matchers._

class NodeSpec extends AnyFlatSpec with should.Matchers {

  "Id" should "be between 1 and N" in {
    import Node._
    val id = getId
    assert(1 <= id && id <= Int.MaxValue)
  }

}
