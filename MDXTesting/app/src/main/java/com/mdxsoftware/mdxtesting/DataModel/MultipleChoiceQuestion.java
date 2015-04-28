package com.mdxsoftware.mdxtesting.DataModel;

import java.util.List;

/**
 * Created by Isaac on 4/7/2015.
 */
public class MultipleChoiceQuestion extends Question {

    private String question;

    private List<String> answers;

    private int correctAnswerIndex;

    private int enteredAnswerIndex;

    public MultipleChoiceQuestion(String question, List<String> answers, int correctAnswerIndex) {
        this.type = QuestionType.MultipleChoice;
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.enteredAnswerIndex = -1;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public int getEnteredAnswerIndex() {
        return enteredAnswerIndex;
    }

    public void setEnteredAnswerIndex(int enteredAnswerIndex) {
        this.enteredAnswerIndex = enteredAnswerIndex;
    }
}
