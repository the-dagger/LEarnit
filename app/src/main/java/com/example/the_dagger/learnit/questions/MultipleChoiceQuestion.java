package com.example.the_dagger.learnit.questions;

import com.example.the_dagger.learnit.abs.Question;
import com.example.the_dagger.learnit.answers.MultipleChoiceAnswer;

import org.json.JSONObject;

/**
 * Created by the-dagger on 1/10/16.
 */

public class MultipleChoiceQuestion extends Question {

    MultipleChoiceAnswer mAnswer, mSelectedChoices;
    public MultipleChoiceQuestion(JSONObject question) {
        mQuestionType = "MultipleChoiceAnswer";
        mQuestion = "";
        mAnswer = new MultipleChoiceAnswer(new int[0]);
        mSelectedChoices = new MultipleChoiceAnswer(new int[0]);
    }

    @Override
    public boolean isAnswerCorrect() {
        // TODO : Implement later
        return false;
    }

    @Override
    public void display() {

    }
}
