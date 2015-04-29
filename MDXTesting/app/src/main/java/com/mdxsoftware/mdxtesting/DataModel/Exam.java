package com.mdxsoftware.mdxtesting.DataModel;

import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.ExamResponse;
import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.MultipleChoiceQuestionResponse;
import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.ShortAnswerQuestionResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Isaac on 4/7/2015.
 */
public class Exam implements Serializable{

    private String examTitle;

    private String examID;

    private List<Question> questionList;

    private Date validFrom;

    private Date validTo;

    private long testDuration;

    public Exam(ExamResponse examResponse)
    {
        List<Question> questions = new ArrayList<Question>();

        for (MultipleChoiceQuestionResponse multipleChoiceQuestionResponse : examResponse.getMultipleChoiceQuestions())
        {
            questions.add(new MultipleChoiceQuestion(multipleChoiceQuestionResponse));
        }

        for (ShortAnswerQuestionResponse shortAnswerQuestionResponse : examResponse.getShortAnswerQuestions())
        {
            questions.add(new ShortAnswerQuestion(shortAnswerQuestionResponse));
        }

        this.examTitle = examResponse.getExamTitle();
        this.examID = examResponse.getExamGuid();
        this.testDuration = examResponse.getTestDuration();
    }


    public Exam(String examTitle, String examID, List<Question> questionList, Date validFrom, Date validTo, long testDuration) {
        this.examTitle = examTitle;
        this.examID = examID;
        this.questionList = questionList;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.testDuration = testDuration;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public long getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(long testDuration) {
        this.testDuration = testDuration;
    }
}
