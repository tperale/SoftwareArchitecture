name := """DDDOnionCookExample"""

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.4" % "test"
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oDF")
