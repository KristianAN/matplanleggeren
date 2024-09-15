package no.krined.noplan.app

import cats.effect.IO
import no.krined.noplan.app.module.*
import no.krined.noplan.common.webapp.Route
import tyrian.Html.*
import tyrian.*
import no.krined.noplan.app.component.Button

object NoplanApp extends TyrianIOApp[Msg, Model]:

  def router: Location => Msg =
    case loc: Location.Internal =>
      loc.pathName match
        case Route.Home.path => Msg.NavigateTo(Route.Home)

    case _ => Msg.NoOp

  def init(flags: Map[String, String]): (Model, Cmd[IO, Msg]) =
    (
      Model.init // .copy(
      //   baseUrl = flags("InitialBaseUrl").replace("5173", "8080")
      // ),
      ,
      Cmd.None
    )

  def update(model: Model): Msg => (Model, Cmd[IO, Msg]) =
    case Msg.HomeMsg(msg) => Home.update(msg, model)
    case Msg.NoOp         => (model, Cmd.None)
    case Msg.NavigateTo(route) =>
      (
        model.copy(activeRoute = route),
        route match
          case _ => Cmd.None
      )

  def view(model: Model): Html[Msg] =
    baseView(
      model.activeRoute match
        case Route.Home => Home.view(model.homeModel).map(Msg.HomeMsg(_))
      ,
      model.activeRoute
    )

  def subscriptions(model: Model): Sub[IO, Msg] = Sub.None

def baseView(v: Html[Msg], active: Route): Html[Msg] =
  div(`class` := "bg-background bg-green")(
    h1(`class` := "text-4xl")("Hello World"),
    p("button component"),
    button(`class` := "btn btn-primary")("button"),
    Button("button", Msg.NoOp)
  )

def setActive(route: Route, active: Route): String =
  if route == active then "active" else ""

enum Msg:
  case NoOp
  case NavigateTo(route: Route)
  case HomeMsg(msg: Home.HomeMsg)

case class Model(
    baseUrl: String = "",
    activeRoute: Route,
    homeModel: Home.HomeModel
):
  def reset =
    Model(
      this.baseUrl,
      Route.Home,
      Home.init
    )

object Model:
  val init = Model("", Route.Home, Home.init)
