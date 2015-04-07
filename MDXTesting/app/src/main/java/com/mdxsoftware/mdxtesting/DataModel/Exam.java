package com.mdxsoftware.mdxtesting.DataModel;

import java.io.Serializable;
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

    public Exam(String examTitle, String examID, List<Question> questionList, Date validFrom, Date validTo) {
        this.examTitle = examTitle;
        this.examID = examID;
        this.questionList = questionList;
        this.validFrom = validFrom;
        this.validTo = validTo;
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
}
