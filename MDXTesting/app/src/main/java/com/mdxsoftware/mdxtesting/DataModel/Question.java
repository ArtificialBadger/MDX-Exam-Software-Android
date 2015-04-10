package com.mdxsoftware.mdxtesting.DataModel;

import java.io.Serializable;

/**
 * Created by Isaac on 4/7/2015.
 */
public abstract class Question implements Serializable {

    protected QuestionType type;

    public QuestionType getType() {
        return type;
    }

}
