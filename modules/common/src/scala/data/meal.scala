package no.krined.noplan.common.data.retter

enum BaseMealComponent:
  case Fish
  case RedMeat
  case Chicken
  case Vegetarian

enum PrepareTime:
  case Short
  case Medium
  case Long

opaque type MealProbabilityRating = Double

final case class Meal(
    navn: String,
    prepareTime: PrepareTime,
    BaseMealComponent: BaseMealComponent,
    mealProbabilityRating: MealProbabilityRating
)
