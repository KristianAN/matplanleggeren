package no.krined.noplan.common.repository

import no.krined.noplan.common.data.retter.Meal

trait MealRepository[F[_]]:
  def storeMeal(meal: Meal): F[Unit]
  def allMeals: F[Vector[Meal]]
  def removeMeal(mealName: String): F[Unit]
  def updateMeals(meals: Vector[Meal]): F[Unit]
