package com.example.myfoodplaner.category.view;


import com.example.myfoodplaner.model.Dtopresenter.ListsDetails;

import java.util.List;

public interface CategoryDetailsView {
    public void showCategoryDetailsData(List<ListsDetails> categoryDetailsList);
    public void showCategoryDetailsErrorMsg(String error);
}
