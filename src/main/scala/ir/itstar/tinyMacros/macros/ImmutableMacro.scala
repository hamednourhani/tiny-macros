package ir.itstar.tinyMacros.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

trait Immutable[T]

object Immutable {
  implicit def materialize[T]: Immutable[T] = macro ImmutableMacro.impl[T]

}

object ImmutableMacro {

  def impl[T](c: blackbox.Context)(implicit tag: c.WeakTypeTag[T]): c.universe.Tree = {

    import c.universe._

    def isImmutable(t: Type): Boolean = {

      val cache = scala.collection.mutable.HashMap[Type, Boolean]()

      def unCached(t: Type): Boolean = {
        t match {
          case AnnotatedType(annots, `t`) =>
            cache(t)

          case ExistentialType(defns, t) =>
            cached(t)

          case RefinedType(parents, defns) =>
            parents.exists(cached)
          case _: SingletonType            =>
            cached(t.widen)

          case TypeRef(_, sym: ClassSymbol, args) =>
            if (sym.isFinal || sym.isModuleClass) {
              val fieldTypeSignature = t.members.collect {
                case s: TermSymbol if !s.isMethod =>
                  if (s.isVar) return false
                  s.typeSignatureIn(t)
              }
              fieldTypeSignature.forall(cached)
            } else {
              false
            }

          case TypeRef(_, sym: TypeSymbol, args) =>
            val TypeBounds(_, hi) = sym.typeSignature.finalResultType
            cached(hi.substituteTypes(sym.typeParams, args))
          case _                                 =>
            sys.error(s"unsupported type : $t")

        }
      }

      def cached(t: Type) = {
        cache.getOrElseUpdate(t, {
          cache(t) = true
          unCached(t)
        })
      }

      cached(t)
    }
    val t = tag.tpe
    if (isImmutable(t)) q"null"
    else c.abort(c.enclosingPosition, s"$t is not immutable")


  }

}
