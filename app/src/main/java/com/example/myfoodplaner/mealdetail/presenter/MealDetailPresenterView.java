package com.example.myfoodplaner.mealdetail.presenter;


import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;

public interface MealDetailPresenterView {
    public void SetClickedItemData(WeekPlan selectedDate);
    public void addToFav(MealsItem mealsItem);
}
