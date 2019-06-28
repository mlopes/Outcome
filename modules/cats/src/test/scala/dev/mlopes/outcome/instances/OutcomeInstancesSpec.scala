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
  }
}
