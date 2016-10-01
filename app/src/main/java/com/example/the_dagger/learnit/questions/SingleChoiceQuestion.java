package com.example.the_dagger.learnit.questions;

import com.example.the_dagger.learnit.Answer;
import com.example.the_dagger.learnit.Question;
import com.example.the_dagger.learnit.answers.MultipleChoiceAnswer;
import com.example.the_dagger.learnit.answers.SingleChoiceAnswer;

import org.json.JSONObject;

import java.io.InvalidClassException;

/**
 * Created by the-dagger on 1/10/16.
 */

public class SingleChoiceQuestion extends Question {

    SingleChoiceAnswer mAnswer, mSelectedChoice;

    public SingleChoiceQuestion(JSONObject question) {
        mQuestionType = "SingleChoiceAnswer";
        mQuestion = "";
        mAnswer = new SingleChoiceAnswer(0);
        mSelectedChoice = new SingleChoiceAnswer(-1);
    }

    @Override
    public boolean isAnswerCorrect() {
        return mAnswer.getChoice() == mSelectedChoice.getChoice();
    }

    @Override
    public void display() {

    }
}
