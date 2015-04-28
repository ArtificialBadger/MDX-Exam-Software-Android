package com.mdxsoftware.mdxtesting.Fragments.QuestionFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mdxsoftware.mdxtesting.DataModel.Question;
import com.mdxsoftware.mdxtesting.DataModel.ShortAnswerQuestion;
import com.mdxsoftware.mdxtesting.R;

/**
 * Created by Isaac on 4/28/2015.
 */
public class ShortAnswerFragment extends QuestionFragment {

    private TextView questionTextView;

    private EditText answerEditText;

    public static ShortAnswerFragment newInstance(Question question) {
        ShortAnswerFragment fragment = new ShortAnswerFragment();
        Bundle args = new Bundle();
        args.putSerializable(QUESTION_PARAM, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View baseView = inflater.inflate(R.layout.short_answer_question, container, false);

        this.questionTextView = (TextView) baseView.findViewById(R.id.short_answer_question_text_view);
        this.answerEditText = (EditText) baseView.findViewById(R.id.answer_edit_text);

        ShortAnswerQuestion shortAnswerQuestion = (ShortAnswerQuestion) question;

        questionTextView.setText(shortAnswerQuestion.getQuestion());

        return baseView;

    }

}
