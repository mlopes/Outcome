package dev.mlopes.outcome

sealed trait Outcome[+E] extends Product with Serializable {
  def asOption: Option[E] = this match {
    case Failure(reason) => Some(reason)
    case Success => None
  }

  def asEither: Either[E, Unit] = this match {
    case Success => Right(())
    case Failure(e) => Left(e)
  }
}

final case class Failure[+E](reason: E) extends Outcome[E]
final case object Success extends Outcome[Nothing]

