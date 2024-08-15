package com.example.myfoodplaner.model;

import com.example.myfoodplaner.model.Dtopresenter.ListsDetailsResponse;
import com.example.myfoodplaner.model.Dtopresenter.MealsDetail;
import com.example.myfoodplaner.model.Dtopresenter.MealsDetailResponse;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.MealsItemResponse;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;
import com.example.myfoodplaner.model.netowark.AreaItemCallback;
import com.example.myfoodplaner.model.netowark.CategoryItemCallBack;
import com.example.myfoodplaner.model.netowark.IngredientsItemCallback;
import com.example.myfoodplaner.model.netowark.MealRemoteDataSourceImpl;
import com.example.myfoodplaner.model.netowark.RandomMealItemCallback;
import com.example.myfoodplaner.model.netowark.database.MealLocalDataSourceImpl;
import com.example.myfoodplaner.model.netowark.remotedb.RemoteDatabaseImp;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealRepositoryImpl implements MealRepositoryView {

    MealRemoteDataSourceImpl mealRemoteDataSource;
    MealLocalDataSourceImpl mealLocalDataSource;
   RemoteDatabaseImp remoteDatabaseImp;
    static MealRepositoryImpl mealRepository;



    public static MealRepositoryImpl getInstance(MealRemoteDataSourceImpl mealRemoteDataSource,MealLocalDataSourceImpl mealLocalDataSource){
        if(mealRepository == null)
            mealRepository = new MealRepositoryImpl(mealRemoteDataSource,mealLocalDataSource);

        return  mealRepository;
    }

 public MealRepositoryImpl(MealRemoteDataSourceImpl mealRemoteDataSource,MealLocalDataSourceImpl mealLocalDataSource){
  this.mealRemoteDataSource = mealRemoteDataSource;
  this.mealLocalDataSource = mealLocalDataSource;
  this.remoteDatabaseImp = new RemoteDatabaseImp();
 }

    @Override
    public void RandomMealNetworkCall(RandomMealItemCallback networkCallback) {
        mealRemoteDataSource.RandomMealNetworkCall(networkCallback);
    }

    @Override
    public void CategoryNetworkCall(CategoryItemCallBack categoryCallBack) {
        mealRemoteDataSource.CategoryNetworkCall(categoryCallBack);
    }

    @Override
    public void IngredientsNetworkCall(IngredientsItemCallback ingredientsCallback) {
        mealRemoteDataSource.IngredientsNetworkCall(ingredientsCallback);
    }

    @Override
    public void AreasNetworkCall(AreaItemCallback areaMealCallback) {
        mealRemoteDataSource.AreasNetworkCall(areaMealCallback);
    }

    @Override
    public Single<ListsDetailsResponse> CategoryDetailsNetworkCall(String category) {
        return mealRemoteDataSource.CategoryDetailsNetworkCall(category).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ListsDetailsResponse> IngredientDetailsNetworkCall(String category) {
        return mealRemoteDataSource.IngredientDetailsNetworkCall(category).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ListsDetailsResponse> AreaDetailsNetworkCall(String category) {

        return mealRemoteDataSource.AreaDetailsNetworkCall(category).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<MealsItemResponse> SearchByNameNetworkCall(String name) {
        return mealRemoteDataSource.searchByNameNetworkCall(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<MealsDetailResponse> getMealByIdNetworkCall(String name) {
        return mealRemoteDataSource.getMealDetailNetworkCall(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ListsDetailsResponse> getMealsByArea(String areaId) {
        return mealRemoteDataSource.AreaDetailsNetworkCall(areaId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
 public Single<List<MealsItem>> getFavoriteMeals() {
  return mealLocalDataSource.getAllFavoriteStoredMeals();
 }

 @Override
 public Single<List<MealsDetail>> getListMealDetails() {
  return mealLocalDataSource.getAllFavoriteStoredMealsDetail();
 }

 @Override
 public void deleteMeal(MealsItem mealsItem) {
  deleteMealRemoteFromFavorite(mealsItem);
  mealLocalDataSource.deleteMealFromFavorite(mealsItem);
 }

 @Override
 public void insertMeal(MealsItem mealsItem) {
  insertMealRemoteToFavorite(mealsItem);
  mealLocalDataSource.insertMealToFavorite(mealsItem);
 }

 @Override
 public void deleteMeal(MealsDetail mealsItem) {
  mealLocalDataSource.deleteMealDetailFromFavorite(mealsItem);
 }

 @Override
 public void insertMeal(MealsDetail mealsItem) {
  mealLocalDataSource.insertMealDetailToFavorite(mealsItem);
 }

 @Override
 public Single<List<WeekPlan>> getWeekPlanMeals() {
  return mealLocalDataSource.getWeekPlanMeals();
 }

 @Override
 public Single<List<WeekPlan>> getMealsForDate(String date) {
  return mealLocalDataSource.getMealsForDate(date);
 }

 @Override
 public void deleteWeekPlanMeal(WeekPlan weekPlan) {
  deleteMealRemoteFromWeekPlan(weekPlan);
  mealLocalDataSource.deleteWeekPlanMealFromCalender(weekPlan);
 }

 @Override
 public void insertWeekPlanMeal(WeekPlan weekPlan) {
  insertMealRemoteToWeekPlan(weekPlan);
  mealLocalDataSource.insertWeekPlanMealToCalender(weekPlan);
 }

 @Override
 public Single<List<MealsItem>> getFavoriteMealsSingle() {
  return mealLocalDataSource.getAllFavoriteStoredMeals();
 }

 //RemoteDB

 @Override
 public void insertMealRemoteToFavorite(MealsItem mealsItem) {
  remoteDatabaseImp.insertToFavorite(mealsItem)
          .subscribe(() -> {
                  },
                  throwable -> {
                  });

 }

 @Override
 public void insertMealRemoteToWeekPlan(WeekPlan weekPlan) {
  remoteDatabaseImp.insertToWeekPlan(weekPlan)
          .subscribe(()->{},throwable ->{

          });
 }

 @Override
 public void deleteMealRemoteFromFavorite(MealsItem mealsItem) {
  remoteDatabaseImp.deleteFromFavorite(mealsItem);
 }

 @Override
 public void deleteMealRemoteFromWeekPlan(WeekPlan weekPlan) {
  remoteDatabaseImp.deleteFromWeekPlane(weekPlan);
 }
 @Override
 public void deleteAllTheCalenderList() {
  mealLocalDataSource.deleteAllTheCalenderList();
 }

 @Override
 public void deleteAllTheFavoriteList() {
  mealLocalDataSource.deleteAllTheFavoriteList();
 }

}
