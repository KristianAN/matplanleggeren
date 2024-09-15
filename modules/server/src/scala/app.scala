package no.krined.noplan

import cats.effect.IOApp
import cats.effect.IO

object App extends IOApp.Simple:
  override def run: IO[Unit] = IO.println("Hello World")
