name := """Order-Management-Rest-API"""
organization := "com.company"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.1"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.8.1"
libraryDependencies += "org.mongodb" % "mongo-java-driver" % "3.8.2"
libraryDependencies += "log4j" % "log4j" % "1.2.17"

libraryDependencies ++= Seq(
  guice,
  "org.pac4j" % "pac4j-mongo" % "3.8.3",
  "org.pac4j" % "pac4j-http" % "3.8.3",
)

PlayKeys.playDefaultPort := 9000
