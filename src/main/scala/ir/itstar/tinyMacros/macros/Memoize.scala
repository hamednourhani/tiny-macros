package ir.itstar.tinyMacros.macros

import scala.meta._
import scala.annotation._


/**
  * reference : https://medium.com/@Arhelmus/metaprogramming-magic-with-scalameta-67e849ab490e
  */

class Memoize extends StaticAnnotation {

  inline def apply(defn: Any) :Any = meta {
    defn match {
      case _ => abort("@Memoize must annotaite a function")
    }
  }
}
