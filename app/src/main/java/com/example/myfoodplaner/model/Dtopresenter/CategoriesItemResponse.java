package com.example.myfoodplaner.model.Dtopresenter;

import java.util.List;

import com.example.myfoodplaner.model.Dtopresenter.CategoriesItem;
import com.google.gson.annotations.SerializedName;

public class CategoriesItemResponse {

	@SerializedName("categories")
	private List<CategoriesItem> categories;

	public List<CategoriesItem> getCategories(){
		return categories;
	}
}