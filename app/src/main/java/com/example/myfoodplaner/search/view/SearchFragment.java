package com.example.myfoodplaner.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;


import com.example.myfoodplaner.R;
import com.example.myfoodplaner.search.view.categorysearch.CategoriesSearchFragment;
import com.example.myfoodplaner.search.view.countrysearch.CountrySearchFragment;
import com.example.myfoodplaner.search.view.ingredientsearch.IngredientsSearchFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class SearchFragment extends Fragment {

    private ChipGroup chipGroup;
    FragmentTransaction fragmentTransaction;
    String fragment ="";
    private CategoriesSearchFragment categoriesSearchFragment;
    private CountrySearchFragment cuisinesSearchFragment;
    private IngredientsSearchFragment ingredientsSearchFragment;

    private String currentFragmentTag = "";
    private FragmentManager fragmentManager;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getActivity().getSupportFragmentManager();

        // Initialize fragments
        categoriesSearchFragment = new CategoriesSearchFragment();
        cuisinesSearchFragment = new CountrySearchFragment();
        ingredientsSearchFragment = new IngredientsSearchFragment();


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chipGroup = view.findViewById(R.id.chip_search_group);


        setupChips();
        setupSearchFunctionality();
    }

    void setupChips()
    {
        for (int i=0; i<chipGroup.getChildCount();i++)
        {
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                       if (chip.getText().toString().equals("Categories")) {

                            fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_search_container, categoriesSearchFragment);
                            fragmentTransaction.commit();
                            fragment="Categories";
                        }else if (chip.getText().toString().equals("Ingredient")) {
                            fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_search_container, ingredientsSearchFragment);
                            fragmentTransaction.commit();
                            fragment="Ingredient";

                        }else if (chip.getText().toString().equals("Countries")) {
                            fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_search_container, cuisinesSearchFragment);
                            fragmentTransaction.commit();
                            fragment = "Countries";
                        }
                    }
                }
            });
            if (chip.getText().toString().equals("Categories")) {
                chip.setChecked(true); // Select "Categories" chip by default
            }
        }
    }


    private void switchFragment(String fragmentName) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (fragmentName) {
            case "Categories":
                fragmentTransaction.replace(R.id.fragment_search_container, categoriesSearchFragment);
                currentFragmentTag = "Categories";
                break;
            case "Ingredient":
                fragmentTransaction.replace(R.id.fragment_search_container, ingredientsSearchFragment);
                currentFragmentTag = "Ingredient";
                break;
            case "Countries":
                fragmentTransaction.replace(R.id.fragment_search_container, cuisinesSearchFragment);
                currentFragmentTag = "Countries";
                break;
        }

        fragmentTransaction.commit();
    }

    private void setupSearchFunctionality() {




    }

//    private void updateSearchQuery(String query) {
//        switch (currentFragmentTag) {
//            case "Categories":
//                if (categoriesSearchFragment != null && categoriesSearchFragment.getSearchInput() != null) {
//                    categoriesSearchFragment.getSearchInput().setText(query);
//                }
//                break;
//            case "Ingredient":
//                if (ingredientsSearchFragment != null && ingredientsSearchFragment.getSearchInput() != null) {
//                    ingredientsSearchFragment.getSearchInput().setText(query);
//                }
//                break;
//            case "Countries":
//                if (cuisinesSearchFragment != null && cuisinesSearchFragment.getSearchInput() != null) {
//                    cuisinesSearchFragment.getSearchInput().setText(query);
//                }
//                break;
//        }
//    }
}
