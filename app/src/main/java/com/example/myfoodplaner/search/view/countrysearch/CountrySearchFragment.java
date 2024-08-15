package com.example.myfoodplaner.search.view.countrysearch;

import android.content.Context;
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
import com.example.myfoodplaner.model.Dtopresenter.AreaItem;
import com.example.myfoodplaner.model.MealRepositoryImpl;
import com.example.myfoodplaner.model.netowark.MealRemoteDataSourceImpl;
import com.example.myfoodplaner.model.netowark.database.MealLocalDataSourceImpl;
import com.example.myfoodplaner.search.presenter.AreasPresenterImp;
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

public class CountrySearchFragment extends Fragment implements OnAreaClickListener,AreasView {

    private RecyclerView areaSearchRecycler;
    private EditText areaSearchET;
    private AreaSearchAdapter areaSearchAdapter;
    AreasPresenterImp areasPresenterImp;
    public CountrySearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        areaSearchRecycler = view.findViewById(R.id.country_search_recycler);
        areaSearchET = view.findViewById(R.id.search_filter_country_editText);
        areaSearchET.setVisibility(View.VISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        areaSearchAdapter = new AreaSearchAdapter(getContext(), new ArrayList<>(), this);
        areaSearchRecycler.setLayoutManager(layoutManager);
        areaSearchRecycler.setAdapter(areaSearchAdapter);


        areasPresenterImp = new AreasPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));

        new Thread(new Runnable() {
            @Override
            public void run() {
                areasPresenterImp.getArea();

            }
        }).start();
        setupSearchFunctionality();



    }

    private void setupSearchFunctionality() {
        Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                        areaSearchET.addTextChangedListener(new TextWatcher() {
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
                        if (areaSearchAdapter != null) {
                            areaSearchAdapter.filter(s);
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
    public void onAreaClick(AreaItem area) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("category", (Serializable) area);

        Navigation.findNavController(requireView()).navigate(R.id.action_searchFragment_to_categoryFragment, bundle);

        Toast.makeText(requireActivity(), "Area clicked: " + area.getStrArea(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showAreasData(List<AreaItem> AreaItemList) {
        areaSearchAdapter.setList(AreaItemList);
        areaSearchAdapter.notifyDataSetChanged();

    }

    @Override
    public void showAreasErrorMsg(String error) {

    }
}
