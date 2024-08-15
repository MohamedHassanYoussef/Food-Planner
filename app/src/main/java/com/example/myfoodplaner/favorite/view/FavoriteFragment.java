package com.example.myfoodplaner.favorite.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfoodplaner.R;
import com.example.myfoodplaner.favorite.presenter.FavPresenterImplementation;
import com.example.myfoodplaner.favorite.presenter.FavouriteMealPresenterView;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetails;
import com.example.myfoodplaner.model.MealRepositoryImpl;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.netowark.database.MealLocalDataSourceImpl;
import com.example.myfoodplaner.model.netowark.MealRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteFragment extends Fragment implements FavouriteMealView, OnFavoriteMealClickListener {

    private RecyclerView favMealRecyclerView;
    private FavAdapter favMealsAdapter;
    private FavouriteMealPresenterView favouriteMealPresenterView;
    private FrameLayout frameLayout;
    private Single<List<MealsItem>> favMealsList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favMealRecyclerView = view.findViewById(R.id.favoriteMeals_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        favMealRecyclerView.setLayoutManager(gridLayoutManager);
        favMealsAdapter = new FavAdapter(getContext(), new ArrayList<>(), this);
        favMealRecyclerView.setAdapter(favMealsAdapter);


        favouriteMealPresenterView = FavPresenterImplementation.getInstance(
                this,
                MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity()))
        );

        showList();
    }

    @Override
    public void showList() {

        favouriteMealPresenterView.getFavMealList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealsItemList -> {

                    favMealsAdapter.setFavMealsList(mealsItemList);
                    favMealsAdapter.notifyDataSetChanged();
                }, throwable -> {
                    Toast.makeText(getContext(), "Unable to load favorite meals: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });


    }


    @Override
    public void onDeleteItemClick(MealsItem meal) {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Meal")
                .setMessage("Are you sure you want to delete this meal?")
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                    favouriteMealPresenterView.deleteMeal(meal);
                    Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    showList(); // Refresh list after deletion
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    }

