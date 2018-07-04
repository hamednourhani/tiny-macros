name := "tiny-macros"

scalaVersion := "2.12.6"

organization := "ir.itstar"

version := "0.0.1"

scalacOptions ++= Seq("-feature")

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.12.6"
)
