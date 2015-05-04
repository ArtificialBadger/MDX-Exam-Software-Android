package com.mdxsoftware.mdxtesting.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mdxsoftware.mdxtesting.Constants;
import com.mdxsoftware.mdxtesting.DataModel.Question;
import com.mdxsoftware.mdxtesting.DataModel.QuestionType;
import com.mdxsoftware.mdxtesting.R;

import java.util.List;

/**
 * Created by Isaac on 4/8/2015.
 */
public class QuestionAdapter extends BaseAdapter {

    private List<Question> questionList;

    public QuestionAdapter(List<Question> questionList) {
        super();
        this.questionList = questionList;
    }


    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public Object getItem(int position) {
        return questionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.question_list_item, parent, false);
        } else {
            rowView = convertView;
        }
        TextView idTextView = (TextView) rowView.findViewById(R.id.question_id_text_view);
        TextView typeTextView = (TextView) rowView.findViewById(R.id.question_type_text_view);

        Question question = questionList.get(position);

        idTextView.setText("Question " + (position+1) );

        if (question.getType() == QuestionType.Matching) {
            typeTextView.setText(Constants.MATCHING);

        } else if (question.getType() == QuestionType.MultipleChoice) {
            typeTextView.setText(Constants.MULTIPLE_CHOICE);

        } else if (question.getType() == QuestionType.ShortAnswer) {
            typeTextView.setText(Constants.SHORT_ANSWER);

        } else {
            typeTextView.setText(Constants.UNKNOWN_QUESTION_TYPE);

        }

        return rowView;
    }
}
