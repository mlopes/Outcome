package dev.mlopes.outcome

package object syntax {
  implicit class OptionOps[A](option: Option[A]) {
    def asOutcome: Outcome[A] = option match {
      case None => Success
      case Some(e) => Failure(e)
    }
  }

  implicit class EitherOps[E](either: Either[E, Unit]) {
    def asOutcome: Outcome[E] = either match {
      case Left(f) => Failure(f)
      case Right(_) => Success
    }
  }
}
