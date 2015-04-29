package com.mdxsoftware.mdxtesting.DataModel;

import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.MultipleChoiceQuestionResponse;

import java.util.List;

/**
 * Created by Isaac on 4/7/2015.
 */
public class MultipleChoiceQuestion extends Question {

    private String question;

    private List<String> answers;

    private String correctAnswer;

    private int enteredAnswerIndex;

    public MultipleChoiceQuestion(MultipleChoiceQuestionResponse multipleChoiceQuestionResponse) {
        this.type = QuestionType.MultipleChoice;
        this.question = multipleChoiceQuestionResponse.getQuestion();
        this.answers = multipleChoiceQuestionResponse.getOptions();
        this.correctAnswer = multipleChoiceQuestionResponse.getSuggestedAnswer();
        this.enteredAnswerIndex = -1;
    }

    public MultipleChoiceQuestion(String question, List<String> answers, String correctAnswerIndex) {
        this.type = QuestionType.MultipleChoice;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswerIndex;
        this.enteredAnswerIndex = -1;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getEnteredAnswerIndex() {
        return enteredAnswerIndex;
    }

    public void setEnteredAnswerIndex(int enteredAnswerIndex) {
        this.enteredAnswerIndex = enteredAnswerIndex;
    }
}
