package com.example.the_dagger.learnit.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.the_dagger.learnit.R;
import com.example.the_dagger.learnit.adapter.CategoryAdapter;
import com.example.the_dagger.learnit.model.Categories;
import com.example.the_dagger.learnit.model.SingleChoiceQuestion;
import com.example.the_dagger.learnit.utility.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.the_dagger.learnit.R.raw.questions;

public class MainActivity extends AppCompatActivity {

    JSONObject singleCategory;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_wallet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_wallet:
                Intent intent = new Intent(this,WalletActivity.class);
                startActivity(intent);
                // Set the text color to red
                return true;
            case R.id.action_logout:
                // Set the text color to green
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Do you want to log out ?");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = MainActivity.this.getSharedPreferences(Constants.TAG_SHARED_PREF, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("access_token");
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<Categories> categoryList = getCategories();
        Log.e("CategoryListSIze", String.valueOf(categoryList.size()));
        ArrayList<SingleChoiceQuestion> singleChoiceQuestionList = new ArrayList<>();
        Set<SingleChoiceQuestion> singleChoiceQuestionsSet = new LinkedHashSet<>();
        for(int i=0;i<categoryList.size();i++){
            for(int j=0;j<categoryList.get(i).getQuizzes().size();j++){
                if(singleChoiceQuestionsSet!=null || singleChoiceQuestionsSet.size()>0){
                    if(!singleChoiceQuestionsSet.equals(categoryList.get(i).getQuizzes().get(j))){
                        singleChoiceQuestionList.add(categoryList.get(i).getQuizzes().get(j));
                        Log.e("Quizzes",categoryList.get(i).getQuizzes().get(j).getQuestion());
                    }
                }
                singleChoiceQuestionsSet.add(categoryList.get(i).getQuizzes().get(j));
            }
        }
//        getQuizzes(singleCategory);
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList,this,singleChoiceQuestionList);
        RecyclerView categoryRv = (RecyclerView) findViewById(R.id.categoryRv);
        categoryRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        categoryRv.setAdapter(categoryAdapter);
        Log.e("List", String.valueOf(categoryList.size()));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private List<Categories> getCategories() {
        JSONArray jsonArray;
        List<Categories> categories = new ArrayList<>();
        List<SingleChoiceQuestion> singleChoiceQuestions = new ArrayList<>();
        int j;
        try {
            InputStream is = getResources().openRawResource(questions);
            jsonArray = new JSONArray(convertStreamToString(is));
            for (j = 0; j < jsonArray.length(); j++) {
                singleCategory = jsonArray.getJSONObject(j);
                Log.e("Categories", String.valueOf(singleCategory.get("name")));
                categories.add(new Categories(singleCategory));
                for(int i=0;i<singleCategory.getJSONArray("quizzes").length();i++) {
                    if (singleCategory.getJSONArray("quizzes").getJSONObject(i).getString("type").equals("single-select"))
                        singleChoiceQuestions.add(new SingleChoiceQuestion(singleCategory.getJSONArray("quizzes").getJSONObject(i)));
                }categories.get(j).setQuizzes(singleChoiceQuestions);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categories;
        // Now iterate through the list
    }

//    private ArrayList<SingleChoiceQuestion> getQuizzes(JSONObject singleCategory) {
//        int i;
//        ArrayList<SingleChoiceQuestion> singleChoiceQuestionList = new ArrayList<>();
//        JSONArray quizzes = null;
//        try {
//
//            quizzes = singleCategory.getJSONArray("quizzes");
//
//            for (i = 0; i < quizzes.length(); i++) {
////                if (quizzes.getJSONObject(i).getString("type").equals("multi-select")) {
////                    questionList.add(new MultipleChoiceQuestion(quizzes.getJSONObject(i)));
//////                        Log.e("QuizMulti", quizzes.getJSONObject(i).getString("question"));
////                } else
//            if (quizzes.getJSONObject(i).getString("type").equals("single-select-item")) {
//                singleChoiceQuestionList.add(new SingleChoiceQuestion(quizzes.getJSONObject(i)));
//            }
////                } else if (quizzes.getJSONObject(i).getString("type").equals("fill-blank")) {
////                    questionList.add(new InputTextQuestion(quizzes.getJSONObject(i)));
//////                Log.e("QuizSelect", quizzes.getJSONObject(i).getString("question"));
////                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return singleChoiceQuestionList;
//    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
