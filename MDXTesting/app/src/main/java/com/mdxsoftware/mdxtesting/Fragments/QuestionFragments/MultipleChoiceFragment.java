package com.mdxsoftware.mdxtesting.Fragments.QuestionFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Isaac on 4/22/2015.
 */
public class MultipleChoiceFragment extends QuestionFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("This is a multiple choice question");
        return textView;
    }

}
