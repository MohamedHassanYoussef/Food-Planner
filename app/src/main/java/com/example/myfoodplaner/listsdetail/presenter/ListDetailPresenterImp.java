package com.example.myfoodplaner.listsdetail.presenter;

import com.example.myfoodplaner.listsdetail.view.ListDetailView;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;
import com.example.myfoodplaner.model.MealRepositoryView;
import com.example.myfoodplaner.model.Dtopresenter.MealsDetail;
import com.example.myfoodplaner.model.Dtopresenter.MealsDetailResponse;
import com.example.myfoodplaner.model.netowark.MealDetailsCallback;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class ListDetailPresenterImp implements ListDetailPresenterView, MealDetailsCallback {
    private ListDetailView listDetailView;
    private MealsDetail mealsDetail;
    private MealRepositoryView mealRepositoryView;

    public ListDetailPresenterImp(ListDetailView listDetailView, MealRepositoryView mealRepositoryView){
        this.listDetailView = listDetailView;
        this.mealRepositoryView = mealRepositoryView;
    }
    @Override
    public Single<MealsDetailResponse> getMealDetail(String category) {
        return mealRepositoryView.getMealByIdNetworkCall(category);
    }

    @Override
    public void addToFav(MealsDetail mealsDetail) {
        this.mealsDetail = mealsDetail;
        MealsItem mealItem = new MealsItem();
        mealItem.setMealData(mealsDetail);
        mealRepositoryView.insertMeal(mealItem);
    }

    @Override
    public void SetClickedItemData(WeekPlan selectedDate) {
        mealRepositoryView.insertWeekPlanMeal(selectedDate);
    }

    @Override
    public void onSuccessResult(List<MealsDetail> mealsDetailList) {
        listDetailView.showMealDetailData(mealsDetailList);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        listDetailView.showMealDetailErrorMsg(errorMsg);
    }
}
