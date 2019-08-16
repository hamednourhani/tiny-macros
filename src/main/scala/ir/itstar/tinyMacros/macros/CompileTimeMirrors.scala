package ir.itstar.tinyMacros.macros

case class Location(filename: String, line: Int, column: Int)

object CompileTimeMirrors {

  import scala.reflect.macros._

  def getCurrentLocation: Location = macro getCurrentLocationImpl

  def getCurrentLocationImpl(c: Context): c.Expr[Location] = {
    import c.universe._
    val pos         = c.macroApplication.pos
    val clsLocation = c.mirror.staticModule("Location")
    c.Expr(
      Apply(Ident(clsLocation),
            List(Literal(Constant(pos.source.path)),
                 Literal(Constant(pos.line)),
                 Literal(Constant(pos.column))))
    )

  }

}
