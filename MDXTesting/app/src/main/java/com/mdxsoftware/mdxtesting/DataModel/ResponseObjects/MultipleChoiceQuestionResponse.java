package com.mdxsoftware.mdxtesting.DataModel.ResponseObjects;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mdxsoftware.mdxtesting.DataModel.QuestionType;

import java.util.List;

/**
 * Created by isaac on 4/28/2015.
 */
@JsonNaming(value = PropertyNamingStrategy.PascalCaseStrategy.class)
public class MultipleChoiceQuestionResponse {

    private String Question;

    private String QuestionGuid;

    private List<String> Options;

    private String SuggestedAnswer;

    private QuestionType Type;

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

    public List<String> getOptions() {
        return Options;
    }

    public void setOptions(List<String> options) {
        Options = options;
    }

    public String getSuggestedAnswer() {
        return SuggestedAnswer;
    }

    public void setSuggestedAnswer(String suggestedAnswer) {
        SuggestedAnswer = suggestedAnswer;
    }

    public QuestionType getType() {
        return Type;
    }

    public void setType(QuestionType type) {
        Type = type;
    }
}
