package com.example.myfoodplaner.model.netowark.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myfoodplaner.model.Dtopresenter.MealsDetail;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDAO {
    @Query("SELECT * FROM meals")
    Single<List<MealsItem>> getAllFavoriteMeals();

    @Query("SELECT * FROM mealdetail")
    Single<List<MealsDetail>> getAllMeals();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMealToFavorite(MealsItem mealsItem);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMealDetailToFavorite(MealsDetail mealsDetail);

    @Delete
    void deleteMealFromFavorite(MealsItem mealsItem);
    @Delete
    void deleteMealDetailFromFavorite(MealsDetail mealsDetail);


    @Query("SELECT * FROM weekplan")
    Single<List<WeekPlan>> getWeekPlanMeals();

    @Query("SELECT * FROM weekplan WHERE date = :date")
    Single<List<WeekPlan>> getMealsForDate(String date);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWeekPlanMealToCalender(WeekPlan weekPlan);
    @Delete
    void deleteWeekPlanMealFromCalender(WeekPlan weekPlan);


    //Remote DB
    @Query("DELETE FROM weekplan")
    void deleteAllTheCalenderList();

    @Query("DELETE FROM meals")
    void deleteAllTheFavoriteList();


}
