package com.mdxsoftware.mdxtesting.DataModel.ResponseObjects;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mdxsoftware.mdxtesting.DataModel.QuestionType;

/**
 * Created by isaac on 4/28/2015.
 */
@JsonNaming(value = PropertyNamingStrategy.PascalCaseStrategy.class)
public class ShortAnswerQuestionResponse {

    private String Question;

    private String QuestionGuid;

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

    public QuestionType getType() {
        return Type;
    }

    public void setType(QuestionType type) {
        Type = type;
    }
}
