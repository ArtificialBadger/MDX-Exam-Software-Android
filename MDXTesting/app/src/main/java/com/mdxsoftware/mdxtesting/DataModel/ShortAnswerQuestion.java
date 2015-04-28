package com.mdxsoftware.mdxtesting.DataModel;

/**
 * Created by Isaac on 4/7/2015.
 */
public class ShortAnswerQuestion extends Question {

    private String question;

    private String sampleAnswer;

    private String enteredAnswer;

    public ShortAnswerQuestion(String question, String sampleAnswer) {
        this.type = QuestionType.ShortAnswer;
        this.question = question;
        this.sampleAnswer = sampleAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getSampleAnswer() {
        return sampleAnswer;
    }

    public String getEnteredAnswer() {
        return enteredAnswer;
    }

    public void setEnteredAnswer(String enteredAnswer) {
        this.enteredAnswer = enteredAnswer;
    }
}
