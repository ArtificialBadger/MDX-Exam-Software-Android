package com.mdxsoftware.mdxtesting.Fragments.QuestionFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mdxsoftware.mdxtesting.DataModel.MultipleChoiceQuestion;
import com.mdxsoftware.mdxtesting.DataModel.Question;
import com.mdxsoftware.mdxtesting.R;

/**
 * Created by Isaac on 4/22/2015.
 */
public class MultipleChoiceFragment extends QuestionFragment implements RadioGroup.OnCheckedChangeListener {

    private TextView questionTextView;

    private RadioGroup answersRadioGroup;

    public static MultipleChoiceFragment newInstance(Question question) {
        MultipleChoiceFragment fragment = new MultipleChoiceFragment();
        Bundle args = new Bundle();
        args.putSerializable(QUESTION_PARAM, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View baseView = inflater.inflate(R.layout.multiple_choice_question, container, false);

        this.questionTextView = (TextView) baseView.findViewById(R.id.question_text_view);
        this.answersRadioGroup = (RadioGroup) baseView.findViewById(R.id.answers_radio_group);

        MultipleChoiceQuestion mulChoiceQuestion = (MultipleChoiceQuestion) question;

        questionTextView.setText(mulChoiceQuestion.getQuestion());

        for (String answer : mulChoiceQuestion.getAnswers())
        {
            RadioButton button = new RadioButton(getActivity());
            button.setText(answer);
            answersRadioGroup.addView(button);
        }

        if (mulChoiceQuestion.getEnteredAnswerIndex() >= 0 && mulChoiceQuestion.getEnteredAnswerIndex() < mulChoiceQuestion.getAnswers().size()) {
            answersRadioGroup.check(answersRadioGroup.getChildAt(mulChoiceQuestion.getEnteredAnswerIndex()).getId());
        }

        answersRadioGroup.setOnCheckedChangeListener(this);

        return baseView;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        ((MultipleChoiceQuestion) question).setEnteredAnswerIndex(answersRadioGroup.indexOfChild(group.findViewById(checkedId)));
    }
}
