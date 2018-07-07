name := "tiny-macros"

scalaVersion := "2.12.6"

organization := "ir.itstar"

version := "0.0.1"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

resolvers += Resolver.sonatypeRepo("releases")

val paradiseVersion = "2.1.0"

libraryDependencies ++= Seq(
  "org.scalameta" %% "scalameta" % "3.7.4",
  "org.scala-lang" % "scala-reflect" % "2.12.6",
  "org.scalatest" %% "scalatest" % "3.0.4"

)

addCompilerPlugin("org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full)