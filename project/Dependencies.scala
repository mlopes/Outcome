import sbt._

object Dependencies {

  private val catsVersion = "2.0.0-M4"

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"

  lazy val catsDependencies = Seq(
    "org.typelevel" %% "cats-core" % catsVersion
  )
}
