package com.mdxsoftware.mdxtesting.DataModel;

import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.MatchingQuestionResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Isaac on 4/7/2015.
 */
public class MatchingQuestion extends Question {

    private String question;

    private String questionGuid;

    private List<String> choices;

    private List<String> answers;

    private Map<String, String> enteredPairs;

    public MatchingQuestion(MatchingQuestionResponse matchingQuestionResponse)
    {
        this.type = matchingQuestionResponse.getType();
        this.question = matchingQuestionResponse.getQuestion();
        this.questionGuid = matchingQuestionResponse.getQuestionGuid();
        this.choices = matchingQuestionResponse.getChoices();
        this.answers = matchingQuestionResponse.getAnswers();
        this.enteredPairs = new LinkedHashMap<>();
    }


    public MatchingQuestion(String question, List<String> choices, List<String> answers, Map<String, String> enteredPairs) {
        this.type = QuestionType.Matching;
        this.question = question;
        this.choices = choices;
        this.answers = answers;
        this.enteredPairs = enteredPairs;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionGuid() {
        return questionGuid;
    }

    public void setQuestionGuid(String questionGuid) {
        this.questionGuid = questionGuid;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Map<String, String> getEnteredPairs() {
        return enteredPairs;
    }

    public void setEnteredPairs(Map<String, String> enteredPairs) {
        this.enteredPairs = enteredPairs;
    }
}
