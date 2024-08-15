package com.example.myfoodplaner.search.view.ingredientsearch;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfoodplaner.R;
import com.example.myfoodplaner.model.Dtopresenter.IngredientsItem;
import com.example.myfoodplaner.model.MealRepositoryImpl;
import com.example.myfoodplaner.model.netowark.MealRemoteDataSourceImpl;
import com.example.myfoodplaner.model.netowark.database.MealLocalDataSourceImpl;
import com.example.myfoodplaner.search.presenter.IngredientsPresenterImp;
import com.example.myfoodplaner.search.presenter.IngredientsPresenterView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class IngredientsSearchFragment extends Fragment implements OnIngredientsClickListener, IngredientsView {

    private RecyclerView ingredientsSearchRecycler;
    private EditText ingredientsSearchET;
    private IngredientSearchAdapter ingredientSearchAdapter;
    private IngredientsPresenterView ingredientsPresenterView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingredients_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ingredientsSearchRecycler = view.findViewById(R.id.searchFilterByIngredient_recyclerView);
        ingredientsSearchET = view.findViewById(R.id.search_filter_ingredients_editText);
        ingredientsSearchET.setVisibility(View.VISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        ingredientSearchAdapter = new IngredientSearchAdapter(getContext(), new ArrayList<>(), this);
        ingredientsSearchRecycler.setLayoutManager(layoutManager);
        ingredientsSearchRecycler.setAdapter(ingredientSearchAdapter);

        ingredientsPresenterView = new IngredientsPresenterImp(
                this,
                MealRepositoryImpl.getInstance(
                        MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(requireActivity())
                )
        );

        new Thread(new Runnable() {
            @Override
            public void run() {
                ingredientsPresenterView.getIngredient();
            }
        }).start();



        setupSearchFunctionality();
    }

    private void setupSearchFunctionality() {
        Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                        ingredientsSearchET.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                emitter.onNext(s.toString());
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                            }
                        });
                    }
                })
                .debounce(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        if (ingredientSearchAdapter != null) {
                            ingredientSearchAdapter.filter(s);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(requireActivity(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void onIngredientClick(IngredientsItem ingredient) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", (Serializable) ingredient);
        Navigation.findNavController(requireView()).navigate(R.id.action_searchFragment_to_categoryFragment, bundle);
        Toast.makeText(requireActivity(), "Ingredient clicked: " + ingredient.getStrIngredient(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showIngredientsData(List<IngredientsItem> ingredientsItemList) {
        ingredientSearchAdapter.setList(ingredientsItemList);
        ingredientSearchAdapter.notifyDataSetChanged();
    }

    @Override
    public void showIngredientsErrorMsg(String error) {
        Toast.makeText(requireActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}
