package com.example.the_dagger.learnit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Categories {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("theme")
    @Expose
    private String theme;
    @SerializedName("quizzes")
    @Expose
    private List<Quiz> quizzes = new ArrayList<Quiz>();
    @SerializedName("scores")
    @Expose
    private List<Integer> scores = new ArrayList<Integer>();
    @SerializedName("solved")
    @Expose
    private String solved;

    public Categories(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getString("id");
        this.name = jsonObject.getString("name");
        this.solved = jsonObject.getString("solved");
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     *
     * @param theme
     * The theme
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     *
     * @return
     * The quizzes
     */
    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    /**
     *
     * @param quizzes
     * The quizzes
     */
    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    /**
     *
     * @return
     * The scores
     */
    public List<Integer> getScores() {
        return scores;
    }

    /**
     *
     * @param scores
     * The scores
     */
    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    /**
     *
     * @return
     * The solved
     */
    public String getSolved() {
        return solved;
    }

    /**
     *
     * @param solved
     * The solved
     */
    public void setSolved(String solved) {
        this.solved = solved;
    }

}