package ir.itstar.tinyMacros.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox


trait Transformer[T] {
  def toOption(t: T): Option[T]
}

protected[macros] object Macros {
  def implTransformer[T](c: blackbox.Context)(implicit tag: c.WeakTypeTag[T]): c.universe.Tree = {
    import c.universe._

    q"""
       new Transformer[${tag.tpe}]{
          def toOption(t : ${tag.tpe}) : Option[${tag.tpe}] = Some.apply[${tag.tpe}](t)
       }
      """
  }
}

object TransformerMacro {
  implicit def transform[T]: Transformer[T] = macro Macros.implTransformer[T]
}

