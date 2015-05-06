package com.mdxsoftware.mdxtesting.DataModel;

import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.ExamResponse;
import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.MatchingQuestionResponse;
import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.MultipleChoiceQuestionResponse;
import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.ShortAnswerQuestionResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Isaac on 4/7/2015.
 */
public class Exam implements Serializable{

    private String examTitle;

    private String examID;

    private List<Question> questionList;

    private long testDuration;

    public Exam(ExamResponse examResponse)
    {
        if (examResponse != null) {
            List<Question> questions = new ArrayList<Question>();


            for (MultipleChoiceQuestionResponse multipleChoiceQuestionResponse : examResponse.getMultipleChoiceQuestions()) {
                questions.add(new MultipleChoiceQuestion(multipleChoiceQuestionResponse));
            }

            for (ShortAnswerQuestionResponse shortAnswerQuestionResponse : examResponse.getShortAnswerQuestions()) {
                questions.add(new ShortAnswerQuestion(shortAnswerQuestionResponse));
            }

            for (MatchingQuestionResponse matchingQuestionResponse : examResponse.getMatchingQuestions()) {
                questions.add(new MatchingQuestion(matchingQuestionResponse));
            }


            this.examTitle = examResponse.getExamTitle();
            this.examID = examResponse.getExamGuid();
            this.testDuration = examResponse.getTestDuration();
            this.questionList = questions;
        }
    }

    public Exam(String examTitle, String examID, List<Question> questionList, long testDuration) {
        this.examTitle = examTitle;
        this.examID = examID;
        this.questionList = questionList;
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

    public long getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(long testDuration) {
        this.testDuration = testDuration;
    }
}
