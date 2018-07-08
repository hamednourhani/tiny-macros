package ir.itstar.tinyMacros.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import scala.reflect.runtime.universe._


trait Query[T]

object Query {

  implicit class QueryApi[T](q: Query[T]) {
    def map[U](f: T => U): Query[U] = macro QueryMacros.map
  }

}

case class Table[T: TypeTag]() extends Query[T]

case class Select[T, U](q: Query[T], fn: Node[U]) extends Query[T]

trait Node[T]

case class Ref[T](name: String) extends Node[T]

object Database {

  def execute[T](q: Query[T]): List[T] = ???
}


object QueryMacros {

  def map(c: blackbox.Context)(f: c.Tree): c.Tree = {
    import c.universe._

    //Query.QueryApi[T](q:Query[T])
    val q"$_.$_[$_]($prefix)" = c.prefix

    val node = f match {
      case q"($param) => $body" =>
        body match {
          case q"$qual.$_" if qual.symbol == param.symbol =>
            val field = body.symbol.name.decodedName.toString
            q"Ref[${body.tpe}]($field)"
        }
    }

    val result = q"Select($prefix,$node)"

    println(result)

    result
  }

}










