package ir.itstar.tinyMacros.macros

import org.scalatest.{Matchers, WordSpec}


class MemoizeTest extends WordSpec with Matchers {

  "Function after macro transformation " should {

    "cache function result " in new Context {
      testFunction("a", 1, 1L)
      testFunction("b", 1, 1L)
      testFunction("c", 1, 1L)
      testFunction("d", 1, 1L)

      callsCount shouldBe 1
    }

    "cache function result depend parameter" in new Context {
      testFunction("a", 1, 1L)
      testFunction("b", 2, 1L)
      testFunction("c", 3, 1L)
      testFunction("d", 4, 1L)

      callsCount shouldBe 4
    }


  }


  trait Context {

    var callsCount = 0

    @Memoize
    def testFunction(a: String, b: Int, c: Long) = {
      callsCount += 1
      s"$a - $b - $c"
    }
  }

}
