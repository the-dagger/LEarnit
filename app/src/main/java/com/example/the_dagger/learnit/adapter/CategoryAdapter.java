package com.example.the_dagger.learnit.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.the_dagger.learnit.R;
import com.example.the_dagger.learnit.activity.QuizActivity;
import com.example.the_dagger.learnit.model.Categories;
import com.example.the_dagger.learnit.model.SingleChoiceQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by the-dagger on 1/10/16.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements View.OnClickListener {
    private int position;
    private List<Categories> listCategories;
    ArrayList<SingleChoiceQuestion> singleChoiceQuestionArrayList;
    private final Context context;
    int answer[] = new int[10];
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_category_item,parent,false);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    public CategoryAdapter(List<Categories> categories, Context context, ArrayList<SingleChoiceQuestion> singleChoiceQuestionList){
        this.listCategories = categories;
        this.context = context;
        singleChoiceQuestionArrayList = singleChoiceQuestionList;
        int i = 0;
        while (i<10){
            answer[i] = singleChoiceQuestionList.get(i).getAnswer();
            i++;
        }
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        this.position = holder.getAdapterPosition();
        Categories singleCategory = listCategories.get(holder.getAdapterPosition());
        if(getItemCount() == -1){
            holder.title.setText("No Categories at the moment");
            holder.title.setGravity(View.TEXT_ALIGNMENT_CENTER);
        }
        else{
//            Log.e("Name",singleCategory.getName());
            holder.title.setText(singleCategory.getName());
        }
    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, QuizActivity.class);
//        intent.putExtra("position",position);
        intent.putExtra("singleAdapter", listCategories.get(position));
        intent.putParcelableArrayListExtra("singleChoiceQuestion",singleChoiceQuestionArrayList);
        intent.putExtra("answer",answer);
        context.startActivity(intent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.category_title);
        }
    }

}
