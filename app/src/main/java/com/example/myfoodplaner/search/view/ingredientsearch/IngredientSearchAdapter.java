package com.example.myfoodplaner.search.view.ingredientsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfoodplaner.R;
import com.example.myfoodplaner.model.Dtopresenter.IngredientsItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class IngredientSearchAdapter extends RecyclerView.Adapter<IngredientSearchAdapter.ViewHolder> {
    private Context context;
    private List<IngredientsItem> ingredientsItemList;
    private List<IngredientsItem> filteredIngredientsItemList;
    private OnIngredientsClickListener onIngredientsClickListener;

    public IngredientSearchAdapter(Context context, List<IngredientsItem> ingredientsItemList, OnIngredientsClickListener onIngredientsClickListener) {
        this.context = context;
        this.ingredientsItemList = ingredientsItemList;
        this.filteredIngredientsItemList = new ArrayList<>(ingredientsItemList);
        this.onIngredientsClickListener = onIngredientsClickListener;
    }

    public IngredientSearchAdapter(Context context) {
        this.context = context;
        this.ingredientsItemList = new ArrayList<>();
        this.filteredIngredientsItemList = new ArrayList<>();
    }

    public void setList(List<IngredientsItem> ingredientsItemList) {
        this.ingredientsItemList = ingredientsItemList;
        this.filteredIngredientsItemList = new ArrayList<>(ingredientsItemList);
        notifyDataSetChanged();
    }

    public void filter(String query) {
        filteredIngredientsItemList.clear();
        if (query.isEmpty()) {
            filteredIngredientsItemList.addAll(ingredientsItemList);
        } else {
            for (IngredientsItem item : ingredientsItemList) {
                if (item.getStrIngredient().toLowerCase().contains(query.toLowerCase())) {
                    filteredIngredientsItemList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ingredients_item_meal_details, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IngredientsItem ingredientsItem = filteredIngredientsItemList.get(position);
        holder.tvIngridentName.setText(ingredientsItem.getStrIngredient());

        Glide.with(context)
                .load("https://www.themealdb.com/images/ingredients/" + ingredientsItem.getStrIngredient() + "-Small.png")
                .into(holder.ingridentImage);

        holder.constraintLayout.setOnClickListener(v -> {
            if (onIngredientsClickListener != null) {
                onIngredientsClickListener.onIngredientClick(ingredientsItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredIngredientsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ingridentImage;
        private TextView tvIngridentName;
        private ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingridentImage = itemView.findViewById(R.id.imageViewIngredientImageBGItem);
            tvIngridentName = itemView.findViewById(R.id.textViewIngredientNameItem_mealDetails);
            constraintLayout = itemView.findViewById(R.id.ingredint_item);
        }
    }
}
