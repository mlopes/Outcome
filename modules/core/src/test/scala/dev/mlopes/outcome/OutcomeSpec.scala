package dev.mlopes.outcome

import org.scalatest.WordSpec
import org.scalatest.Matchers
import org.scalatest.Inside

import dev.mlopes.outcome.syntax._

class OutcomeSpec extends WordSpec with Matchers with Inside {
  "Outcome" should {
    "be created with success" in {
      val outcome: Outcome[String] = Success
      outcome shouldBe a [Outcome[_]]
      outcome shouldBe a [Success.type]
    }

    "be created with a failure" in {
      val outcome: Outcome[String] = Failure("this failed")
      outcome shouldBe a [Outcome[_]]
      outcome shouldBe a [Failure[_]]
    }

    "be isomorphic to Option[A]" in {
      val success: Outcome[Int] = Success
      success.asOption shouldBe a [Option[_]]
      success.asOption should matchPattern { case None => }

      val failed: Outcome[String] = Failure("abc")
      failed.asOption shouldBe a [Option[_]]
      failed.asOption should matchPattern { case Some(_) => }
      inside(failed.asOption) { case Some(i) => i shouldBe a [String] }

      val some: Option[Boolean] = Some(true)
      some.asOutcome shouldBe a [Outcome[_]]
      some.asOutcome should matchPattern { case Failure(true) => }

      val none: Option[Int] = None
      none.asOutcome shouldBe a [Outcome[_]]
      none.asOutcome should matchPattern { case Success => }
    }

    "be isomorphic to Either[E, Unit]" in {
      val success: Outcome[Int] = Success
      success.asEither shouldBe a [Either[_, Unit]]
      success.asEither should matchPattern { case Right(()) => }

      val failed: Outcome[String] = Failure("abc")
      failed.asEither shouldBe a [Either[_, Unit]]
      failed.asEither should matchPattern { case Left(_) => }
      inside(failed.asEither) { case Left(e) => e shouldBe a [String] }

      val left: Either[Boolean, Unit] = Left(true)
      left.asOutcome shouldBe a [Outcome[_]]
      left.asOutcome should matchPattern { case Failure(true) => }

      val right: Either[Int, Unit] = Right(())
      right.asOutcome shouldBe a [Outcome[_]]
      right.asOutcome should matchPattern { case Success => }
    }
  }
}
