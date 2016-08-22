name := """argentum"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.adrianhurt" %% "play-bootstrap" % "1.0-P25-B3",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.webjars" % "font-awesome" % "4.5.0",
  "org.webjars" % "bootstrap-datepicker" % "1.4.0",
  "org.webjars.bower" % "datatables.net" % "1.10.12",
  "org.webjars.bower" % "datatables.net-bs" % "1.10.12"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

