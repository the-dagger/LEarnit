package com.example.the_dagger.learnit.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.the_dagger.learnit.R;
import com.example.the_dagger.learnit.abs.Question;
import com.example.the_dagger.learnit.adapter.CategoryAdapter;
import com.example.the_dagger.learnit.model.Categories;
import com.example.the_dagger.learnit.questions.MultipleChoiceQuestion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.the_dagger.learnit.R.raw.questions;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<Categories> categoryList = getCategories();
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList,this);
        RecyclerView categoryRv = (RecyclerView) findViewById(R.id.categoryRv);
        categoryRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
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

    private List<Categories> getCategories () {
        JSONArray jsonArray;
        List<Categories> categories = new ArrayList<>();
        int j;
        try {
            InputStream is = getResources().openRawResource(questions);
            jsonArray = new JSONArray(convertStreamToString(is));
//            Log.e("jsonArray",jsonArray.toString(4));
            for(j = 0;j<jsonArray.length();j++) {
                JSONObject singleCategory = jsonArray.getJSONObject(j);
                Log.e("Categories", String.valueOf(singleCategory.get("name")));
                categories.add(new Categories(singleCategory));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return categories;
        // Now iterate through the list
    }

    private List<Question> getQuizzes(JSONObject singleCategory) throws JSONException {
        int i;
        List<Question> questions = new ArrayList<>();
        JSONArray quizzes = singleCategory.getJSONArray("quizzes");
        for (i = 0; i < quizzes.length(); i++) {
            if (quizzes.getJSONObject(i).getString("type").equals("multi-select")) {
                questions.add(new MultipleChoiceQuestion(quizzes.getJSONObject(i)));
//                        Log.e("QuizMulti", quizzes.getJSONObject(i).getString("question"));
            } else if (quizzes.getJSONObject(i).getString("type").equals("single-select-item")) {
                questions.add(new MultipleChoiceQuestion(quizzes.getJSONObject(i)));
//                        Log.e("QuizSelect", quizzes.getJSONObject(i).getString("question"));
            } else if (quizzes.getJSONObject(i).getString("type").equals("fill-blank")) {
                questions.add(new MultipleChoiceQuestion(quizzes.getJSONObject(i)));
//                Log.e("QuizSelect", quizzes.getJSONObject(i).getString("question"));
            }
        }
        return questions;
    }

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
