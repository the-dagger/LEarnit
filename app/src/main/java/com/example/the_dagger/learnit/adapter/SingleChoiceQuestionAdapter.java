package com.example.the_dagger.learnit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.the_dagger.learnit.R;
import com.example.the_dagger.learnit.model.SingleChoiceQuestion;

import java.util.ArrayList;

/**
 * Created by the-dagger on 2/10/16.
 */

public class SingleChoiceQuestionAdapter extends RecyclerView.Adapter<SingleChoiceQuestionAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<SingleChoiceQuestion> singleChoiceQuestionArrayList;

    public int index = -1;
    @Override
    public SingleChoiceQuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_single_choice,parent,false);
        return new ViewHolder(itemView);
    }

    public SingleChoiceQuestionAdapter(Context context, ArrayList<SingleChoiceQuestion> singleChoiceQuestionList){
        this.singleChoiceQuestionArrayList = singleChoiceQuestionList;
    }

    @Override
    public void onBindViewHolder(SingleChoiceQuestionAdapter.ViewHolder holder, int position) {
        int position1 = holder.getAdapterPosition();
        SingleChoiceQuestion singleChoiceQuestion = singleChoiceQuestionArrayList.get(holder.getAdapterPosition());
        if(getItemCount() == -1){
            holder.question.setText("No Questions at the moment");
        }
        else{
//            Log.e("Name",singleCategory.getName());
            holder.question.setText(singleChoiceQuestion.getQuestion());
            holder.rb0.setText(singleChoiceQuestion.getOptions().get(0));
            holder.rb1.setText(singleChoiceQuestion.getOptions().get(1));
            holder.rb2.setText(singleChoiceQuestion.getOptions().get(2));
            holder.rb3.setText(singleChoiceQuestion.getOptions().get(3));

        }
    }

    @Override
    public int getItemCount() {
        return singleChoiceQuestionArrayList.size();
    }

    @Override
    public void onClick(View v) {
//        recyclerView = (RecyclerView) v.findViewById(R.id.quizRv);
//        recyclerView.smoothScrollToPosition(position+1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView question;
        public RadioGroup group;
        public RadioButton rb0;
        public RadioButton rb1;
        public RadioButton rb2;
        public RadioButton rb3;
        public ViewHolder(View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.questionTitle);
            rb0 = (RadioButton) itemView.findViewById(R.id.singleChoice0);
            rb1 = (RadioButton) itemView.findViewById(R.id.singleChoice1);
            rb2 = (RadioButton) itemView.findViewById(R.id.singleChoice2);
            rb3 = (RadioButton) itemView.findViewById(R.id.singleChoice3);

            group = (RadioGroup) itemView.findViewById(R.id.radioGroup);
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    View radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                    index = radioGroup.indexOfChild(radioButton);
//                    Log.e("Index", String.valueOf(index) + " and " + String.valueOf(i));
                }
            });
        }
    }
}
