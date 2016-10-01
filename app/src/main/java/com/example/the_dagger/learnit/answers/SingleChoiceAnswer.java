package com.example.the_dagger.learnit.answers;

import com.example.the_dagger.learnit.abs.Answer;

/**
 * Created by vineet on 1/10/16.
 */
public class SingleChoiceAnswer extends Answer {

    int mChoice;

    public SingleChoiceAnswer(int choice) {
        this.mChoice = choice;
    }

    public int getChoice() {
        return mChoice;
    }
}
