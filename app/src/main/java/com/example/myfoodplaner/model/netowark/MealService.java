package com.example.myfoodplaner.model.netowark;

import com.example.myfoodplaner.model.Dtopresenter.AreaItemResponse;
import com.example.myfoodplaner.model.Dtopresenter.CategoriesItemResponse;
import com.example.myfoodplaner.model.Dtopresenter.IngredientsItemResponse;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetailsResponse;
import com.example.myfoodplaner.model.Dtopresenter.MealsDetailResponse;
import com.example.myfoodplaner.model.Dtopresenter.MealsItemResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    @GET("random.php")
    public Single<MealsItemResponse> getRandomMeal();

    @GET("categories.php")
    public Single<CategoriesItemResponse>getCategory();

    @GET("list.php?i=list")
    public Single<IngredientsItemResponse>getIngredients();
    @GET("list.php?a=list")
    public Single<AreaItemResponse>getAreas();

    @GET("filter.php")
    public Single<ListsDetailsResponse> getMealsByCategory(@Query("c") String category);
    @GET("filter.php")
    public Single<ListsDetailsResponse> getMealsByIngredient(@Query("i") String category);
    @GET("filter.php")
    public Single<ListsDetailsResponse> getMealsByArea(@Query("a") String category);

    @GET("search.php")
    public Single<MealsItemResponse>searchByName(@Query("s") String mealName);

    @GET("lookup.php")
    public Single<MealsDetailResponse> getMealById(@Query("i") String id);

}
