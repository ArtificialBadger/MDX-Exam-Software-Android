package com.mdxsoftware.mdxtesting.DataModel;

import java.util.Map;

/**
 * Created by Isaac on 4/7/2015.
 */
public class MatchingQuestion extends Question {

    private String question;

    private Map<String, String> pairs;

    private Map<String, String> enteredPairs;

    public MatchingQuestion(String question, Map<String, String> pairs, Map<String, String> enteredPairs) {
        this.type = QuestionType.Matching;
        this.question = question;
        this.pairs = pairs;
        this.enteredPairs = enteredPairs;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String, String> getPairs() {
        return pairs;
    }

    public void setPairs(Map<String, String> pairs) {
        this.pairs = pairs;
    }

    public Map<String, String> getEnteredPairs() {
        return enteredPairs;
    }

    public void setEnteredPairs(Map<String, String> enteredPairs) {
        this.enteredPairs = enteredPairs;
    }
}
