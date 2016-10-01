package com.example.the_dagger.learnit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by the-dagger on 1/10/16.
 */

public class Quiz {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private Integer[] answer;
    @SerializedName("options")
    @Expose
    private List<String> options = new ArrayList<String>();
    @SerializedName("min")
    @Expose
    private Integer min;
    @SerializedName("max")
    @Expose
    private Integer max;

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The question
     */
    public String getQuestion() {
        return question;
    }

    /**
     *
     * @param question
     * The question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     *
     * @return
     * The answer
     */
    public Integer[] getAnswer() {
        return answer;
    }

    /**
     *
     * @param answer
     * The answer
     */
    public void setAnswer(Integer[] answer) {
        this.answer = answer;
    }

    /**
     *
     * @return
     * The options
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     *
     * @param options
     * The options
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     *
     * @return
     * The min
     */
    public Integer getMin() {
        return min;
    }

    /**
     *
     * @param min
     * The min
     */
    public void setMin(Integer min) {
        this.min = min;
    }

    /**
     *
     * @return
     * The max
     */
    public Integer getMax() {
        return max;
    }

    /**
     *
     * @param max
     * The max
     */
    public void setMax(Integer max) {
        this.max = max;
    }

}