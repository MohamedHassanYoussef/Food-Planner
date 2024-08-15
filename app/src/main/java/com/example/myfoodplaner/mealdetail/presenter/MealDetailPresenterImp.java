package com.example.myfoodplaner.mealdetail.presenter;


import com.example.myfoodplaner.mealdetail.view.MealDetailView;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;
import com.example.myfoodplaner.model.MealRepositoryView;

public class MealDetailPresenterImp implements MealDetailPresenterView{

    private MealDetailView mealDetailView;
    private MealRepositoryView mealRepositoryView;

    public MealDetailPresenterImp(MealDetailView mealDetailView, MealRepositoryView mealRepositoryView) {
        this.mealDetailView = mealDetailView;
        this.mealRepositoryView = mealRepositoryView;
    }

    @Override
    public void SetClickedItemData(WeekPlan selectedDate) {
        mealRepositoryView.insertWeekPlanMeal(selectedDate);
    }

    @Override
    public void addToFav(MealsItem mealsItem) {
        // Implement your logic to add the meal to favorites
        mealRepositoryView.insertMeal(mealsItem);
    }

    private void addToCalendar(MealsItem mealsItem, String selectedDate) {

        System.out.println("Selected Date: " + selectedDate);
        System.out.println("Meal Details: " + mealsItem);
    }

}
