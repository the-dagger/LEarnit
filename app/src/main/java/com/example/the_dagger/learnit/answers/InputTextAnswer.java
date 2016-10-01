package com.example.the_dagger.learnit.answers;

import com.example.the_dagger.learnit.abs.Answer;

/**
 * Created by vineet on 1/10/16.
 */
public class InputTextAnswer extends Answer {

    String mAnswer;

    public InputTextAnswer(String answer) {
        this.mAnswer = answer;
    }

    public String getAnswer() {
        return mAnswer;
    }
}
