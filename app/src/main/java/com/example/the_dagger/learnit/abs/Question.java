package com.example.the_dagger.learnit.abs;

/**
 * Created by the-dagger on 1/10/16.
 */

public abstract class Question {

    public String mQuestionType;
    public String mQuestion;



    public String getQuestionType() {
        return mQuestionType;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public abstract boolean isAnswerCorrect();
    public abstract void display();
}
