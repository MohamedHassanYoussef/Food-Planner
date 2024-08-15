package com.example.myfoodplaner.model;


import com.example.myfoodplaner.model.Dtopresenter.CategoriesItem;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetailsResponse;
import com.example.myfoodplaner.model.Dtopresenter.MealsDetail;
import com.example.myfoodplaner.model.Dtopresenter.MealsDetailResponse;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.MealsItemResponse;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;
import com.example.myfoodplaner.model.netowark.AreaItemCallback;
import com.example.myfoodplaner.model.netowark.CategoryItemCallBack;
import com.example.myfoodplaner.model.netowark.IngredientsItemCallback;
import com.example.myfoodplaner.model.netowark.RandomMealItemCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Query;

public interface MealRepositoryView {
    //Remote
    public void RandomMealNetworkCall(RandomMealItemCallback networkCallback);
    public void CategoryNetworkCall(CategoryItemCallBack categoryCallBack);
    public void IngredientsNetworkCall(IngredientsItemCallback ingredientsCallback);
    public void AreasNetworkCall(AreaItemCallback areaMealCallback);
    public Single<ListsDetailsResponse> CategoryDetailsNetworkCall(String category);
    public Single<ListsDetailsResponse> IngredientDetailsNetworkCall(String category);
    public Single<ListsDetailsResponse> AreaDetailsNetworkCall(String category);
    public Single<MealsItemResponse> SearchByNameNetworkCall(String name);
    public Single<MealsDetailResponse> getMealByIdNetworkCall(String name);
    public Single<ListsDetailsResponse> getMealsByArea( String category);

    //Local
    public Single<List<MealsItem>> getFavoriteMeals();
    public Single<List<MealsDetail>>getListMealDetails();
    public void deleteMeal(MealsItem mealsItem);
    public void insertMeal(MealsItem mealsItem);
    public void deleteMeal(MealsDetail mealsItem);
    public void insertMeal(MealsDetail mealsItem);

    public Single<List<WeekPlan>> getWeekPlanMeals();
    Single<List<WeekPlan>> getMealsForDate(String date);
    public void deleteWeekPlanMeal( WeekPlan weekPlan);
    public void insertWeekPlanMeal(WeekPlan weekPlan);


    Single<List<MealsItem>> getFavoriteMealsSingle();


    //RemoteDB
    void insertMealRemoteToFavorite(MealsItem mealsItem);
    void insertMealRemoteToWeekPlan(WeekPlan weekPlan);
    void deleteMealRemoteFromFavorite(MealsItem mealsItem);
    void deleteMealRemoteFromWeekPlan(WeekPlan weekPlan);

    public void deleteAllTheCalenderList();
    public void deleteAllTheFavoriteList();



}
