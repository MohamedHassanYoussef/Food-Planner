package com.example.myfoodplaner.model.netowark.database;


import android.content.Context;
import android.util.Log;

import com.example.myfoodplaner.model.Dtopresenter.MealsDetail;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;


import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class MealLocalDataSourceImpl implements MealLocalDataSource{
    private MealDAO mealDAO;
    private static MealLocalDataSourceImpl mealLocalDataSource = null;
    private Single<List<MealsItem>> storedFavoriteMeals;
    private Single<List<MealsDetail>> storedFavoriteMealsDetail;
    private Single<List<WeekPlan>> storedWeekPlanMeals;
    private MealLocalDataSourceImpl(Context context) {
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.getMealsDAO();
        storedFavoriteMeals = mealDAO.getAllFavoriteMeals();
        storedWeekPlanMeals = mealDAO.getWeekPlanMeals();
        storedFavoriteMealsDetail = mealDAO.getAllMeals();
    }

    public static MealLocalDataSourceImpl getInstance(Context context) {
        if (mealLocalDataSource == null)
            mealLocalDataSource = new MealLocalDataSourceImpl(context);

        return mealLocalDataSource;
    }

    @Override
    public void insertMealToFavorite(MealsItem mealsItem) {
        new Thread(() -> {
            mealDAO.insertMealToFavorite(mealsItem);
            Log.i("TAG", "inserting: Adding Item ");
        }).start();
    }

    @Override
    public void deleteMealFromFavorite(MealsItem mealsItem) {
        new Thread(() -> {
            mealDAO.deleteMealFromFavorite(mealsItem);
        }).start();
    }

    @Override
    public Single<List<MealsItem>> getAllFavoriteStoredMeals() {
        return storedFavoriteMeals;
    }

    @Override
    public void insertMealDetailToFavorite(MealsDetail mealsDetail) {
        new Thread(() -> {
            mealDAO.insertMealDetailToFavorite(mealsDetail);
            Log.i("TAG", "inserting: Adding Item ");
        }).start();
    }

    @Override
    public void deleteMealDetailFromFavorite(MealsDetail mealsDetail) {
        new Thread(() -> {
            mealDAO.deleteMealDetailFromFavorite(mealsDetail);
        }).start();
    }

    @Override
    public Single<List<MealsDetail>> getAllFavoriteStoredMealsDetail() {
        return storedFavoriteMealsDetail;
    }

    @Override
    public Single<List<WeekPlan>> getWeekPlanMeals() {
        return storedWeekPlanMeals;
    }

    @Override
    public void insertWeekPlanMealToCalender(WeekPlan weekPlan) {
        new Thread(() -> {
            mealDAO.insertWeekPlanMealToCalender(weekPlan);
            Log.i("TAG", "inserting: Adding Item to calender ");
        }).start();
    }

    @Override
    public void deleteWeekPlanMealFromCalender(WeekPlan weekPlan) {
        new Thread(() -> {
            mealDAO.deleteWeekPlanMealFromCalender(weekPlan);
            Log.i("TAG", "deleting: Removing item from calender ");
        }).start();
    }
    @Override
    public Single<List<WeekPlan>> getMealsForDate(String date) {
        // Assuming you have a method in your DAO to query meals for a specific date
        return mealDAO.getMealsForDate(date);
    }

    @Override
    public void deleteAllTheCalenderList() {
        new Thread(() -> {
            mealDAO.deleteAllTheCalenderList();
            Log.i("TAG", "Deleting all meals from the calendar list");
        }).start();
    }

    @Override
    public void deleteAllTheFavoriteList() {
        new Thread(() -> {
            mealDAO.deleteAllTheFavoriteList();
            Log.i("TAG", "Deleting all meals from the favorite list");
        }).start();
    }
}
