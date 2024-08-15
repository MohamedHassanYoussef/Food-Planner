package com.example.myfoodplaner.category.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfoodplaner.R;
import com.example.myfoodplaner.category.presenter.CategoryDetailsPresenterImp;
import com.example.myfoodplaner.category.presenter.CategoryDetailsPresenterView;
import com.example.myfoodplaner.model.Dtopresenter.AreaItem;
import com.example.myfoodplaner.model.Dtopresenter.CategoriesItem;
import com.example.myfoodplaner.model.Dtopresenter.IngredientsItem;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetails;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetailsResponse;
import com.example.myfoodplaner.model.MealRepositoryImpl;
import com.example.myfoodplaner.model.netowark.MealRemoteDataSourceImpl;
import com.example.myfoodplaner.model.netowark.database.MealLocalDataSourceImpl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class CategoryFragment extends Fragment  implements CategoryDetailsView ,OnCategoryDetailsClickListener{

    private Context context;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView;
    Single<ListsDetailsResponse> categoryDetailsList;
    private CategoryDetailsPresenterView categoryDetailsPresenterView;
    private LinearLayoutManager linearLayoutManager;
    CardView randomCardView;
   // CategoriesItem category;
    Object item;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_category, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        categoryAdapter = new CategoryAdapter(requireActivity(),new ArrayList<>(),this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        categoryDetailsPresenterView = new CategoryDetailsPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));

       // category = (CategoriesItem) getArguments().getSerializable("category");
        try {

            item = (CategoriesItem) getArguments().getSerializable("category");
        } catch (ClassCastException e) {

            try {

                item = (AreaItem) getArguments().getSerializable("category");
        } catch (ClassCastException error) {
                try {

                    item = (IngredientsItem) getArguments().getSerializable("category");
                } catch (ClassCastException error1) {

                }

        }

        }

      //  Toast.makeText(requireActivity(), "strmeal"+ category.getStrCategory(), Toast.LENGTH_SHORT).show();
        if (item != null && item instanceof CategoriesItem  ) {
            CategoriesItem category = (CategoriesItem) item;
            categoryDetailsList = categoryDetailsPresenterView.getCategoryDetail(category.getStrCategory());
            Log.i("TAG", "s()categoryDetailsList " +categoryDetailsList);
            categoryDetailsList.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                                categoryAdapter.setList(item.getListDetails());
                                recyclerView.setAdapter(categoryAdapter);
                            },
                            throwable -> {
                                Log.i("TAG", "showCategoryDetail: unable to show products because: " + throwable.getMessage());
                            });
        }else if (item != null && item instanceof AreaItem ){
            AreaItem areaItem =(AreaItem) item;
            categoryDetailsList = categoryDetailsPresenterView.getMealsByArea(areaItem.getStrArea());
            Log.i("TAG", "s()categoryDetailsList " +categoryDetailsList);
            categoryDetailsList.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                                categoryAdapter.setList(item.getListDetails());
                                recyclerView.setAdapter(categoryAdapter);
                            },
                            throwable -> {
                                Log.i("TAG", "showCategoryDetail: unable to show products because: " + throwable.getMessage());
                            });

        }else if (item != null && item instanceof IngredientsItem ) {
            IngredientsItem ingredientsItem = (IngredientsItem) item;
            categoryDetailsList = categoryDetailsPresenterView.IngredientDetailsNetworkCall(ingredientsItem.getStrIngredient());


            Log.i("TAG", "s()categoryDetailsList " + categoryDetailsList);
            categoryDetailsList.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                                categoryAdapter.setList(item.getListDetails());
                                recyclerView.setAdapter(categoryAdapter);

                            },
                            throwable -> {
                                Log.i("TAG", "showCategoryDetail: unable to show products because: " + throwable.getMessage());
                            });

        }
    }

    @Override
    public void showCategoryDetailsData(List<ListsDetails> categoryDetailsList) {

    }

    @Override
    public void showCategoryDetailsErrorMsg(String error) {

    }

    @Override
    public void onCategoryClick(ListsDetails listsDetails) {
        Bundle bundle = new Bundle();
       bundle.putSerializable("categoryDetail", listsDetails);
        Navigation.findNavController(requireView()).navigate(R.id.action_categoryFragment_to_listDetailFragment, bundle);
    }
}