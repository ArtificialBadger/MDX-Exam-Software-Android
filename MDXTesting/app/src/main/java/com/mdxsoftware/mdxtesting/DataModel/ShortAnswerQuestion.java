package com.mdxsoftware.mdxtesting.DataModel;

/**
 * Created by Isaac on 4/7/2015.
 */
public class ShortAnswerQuestion extends Question {

    private String question;

    private String answer;

    public ShortAnswerQuestion(String question, String answer) {
        this.type = QuestionType.ShortAnswer;
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
