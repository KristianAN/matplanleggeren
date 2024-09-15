package no.krined.noplan.app.component

import tyrian.Html.*

object Button:
  enum ButtonVariant:
    case Default

  private def variantClass(variant: ButtonVariant): String =
    variant match
      case ButtonVariant.Default =>
        "bg-primary text-primary-foreground hover:bg-primary/90"

  enum ButtonSize:
    case Default

  private def sizeClass(size: ButtonSize): String =
    size match
      case ButtonSize.Default => "h-10 px-4 py-2"

  private val base =
    "px-6 py-2 font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-blue-600 rounded-lg hover:bg-blue-500 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-80"

  def apply[M](
      label: String,
      onClickMsg: M,
      variant: ButtonVariant = ButtonVariant.Default,
      size: ButtonSize = ButtonSize.Default
  ) =
    button(
      `class` := s"$base",
      onClick(onClickMsg)
    )(label)
