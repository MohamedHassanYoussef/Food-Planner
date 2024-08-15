package com.example.myfoodplaner.search.view.ingredientsearch;


import com.example.myfoodplaner.model.Dtopresenter.IngredientsItem;

import java.util.List;

public interface IngredientsView {
    public void showIngredientsData(List<IngredientsItem> IngredientsItemList);
    public void showIngredientsErrorMsg(String error);
}
