package ir.itstar.tinyMacros.macros



import scala.annotation._
import scala.meta._
import dialects.ParadiseTypelevel211.

/**
  * ref : https://medium.com/@Arhelmus/metaprogramming-magic-with-scalameta-67e849ab490e
  * ref : https://docs.scala-lang.org/sips/inline-meta.html#inline-reduction
  * scalameta cheatSheet : https://github.com/scalameta/scalameta/blob/master/notes/quasiquotes.md
  */

@compileTimeOnly("should be used only in compile time")
class Memoize extends StaticAnnotation {

  inline def apply(defn: Any) :Any = meta {
    defn match {
      case q"def $name(...$paramss) : $result = {..$body}" =>
        q"def $name(...$paramss) : $result = {..$body}"
//      case _ => abort("@Memoize must annotaite a function")
    }
  }
}
