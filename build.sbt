name := "FunctionComposition"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
libraryDependencies += "joda-time" % "joda-time" % "2.9.4"
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.4"

scalacOptions ++= Seq(
  "-language:postfixOps"
)