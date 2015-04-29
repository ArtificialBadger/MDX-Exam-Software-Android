package com.mdxsoftware.mdxtesting.DataModel.ResponseObjects;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mdxsoftware.mdxtesting.DataModel.MultipleChoiceQuestion;
import com.mdxsoftware.mdxtesting.DataModel.ShortAnswerQuestion;

import java.util.List;

/**
 * Created by isaac on 4/28/2015.
 */
@JsonNaming(value = PropertyNamingStrategy.PascalCaseStrategy.class)
public class ExamResponse {

    private String ExamTitle;

    private String ExamGuid;

    private long TestDuration;

    private List<ShortAnswerQuestionResponse> ShortAnswerQuestions;

    private List<MultipleChoiceQuestionResponse> MultipleChoiceQuestions;

    public ExamResponse() {}

    public String getExamTitle() {
        return ExamTitle;
    }

    public void setExamTitle(String examTitle) {
        ExamTitle = examTitle;
    }

    public String getExamGuid() {
        return ExamGuid;
    }

    public void setExamGuid(String examGuid) {
        ExamGuid = examGuid;
    }

    public long getTestDuration() {
        return TestDuration;
    }

    public void setTestDuration(long testDuration) {
        TestDuration = testDuration;
    }

    public List<ShortAnswerQuestionResponse> getShortAnswerQuestions() {
        return ShortAnswerQuestions;
    }

    public void setShortAnswerQuestions(List<ShortAnswerQuestionResponse> shortAnswerQuestions) {
        ShortAnswerQuestions = shortAnswerQuestions;
    }

    public List<MultipleChoiceQuestionResponse> getMultipleChoiceQuestions() {
        return MultipleChoiceQuestions;
    }

    public void setMultipleChoiceQuestions(List<MultipleChoiceQuestionResponse> multipleChoiceQuestions) {
        MultipleChoiceQuestions = multipleChoiceQuestions;
    }
}
