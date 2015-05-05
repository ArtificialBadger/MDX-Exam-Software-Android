package com.mdxsoftware.mdxtesting.Fragments.QuestionFragments;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    private List<String> options;

    private List<String> shuffledOptions;

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

        options = new ArrayList<String>(matchingQuestion.getPairs().keySet());

        shuffledOptions = new ArrayList<String>(matchingQuestion.getPairs().keySet());
        Collections.shuffle(shuffledOptions);

        List<String> choices = new ArrayList<String>();
        for (int i = 0; i < matchingQuestion.getPairs().size() + 1; i++)
        {
            choices.add(i + "");
        }

        for (int i = 0; i < matchingQuestion.getPairs().size(); i++)
        {
            // A copy of i to be used in the selection listener for the spinner. A compler error exists if a non final variable is used.
            final int j = i;

            // Create a LinearLayout for the spinner, option, and answer
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setGravity(Gravity.LEFT);

            // Create a TextView for the Option in random order
            TextView optionTextView = new TextView(getActivity());
            optionTextView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            optionTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            optionTextView.setText(shuffledOptions.get(i));

            // Create a spinner for the user to select an option
            Spinner spinner = new Spinner(getActivity());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, choices);
            spinner.setGravity(Gravity.LEFT);
            spinner.setAdapter(adapter);

            // If an answer already exists for this option in the entered pairs, prefill it
            if (matchingQuestion.getEnteredPairs().containsKey(shuffledOptions.get(i)))
            {
                // TODO prefill spinner selection
            }

            // The listener for the spinner when a new item is clicked
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position > 0)
                    {
                        // Adds the user selected pair to the entered pairs Map
                        matchingQuestion.getEnteredPairs().put(shuffledOptions.get(j), matchingQuestion.getPairs().get(options.get(position - 1)));
                        Toast.makeText(getActivity(), shuffledOptions.get(j) + " -> " + matchingQuestion.getPairs().get(options.get(position - 1)), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // We don't really care if they select nothing
                }
            });

            // Create a TextView for the Answer
            TextView answerTextView = new TextView(getActivity());
            answerTextView.setLayoutParams(new TableLayout.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            answerTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            answerTextView.setText((i + 1) + ".\t" + matchingQuestion.getPairs().get(options.get(i)));

            // Add the views to a row
            linearLayout.addView(spinner);
            linearLayout.addView(optionTextView);
            linearLayout.addView(answerTextView);

            // Add the row to the main fragment
            questionLinearLayout.addView(linearLayout);
        }

        return baseView;
    }
}
