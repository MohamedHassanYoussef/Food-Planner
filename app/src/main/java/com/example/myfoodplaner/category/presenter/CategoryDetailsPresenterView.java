package com.example.myfoodplaner.category.presenter;


import com.example.myfoodplaner.model.Dtopresenter.ListsDetailsResponse;

import io.reactivex.rxjava3.core.Single;

public interface CategoryDetailsPresenterView {
    public Single<ListsDetailsResponse> getCategoryDetail(String category);
    public Single<ListsDetailsResponse> getMealsByArea(String areaId);
    public Single<ListsDetailsResponse> IngredientDetailsNetworkCall(String id);
}
