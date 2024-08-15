package com.example.myfoodplaner.mealdetail.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myfoodplaner.R;
import com.example.myfoodplaner.home.view.HomeActivity;
import com.example.myfoodplaner.mealdetail.presenter.MealDetailPresenterImp;
import com.example.myfoodplaner.mealdetail.presenter.MealDetailPresenterView;
import com.example.myfoodplaner.model.Dtopresenter.GeneratingIngridentsArrayLists;
import com.example.myfoodplaner.model.Dtopresenter.ListsDetails;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.Dtopresenter.WeekPlan;
import com.example.myfoodplaner.model.MealRepositoryImpl;
import com.example.myfoodplaner.model.MealRepositoryView;
import com.example.myfoodplaner.model.netowark.MealRemoteDataSourceImpl;
import com.example.myfoodplaner.model.netowark.database.MealLocalDataSourceImpl;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.rxjava3.disposables.Disposable;


public class MealDetailsFragment extends Fragment implements MealDetailView ,OnDetailItemClickListener{


    public static final String TAG = "TAG";


    private ImageView itemImage;
    private TextView tvItemName;
    private TextView tvItemCountry;
    private TextView tvItemCategory;
    private TextView tvProcedures;
    private ImageView addToFavImage;
    private MealDetailPresenterView mealDetailPresenterView;
    private MealsItem mealsItem;
    private WeekPlan weekPlan;
    private ImageView addToCalender;
    private MealsItem mealsItemCategory;
    private Context context;
    private IngridentsAdapter ingridentsAdapter;
    private RecyclerView recyclerView;
    private List<MealsItem> mealsItemList;
    private ImageView itemIngridentImage;
    private TextView itemIngridentName;
    private LinearLayoutManager linearLayoutManager;
    private CardView ingridentCardView;
    private YouTubePlayerView youTubePlayerView;
    private MealRepositoryView mealRepositoryView;
    private OnDetailItemClickListener onDetailItemClickListener;
    private HomeActivity homeActivity;
    private ListsDetails listsDetails;
    private ListsDetails listAreaDetails;
    private ListsDetails listingredientDetails;
    private MealsItem searchByName;
    private MealsItem favMeal;
    private WeekPlan weekPlanMeal;


    MealDetailPresenterView mealsDetailsPresenter;
    IngridentsAdapter ingredientsAdapter;

    CircleImageView imageViewADDToWeekPlanner;
    YouTubePlayerView yt;
    RecyclerView recyclerViewIngredientsItemDetails;
    Disposable disposable;



    TextView txtViewMealNameItemDetails;
    TextView textViewMealCateItemDetails;
    TextView textViewMealCountryItemDetails;
    TextView textViewProcedures;
    ImageView mealImage;
    CircleImageView imageViewAddToFavITemDetails;
    View view;
    Button addToPhoneCalender;
    boolean isFav ;

    public MealDetailsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewIngredientsItemDetails);
        tvItemName = view.findViewById(R.id.txtViewMealNameItemDetails);
        tvItemCountry = view.findViewById(R.id.textViewMealCountryItemDetails);
        tvItemCategory = view.findViewById(R.id.textViewMealCateItemDetails);
        itemImage = view.findViewById(R.id.mealImage);
        addToFavImage = view.findViewById(R.id.imageViewAddToFavITemDetails);
        tvProcedures = view.findViewById(R.id.textViewProcedures);
        youTubePlayerView = view.findViewById(R.id.ytPlayer);
        addToCalender= view .findViewById(R.id.imageViewAddToCalendarItemDetails);
        mealsItem = (MealsItem) getArguments().getSerializable("item");
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ingridentsAdapter =new IngridentsAdapter(requireContext(),new ArrayList<>());
        recyclerView.setAdapter(ingridentsAdapter);

        mealDetailPresenterView = new MealDetailPresenterImp(this, MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(requireActivity())));


        addToFavImage.setOnClickListener(v -> {
                mealDetailPresenterView.addToFav(mealsItem);
            Toast.makeText(getActivity(), "Add To Favorite Successfully", Toast.LENGTH_SHORT).show();

        });
        addToCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            String date = getDateString(year,month,dayOfMonth);
                            mealsItem = (MealsItem) getArguments().getSerializable("item");
                            WeekPlan weekPlan = new WeekPlan();
                            weekPlan.setDate(date);
                            weekPlan.setMealData(mealsItem);
                            addToCalendar(weekPlan);
                        }
                        private void addToCalendar(WeekPlan date) {
                            mealDetailPresenterView.SetClickedItemData(date);
                            // date.setDate(selectedDate);
                        }
                    }, year, month, dayOfMonth);
                    datePickerDialog.show();
                //}
            }
        });

        // Load YouTube video
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                loadVideo(youTubePlayer);
            }
        });


        showItemDetailData(mealsItem);

        return view;
    }


    public void showItemDetailData(MealsItem mealsItem) {
        tvItemName.setText(mealsItem.getStrMeal());
        tvItemCountry.setText(mealsItem.getStrArea());
        tvItemCategory.setText(mealsItem.getStrCategory());
        tvProcedures.setText(mealsItem.getStrInstructions());
        Glide.with(requireActivity()).load(mealsItem.getStrMealThumb()).into(itemImage);

        ingridentsAdapter.setMealItemDetailList(GeneratingIngridentsArrayLists.getIngridentsArray(mealsItem));
    }

    @Override
    public void addToFav(MealsItem mealsItem) {
        mealRepositoryView.insertMeal(mealsItem);
    }

    @Override
    public void showItemDetailErrorMsg(String error) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show();
    }

    private String getVideoId(String videoUrl) {
        String videoId = null;
        String[] urlParts = videoUrl.split("v=");
        if (urlParts.length > 1) {
            videoId = urlParts[1];
        }
        return videoId;
    }

    private void loadVideo(@NonNull YouTubePlayer youTubePlayer) {
        String videoUrl = mealsItem.getStrYoutube();
        if (videoUrl != null && !videoUrl.isEmpty()) {
            youTubePlayer.loadVideo(getVideoId(videoUrl), 0);
        }
    }


    public static String getDateString(int year, int month, int dayOfMonth){

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,dayOfMonth);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return  format.format(calendar.getTime());
    }

    @Override
    public void onItemClickListener(MealsItem mealsItem) {

    }
}