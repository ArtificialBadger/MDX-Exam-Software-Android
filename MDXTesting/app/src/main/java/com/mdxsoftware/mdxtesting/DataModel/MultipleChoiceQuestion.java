package com.mdxsoftware.mdxtesting.DataModel;

import java.util.List;

/**
 * Created by Isaac on 4/7/2015.
 */
public class MultipleChoiceQuestion extends Question {

    private String question;

    private List<String> answers;

    private int correctAnswer;

    public MultipleChoiceQuestion(String question, List<String> answers, int correctAnswer) {
        this.type = QuestionType.MultipleChoice;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
