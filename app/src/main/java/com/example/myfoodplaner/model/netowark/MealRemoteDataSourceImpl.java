package com.example.myfoodplaner.model.netowark;

import com.example.myfoodplaner.model.Dtopresenter.AreaItem;
import com.example.myfoodplaner.model.Dtopresenter.AreaItemResponse;
import com.example.myfoodplaner.model.Dtopresenter.CategoriesItem;
import com.example.myfoodplaner.model.Dtopresenter.CategoriesItemResponse;
import com.example.myfoodplaner.model.Dtopresenter.IngredientsItem;
import com.example.myfoodplaner.model.Dtopresenter.IngredientsItemResponse;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetailsResponse;
import com.example.myfoodplaner.model.Dtopresenter.MealsDetailResponse;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.MealsItemResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImpl implements MealRemoteDataSource{

    private static  final String   Base_Url="https://www.themealdb.com/api/json/v1/1/";
    private MealService mealService;
   private static Retrofit retrofit= null;
    private static MealRemoteDataSourceImpl mealRemoteDataSource = null;
    List<MealsItem>mealsItemList;
    List<CategoriesItem>categoriesItemList;
    List<IngredientsItem> ingredientsItemResponses;
    List<AreaItem>areaItemList;

    private String err;



    public MealRemoteDataSourceImpl (){
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()  // Use the class-level retrofit variable
                .baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        mealService = retrofit.create(MealService.class);
    }


    public static MealRemoteDataSourceImpl getInstance() {
        if (mealRemoteDataSource == null)
            mealRemoteDataSource = new MealRemoteDataSourceImpl();
        return mealRemoteDataSource;
    }



    @Override
    public void RandomMealNetworkCall(RandomMealItemCallback networkCallback) {
        Single<MealsItemResponse> mealsItemResponseSingle = mealService.getRandomMeal();
        mealsItemResponseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item-> {
                    mealsItemList = item.getRandomMealList();
                    networkCallback.onSuccessResult(mealsItemList);
                },throwable -> {
                    err=throwable.getLocalizedMessage();
                    networkCallback.onFailureResult(err);


                });
    }




    @Override
    public void IngredientsNetworkCall(IngredientsItemCallback ingredientsItemCallback) {
        Single<IngredientsItemResponse> ingredientsItemResponseSingle = mealService.getIngredients();
        ingredientsItemResponseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item-> {
                    ingredientsItemResponses=item.getIngredientList();
                    ingredientsItemCallback.onSuccessResult(ingredientsItemResponses);
                },throwable -> {
                    err=throwable.getLocalizedMessage();
                    ingredientsItemCallback.onFailuresResult(err);
                });
    }

    @Override
    public void AreasNetworkCall(AreaItemCallback areaItemCallback) {
        Single<AreaItemResponse> areaItemResponseSingle = mealService.getAreas();
        areaItemResponseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item-> {
                    areaItemList = item.getAreasList();
                    areaItemCallback.onSuccessResult(areaItemList);
                }, throwable -> {
                            err=throwable.getLocalizedMessage();
                            areaItemCallback.onFailureResult(err);

                });

    }

    @Override
    public void CategoryNetworkCall(CategoryItemCallBack categoryCallBack) {
        Single<CategoriesItemResponse> categoriesItemResponseSingle = mealService.getCategory();
        categoriesItemResponseSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    categoriesItemList=item.getCategories();
                    categoryCallBack.onSuccessResult(categoriesItemList);
                }, throwable -> {
                    err = throwable.getLocalizedMessage();
                    categoryCallBack.onFailureResult(err);
                });
    }

    @Override
    public Single<ListsDetailsResponse> CategoryDetailsNetworkCall(String category) {
        return mealService.getMealsByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ListsDetailsResponse> IngredientDetailsNetworkCall(String category) {
        return mealService.getMealsByIngredient(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<ListsDetailsResponse> AreaDetailsNetworkCall(String category) {
        return mealService.getMealsByArea(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<MealsItemResponse> searchByNameNetworkCall(String name) {

        return mealService.searchByName(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<MealsDetailResponse> getMealDetailNetworkCall(String name) {

        return mealService.getMealById(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
