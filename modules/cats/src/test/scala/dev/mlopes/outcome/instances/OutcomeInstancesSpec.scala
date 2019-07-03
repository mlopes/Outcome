package dev.mlopes.outcome.instances

import cats.Monad
import dev.mlopes.outcome._
import org.scalatest.{Matchers, WordSpec}
import org.scalactic.TypeCheckedTripleEquals


class OutcomeInstancesSpec extends WordSpec with Matchers with TypeCheckedTripleEquals {
  "Outcome Instances" should {
    "provide pure" in {
      Monad[Outcome].pure("123") should ===(Failure("123"))
    }

    "be flat mappable" in {
      Monad[Outcome].flatMap(Failure("fail"))(_ => Success) shouldBe Success
    }

    "be mappable" in {
      Monad[Outcome].map(Failure("fail"))(_ => 123) shouldBe Failure(123)
    }

    "be appliable" in {
      Monad[Outcome].ap(Failure({s: String => s.length }))(Failure("hello world")) shouldBe Failure(11)
    }

    "be map2able" in {
      Monad[Outcome].map2(Failure("Hello"), Failure("World"))((x: String, y: String) => s"$x $y") shouldBe Failure("Hello World")
    }

    "even lift" in {
      val f = Monad[Outcome].lift((s: String) => s.length)
      f(Failure("xyz")) shouldBe Failure(3)
    }
  }
}
