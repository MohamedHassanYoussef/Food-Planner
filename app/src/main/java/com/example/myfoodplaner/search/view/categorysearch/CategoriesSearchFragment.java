package com.example.myfoodplaner.search.view.categorysearch;

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
import com.example.myfoodplaner.home.presenter.CategoryMealPresenterImp;
import com.example.myfoodplaner.home.presenter.CategoryMealPresenterView;
import com.example.myfoodplaner.home.view.CategoryMealView;
import com.example.myfoodplaner.model.Dtopresenter.CategoriesItem;
import com.example.myfoodplaner.model.MealRepositoryImpl;
import com.example.myfoodplaner.model.netowark.MealRemoteDataSourceImpl;
import com.example.myfoodplaner.model.netowark.database.MealLocalDataSourceImpl;
import com.example.myfoodplaner.search.presenter.SearchPresenterImplementation;

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

public class CategoriesSearchFragment extends Fragment implements  CategoryMealView ,OnCategoryClicked{
    private RecyclerView categoriesSearchRecycler;
    public EditText categorySearchET;
    CategoriesSearchAdapter categoriesSearchAdapter;

    private CategoryMealPresenterView categoryMealPresenterView;

    public CategoriesSearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoriesSearchRecycler = view.findViewById(R.id.categories_search_recycler);
        categorySearchET = view.findViewById(R.id.search_filter_category_editText);
        categorySearchET.setVisibility(View.VISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        categoriesSearchAdapter = new CategoriesSearchAdapter(getContext(), new ArrayList<>(), this);
        categoriesSearchRecycler.setLayoutManager(layoutManager);
        categoriesSearchRecycler.setAdapter(categoriesSearchAdapter);

        categoryMealPresenterView = new CategoryMealPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));
      //  categoryMealPresenterView.getCategory();

        new Thread(new Runnable() {
            @Override
            public void run() {
                categoryMealPresenterView.getCategory();

            }
        }).start();
        setupSearchFunctionality();
    }

    private void setupSearchFunctionality() {
        Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                        categorySearchET.addTextChangedListener(new TextWatcher() {
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
                        if (categoriesSearchAdapter != null) {
                            categoriesSearchAdapter.filter(s);
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
    public void showCategoryData(List<CategoriesItem> categoriesItemList) {
        categoriesSearchAdapter.setList(categoriesItemList);
        categoriesSearchAdapter.notifyDataSetChanged();
       // Toast.makeText(requireActivity(), "Success: " + categoriesItemList.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMsgCategory(String error) {
        Toast.makeText(requireActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategoryClick(CategoriesItem category) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("category", (Serializable) category);
        Toast.makeText(requireActivity(), "category" + category, Toast.LENGTH_SHORT).show();
        Navigation.findNavController(requireView()).navigate(R.id.action_searchFragment_to_categoryFragment, bundle);
        Toast.makeText(getContext(), "Category clicked: " , Toast.LENGTH_SHORT).show();
    }
}
