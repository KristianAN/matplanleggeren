package no.krined.noplan.common.algo.probability

import cats.syntax.all.*
import cats.Functor

/** Will prefer items at the end of the vector
  *
  * @param items
  * @param random
  *   Must return a double between 0.0 and 1.0
  * @param scale
  * @return
  */
def pickItemWithPowProbability[F[_]: Functor, T](
    items: Vector[T],
    random: => F[Double],
    scale: Double = 1.0
): F[T] =
  random
    .map(num => (num * scale % items.length).toInt)
    .map: r =>
      val idx = Math.max(0, Math.min(items.length, r))
      items(idx)

// def pickItemWithPowProbability[T](items: List[T], random: => IO[Double], scale: Double = 1.0): IO[T] =
//   random.map: num =>
//     val r = (num * scale % items.length).toInt
//     items(Math.max(0, Math.min(items.length, r)))
