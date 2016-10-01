package com.example.the_dagger.learnit.answers;

import com.example.the_dagger.learnit.abs.Answer;

/**
 * Created by vineet on 1/10/16.
 */
public class MultipleChoiceAnswer extends Answer {

    int[] mChoices;

    public MultipleChoiceAnswer(int[] choices) {
        this.mChoices = choices;
    }

    public int[] getChoices() {
        return mChoices;
    }
}
