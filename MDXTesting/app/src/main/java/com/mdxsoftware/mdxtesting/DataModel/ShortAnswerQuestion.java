package com.mdxsoftware.mdxtesting.DataModel;

import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.ShortAnswerQuestionResponse;

/**
 * Created by Isaac on 4/7/2015.
 */
public class ShortAnswerQuestion extends Question {

    private String question;

    private String enteredAnswer;

    public ShortAnswerQuestion(ShortAnswerQuestionResponse shortAnswerQuestionResponse)
    {
        this.type = QuestionType.ShortAnswer;
        this.question = shortAnswerQuestionResponse.getQuestion();
    }


    public ShortAnswerQuestion(String question) {
        this.type = QuestionType.ShortAnswer;
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getEnteredAnswer() {
        return enteredAnswer;
    }

    public void setEnteredAnswer(String enteredAnswer) {
        this.enteredAnswer = enteredAnswer;
    }
}
