package com.mdxsoftware.mdxtesting.Fragments.QuestionFragments;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mdxsoftware.mdxtesting.DataModel.MatchingQuestion;
import com.mdxsoftware.mdxtesting.DataModel.Question;
import com.mdxsoftware.mdxtesting.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Isaac on 5/5/2015.
 */
public class MatchingFragment extends QuestionFragment{

    private TextView questionTextView;

    private LinearLayout questionLinearLayout;

    public static MatchingFragment newInstance(Question question)
    {
        MatchingFragment fragment = new MatchingFragment();
        Bundle args = new Bundle();
        args.putSerializable(QUESTION_PARAM, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View baseView = inflater.inflate(R.layout.matching_question, container, false);

        this.questionTextView = (TextView) baseView.findViewById(R.id.matching_question_text_view);
        this.questionLinearLayout = (LinearLayout) baseView.findViewById(R.id.matching_question_linear_layout);

        final MatchingQuestion matchingQuestion = (MatchingQuestion) question;

        questionTextView.setText(matchingQuestion.getQuestion());

        final List<String> shuffledChoices = new ArrayList<String>(matchingQuestion.getChoices());
        Collections.shuffle(shuffledChoices);

        List<String> spinnerChoices = new ArrayList<String>();
        for (int i = 0; i < matchingQuestion.getChoices().size() + 1; i++)
        {
            spinnerChoices.add(i + "");    // Do not change the how these are created, the index of the selected item is important to the listener

        }

        for (int i = 0; i < matchingQuestion.getChoices().size(); i++)
        {
            // A copy of i to be used in the selection listener for the spinner. A compiler error exists if a non final variable is used.
            final int j = i;

            // Create a LinearLayout for the spinner, option, and answer
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setGravity(Gravity.LEFT);
            linearLayout.setPadding(8,8,8,0);

            // Create a TextView for the Option in random order
            TextView optionTextView = new TextView(getActivity());
            optionTextView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            optionTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            optionTextView.setText(shuffledChoices.get(i));

            // Create a FrameLayout to house the spinner because the spinner was getting cropped off slightly when not in a frame
            FrameLayout spinnerFrame = new FrameLayout(getActivity());
            spinnerFrame.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));

            // Create a spinner for the user to select an option
            Spinner spinner = new Spinner(getActivity());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, spinnerChoices);
            spinner.setAdapter(adapter);

            // If an answer already exists for this option in the entered pairs, prefill it
            if (matchingQuestion.getEnteredPairs().containsKey(shuffledChoices.get(i)))
            {
                spinner.setSelection(matchingQuestion.getAnswers().indexOf(matchingQuestion.getEnteredPairs().get(shuffledChoices.get(i))) + 1, false);
            }

            // The listener for the spinner when a new item is clicked
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position > 0)
                    {
                        // Adds the user selected pair to the entered pairs Map
                        matchingQuestion.getEnteredPairs().put(shuffledChoices.get(j), matchingQuestion.getAnswers().get(position - 1));
                        Toast.makeText(getActivity(), shuffledChoices.get(j) + " -> " +  matchingQuestion.getAnswers().get(position - 1), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // We don't really care if they select nothing
                }
            });
            spinnerFrame.addView(spinner);

            // Create a TextView for the Answer
            TextView answerTextView = new TextView(getActivity());
            answerTextView.setLayoutParams(new TableLayout.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            answerTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            answerTextView.setText((i + 1) + ".\t" + matchingQuestion.getAnswers().get(i));

            // Add the views to a row
            linearLayout.addView(spinnerFrame);
            linearLayout.addView(optionTextView);
            linearLayout.addView(answerTextView);

            // Add the row to the main fragment
            questionLinearLayout.addView(linearLayout);
        }

        return baseView;
    }
}
