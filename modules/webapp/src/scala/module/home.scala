package no.krined.noplan.app.module

import cats.effect.IO
import no.krined.noplan.app.Model
import no.krined.noplan.app.Msg
import tyrian.Html.*
import tyrian.*

object Home:

  case class HomeModel(
      val title: String
  )

  def init: HomeModel =
    HomeModel(
      title = "Hello world"
    )

  enum HomeMsg:
    case NoOp

  def updateModel(msg: HomeMsg, model: HomeModel): HomeModel =
    msg match
      case HomeMsg.NoOp => model

  def update(msg: HomeMsg, model: Model): (Model, Cmd[IO, Msg]) =
    val updatedModel = updateModel(msg, model.homeModel)
    msg match
      case HomeMsg.NoOp => (model.copy(homeModel = updatedModel), Cmd.None)

  def view(model: HomeModel): Html[HomeMsg] =
    div(
      h1(model.title)
    )
