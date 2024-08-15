package com.example.myfoodplaner.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfoodplaner.R;
import com.example.myfoodplaner.model.Dtopresenter.CategoriesItem;

import java.util.List;

public class MealCategoryAdapter extends RecyclerView.Adapter<MealCategoryAdapter.ViewHolder>{
    private Context context;
    private List<CategoriesItem> categoriesItemList;
    private OnCategoryClickListener categoryClickListener;
    public MealCategoryAdapter(Context context, List<CategoriesItem> categoriesItemList, OnCategoryClickListener categoryClickListener) {
        this.context = context;
        this.categoriesItemList =  categoriesItemList;
        this.categoryClickListener = categoryClickListener;
    }

    public void setCategoryClickListener(OnCategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }

    public MealCategoryAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<CategoriesItem> categoriesItemList) {
        this.categoriesItemList = categoriesItemList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoriesItem categoriesItem = categoriesItemList.get(position);

        holder.txtCategoryName.setText(categoriesItem.getStrCategory());
        Glide.with(context).load(categoriesItem.getStrCategoryThumb()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryClickListener != null) {
                    categoryClickListener.onCategoryClick(categoriesItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView txtCategoryName;

        public ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.imageItemCategory);
            txtCategoryName = itemView.findViewById(R.id.tv_ItemCategory);
        }
    }
}
