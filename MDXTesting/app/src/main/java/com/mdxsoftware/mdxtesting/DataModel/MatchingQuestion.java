package com.mdxsoftware.mdxtesting.DataModel;

import java.util.Dictionary;

/**
 * Created by Isaac on 4/7/2015.
 */
public class MatchingQuestion extends Question {

    private String question;

    private Dictionary<String, String> pairs;

    public MatchingQuestion(String question, Dictionary<String, String> pairs) {
        this.type = QuestionType.Matching;
        this.question = question;
        this.pairs = pairs;
    }

    public String getQuestion() {
        return question;
    }

    public Dictionary<String, String> getPairs() {
        return pairs;
    }

}
