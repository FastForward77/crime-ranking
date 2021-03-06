organization in ThisBuild := "com.scalacamp"

name := "crime-ranking"

version in ThisBuild := "0.0.1-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.11"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
)

mainClass in (Compile, packageBin) := Some("com.scalacamp.crimes.CrimeRankingApp")