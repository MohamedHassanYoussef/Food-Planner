package com.example.myfoodplaner.listsdetail.presenter;


import com.example.myfoodplaner.model.Dtopresenter.MealsDetail;
import com.example.myfoodplaner.model.Dtopresenter.MealsDetailResponse;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;

import io.reactivex.rxjava3.core.Single;

public interface ListDetailPresenterView {
    public Single<MealsDetailResponse> getMealDetail(String category);
    public void addToFav(MealsDetail mealsDetail);
    public void SetClickedItemData(WeekPlan selectedDate);
}
