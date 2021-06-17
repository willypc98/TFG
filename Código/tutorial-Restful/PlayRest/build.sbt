name := """PlayRest"""
organization := "com.rest"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"

libraryDependencies += guice

libraryDependencies ++= Seq(
  javaJdbc
)
libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.41"

)

libraryDependencies ++= Seq(
  "org.freemarker" % "freemarker" % "2.3.31"

)


//libraryDependencies += "net.sf.json-lib" % "json-lib" % "2.4" classifier "jdk15"
//libraryDependencies += "com.typesafe.play" %% "play-json" % -version-