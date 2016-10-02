package com.example.the_dagger.learnit.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.example.the_dagger.learnit.abs.Question;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by the-dagger on 1/10/16.
 */

public class SingleChoiceQuestion implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("answer")
    @Expose
    private int answer;
    @SerializedName("options")
    @Expose
    private List<String> options = new ArrayList<String>();
    @SerializedName("min")
    @Expose
    private Integer min;
    @SerializedName("max")
    @Expose
    private Integer max;

    private View.OnClickListener requestBtnClickListener;
    /**
     * @return The type
     */

    public SingleChoiceQuestion(Question q) {
        this.question = q.getQuestion();
    }

    protected SingleChoiceQuestion(Parcel in) {
        type = in.readString();
        question = in.readString();
        options = in.createStringArrayList();
    }

    public SingleChoiceQuestion(JSONObject question) {
        try {
            this.question = question.get("question").toString();
            this.answer = question.getInt("answer");
            int i;
            for(i = 0;i<question.getJSONArray("options").length();i++ )
                this.options.add(question.getJSONArray("options").getString(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(question);
        dest.writeStringList(options);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SingleChoiceQuestion> CREATOR = new Creator<SingleChoiceQuestion>() {
        @Override
        public SingleChoiceQuestion createFromParcel(Parcel in) {
            return new SingleChoiceQuestion(in);
        }

        @Override
        public SingleChoiceQuestion[] newArray(int size) {
            return new SingleChoiceQuestion[size];
        }
    };

    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question The question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return The answer
     */
    public int getAnswer() {
        return answer;
    }

    /**
     * @param answer The answer
     */
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    /**
     * @return The options
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * @param options The options
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * @return The min
     */
    public Integer getMin() {
        return min;
    }

    /**
     * @param min The min
     */
    public void setMin(Integer min) {
        this.min = min;
    }

    /**
     * @return The max
     */
    public Integer getMax() {
        return max;
    }

    /**
     * @param max The max
     */
    public void setMax(Integer max) {
        this.max = max;
    }

}