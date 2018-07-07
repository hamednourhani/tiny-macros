package ir.itstar.tinyMacros.macros

import scala.meta._
import org.scalatest.{Matchers, WordSpec}


class MemoizeTest extends WordSpec with Matchers {

  "Function after macro transformation " should {

    "cache function result " in new Context {
      testFunction("a", 1, 1L)
      testFunction("b", 2, 2L)
      testFunction("c", 3, 3L)

      callsCount shouldBe 3
    }


  }


  trait Context {

    var callsCount = 0

    def testFunction(a: String, b: Int, c: Long) = {
      callsCount += 1
      q"$a - $b - $c"
    }
  }

}
