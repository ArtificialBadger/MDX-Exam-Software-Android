package com.mdxsoftware.mdxtesting.DataModel;

import java.util.LinkedHashMap;

/**
 * Created by Isaac on 4/7/2015.
 */
public class MatchingQuestion extends Question {

    private String question;

    private LinkedHashMap<String, String> pairs;

    private LinkedHashMap<String, String> enteredPairs;

    public MatchingQuestion(String question, LinkedHashMap<String, String> pairs, LinkedHashMap<String, String> enteredPairs) {
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

    public LinkedHashMap<String, String> getPairs() {
        return pairs;
    }

    public void setPairs(LinkedHashMap<String, String> pairs) {
        this.pairs = pairs;
    }

    public LinkedHashMap<String, String> getEnteredPairs() {
        return enteredPairs;
    }

    public void setEnteredPairs(LinkedHashMap<String, String> enteredPairs) {
        this.enteredPairs = enteredPairs;
    }
}
