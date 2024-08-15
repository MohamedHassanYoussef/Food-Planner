package com.example.myfoodplaner.mealdetail.view;


import com.example.myfoodplaner.model.Dtopresenter.MealsItem;

public interface MealDetailView {
    public void showItemDetailData(MealsItem mealsItem);
    public void addToFav(MealsItem mealsItem);
    public void showItemDetailErrorMsg(String error);
}
