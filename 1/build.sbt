name := """DDDOnionCookExample"""

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "com.google.inject" % "guice" % "4.1.0",
  "net.codingwell" %% "scala-guice" % "4.1.1"
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oDF")
