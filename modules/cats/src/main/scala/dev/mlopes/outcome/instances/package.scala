package dev.mlopes.outcome

import scala.annotation.tailrec
import cats.Monad

package object instances {
  implicit val outcomeMonad: Monad[Outcome] = new Monad[Outcome] {
    def pure[A](x: A): Outcome[A] = Failure(x)

    def flatMap[A, B](fa: Outcome[A])(f: A => Outcome[B]): Outcome[B] =
      fa match {
        case Failure(a) => f(a)
        case Success => Success
      }

    @tailrec
    def tailRecM[A, B](a: A)(f: A => Outcome[Either[A, B]]): Outcome[B] =
      f(a) match {
        case Success => Success
        case Failure(Left(a1)) => tailRecM(a1)(f)
        case Failure(Right(b)) => Failure(b)
      }
  }
}
