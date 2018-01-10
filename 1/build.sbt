name := """DDDOnionCookExample"""

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.4" % "test",
  "com.google.inject" % "guice" % "4.1.0",
  "com.google.inject.extensions" % "guice-assistedinject" % "4.0",
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oDF")
