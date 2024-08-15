package com.example.myfoodplaner.search.presenter;


import com.example.myfoodplaner.model.Dtopresenter.AreaItem;
import com.example.myfoodplaner.model.MealRepositoryView;
import com.example.myfoodplaner.model.netowark.AreaItemCallback;
import com.example.myfoodplaner.search.view.countrysearch.AreasView;

import java.util.List;

public class AreasPresenterImp implements AreasPresenterView, AreaItemCallback {

    private AreasView areasView;
    private MealRepositoryView mealRepositoryView;
    public AreasPresenterImp(AreasView areasView,MealRepositoryView mealRepositoryView){
        this.areasView = areasView;
        this.mealRepositoryView = mealRepositoryView;
    }
    @Override
    public void onSuccessResult(List<AreaItem> areaItemsItem) {
        areasView.showAreasData(areaItemsItem);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        areasView.showAreasErrorMsg(errorMsg);
    }

    @Override
    public void getArea() {
        mealRepositoryView.AreasNetworkCall(this);
    }
}
