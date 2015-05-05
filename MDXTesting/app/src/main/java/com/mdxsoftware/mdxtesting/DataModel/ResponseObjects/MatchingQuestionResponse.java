package com.mdxsoftware.mdxtesting.DataModel.ResponseObjects;

import com.mdxsoftware.mdxtesting.DataModel.QuestionType;

import java.util.List;
import java.util.Map;

/**
 * Created by isaac on 5/5/2015.
 */
public class MatchingQuestionResponse {

    private String Question;

    private String QuestionGuid;

    private QuestionType Type;

    private List<String> Choices;

    private List<String> Answers;

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getQuestionGuid() {
        return QuestionGuid;
    }

    public void setQuestionGuid(String questionGuid) {
        QuestionGuid = questionGuid;
    }

    public QuestionType getType() {
        return Type;
    }

    public void setType(QuestionType type) {
        Type = type;
    }

    public List<String> getChoices() {
        return Choices;
    }

    public void setChoices(List<String> choices) {
        Choices = choices;
    }

    public List<String> getAnswers() {
        return Answers;
    }

    public void setAnswers(List<String> answers) {
        Answers = answers;
    }
}
