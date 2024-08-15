package com.example.myfoodplaner.search.view.countrysearch;


import com.example.myfoodplaner.model.Dtopresenter.AreaItem;

import java.util.List;

public interface AreasView {
    public void showAreasData(List<AreaItem> AreaItemList);
    public void showAreasErrorMsg(String error);
}
