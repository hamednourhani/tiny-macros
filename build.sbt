name := "tiny-macros"

scalaVersion := "2.12.6"

organization := "ir.itstar"

version := "0.0.1"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.scalameta" %% "scalameta" % "3.7.4",
  "org.scala-lang" % "scala-reflect" % "2.12.6"
)

addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M8" cross CrossVersion.full)