package com.example.myfoodplaner.search.view.categorysearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfoodplaner.R;
import com.example.myfoodplaner.model.Dtopresenter.CategoriesItem;

import java.util.ArrayList;
import java.util.List;

public class CategoriesSearchAdapter extends RecyclerView.Adapter<CategoriesSearchAdapter.ViewHolder> {
    private Context context;
    private List<CategoriesItem> categoriesItemList;
    private List<CategoriesItem> filteredList;
    private final OnCategoryClicked onCategoryClicked;

    public CategoriesSearchAdapter(Context context, List<CategoriesItem> categoriesItemList, OnCategoryClicked onCategoryClicked) {
        this.context = context;
        this.categoriesItemList = categoriesItemList;
        this.onCategoryClicked = onCategoryClicked;
        this.filteredList = new ArrayList<>(categoriesItemList);
    }



    @NonNull
    @Override
    public CategoriesSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.category_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoriesItem item = filteredList.get(position);
        String name = item.getStrCategory();
        holder.categoryTextView.setText(name);
        Glide.with(context)
                .load(item.getStrCategoryThumb())
                .into(holder.categoryImage);

        holder.constraintLayout.setOnClickListener(v -> onCategoryClicked.onCategoryClick(item));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void setCategoriesSearchList(List<CategoriesItem> categoriesItemList) {
        this.categoriesItemList = categoriesItemList;
        this.filteredList = new ArrayList<>(categoriesItemList);
        notifyDataSetChanged();
    }

    public void setList(List<CategoriesItem> categoriesItemList) {
        this.categoriesItemList = categoriesItemList;
        this.filteredList = new ArrayList<>(categoriesItemList);
        notifyDataSetChanged();
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(categoriesItemList);
        } else {
            for (CategoriesItem item : categoriesItemList) {
                if (item.getStrCategory().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTextView;
        private ImageView categoryImage;
        private ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.imageItemCategory);
            categoryTextView = itemView.findViewById(R.id.tv_ItemCategory);
            constraintLayout = itemView.findViewById(R.id.category_item);
        }
    }


}
