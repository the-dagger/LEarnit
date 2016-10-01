package com.example.the_dagger.learnit.questions;

import com.example.the_dagger.learnit.Answer;
import com.example.the_dagger.learnit.Question;
import com.example.the_dagger.learnit.answers.InputTextAnswer;
import com.example.the_dagger.learnit.answers.MultipleChoiceAnswer;

import org.json.JSONObject;

import java.io.InvalidClassException;

/**
 * Created by the-dagger on 1/10/16.
 */

public class InputTextQuestion extends Question {

    InputTextAnswer mAnswer, mSelectedAnswer;
    public InputTextQuestion(JSONObject question) {
        mQuestionType = "InputTextQuestion";
        mQuestion = "";
        mAnswer = new InputTextAnswer("");
        mSelectedAnswer = new InputTextAnswer("");
    }

    @Override
    public boolean isAnswerCorrect() {
        return mAnswer.getAnswer().equals(mSelectedAnswer.getAnswer());
    }

    @Override
    public void display() {

    }
}
