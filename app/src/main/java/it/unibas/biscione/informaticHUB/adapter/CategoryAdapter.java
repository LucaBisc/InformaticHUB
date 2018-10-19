package it.unibas.biscione.informaticHUB.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.unibas.biscione.informaticHUB.activity.CategoryCourseActivity;
import it.unibas.biscione.informaticHUB.Application;
import it.unibas.biscione.informaticHUB.R;
import it.unibas.biscione.informaticHUB.model.Category;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<Category> catList;

    public CategoryAdapter(Context context, ArrayList<Category> categoryList) {
        this.catList = categoryList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_category, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }


    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        holder.name.setText(catList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category c = catList.get(position);
                int id = c.getId();
                CategoryCourseActivity categoryCourseActivity = (CategoryCourseActivity) Application.getInstance().getCurrentActivity();
                categoryCourseActivity.showCategoryCourseActivity(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catList.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView name;


        public CategoryViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtCat);
        }
    }
}
