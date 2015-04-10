package com.mdxsoftware.mdxtesting.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mdxsoftware.mdxtesting.DataModel.MatchingQuestion;
import com.mdxsoftware.mdxtesting.DataModel.MultipleChoiceQuestion;
import com.mdxsoftware.mdxtesting.DataModel.Question;
import com.mdxsoftware.mdxtesting.DataModel.QuestionType;
import com.mdxsoftware.mdxtesting.R;

import java.util.List;

/**
 * Created by Isaac on 4/8/2015.
 */
public class QuestionAdapter extends BaseAdapter {

    private List<Question> questionList;

    public QuestionAdapter(List<Question> questionList)
    {
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
        TextView textView = (TextView) rowView.findViewById(R.id.question_title_text_view);

        Question question = questionList.get(position);

        if (question.getType() == QuestionType.Matching)
        {
            textView.setText(((MatchingQuestion) question).getQuestion());

        } else if (question.getType() == QuestionType.MultipleChoice) {
            textView.setText(((MultipleChoiceQuestion) question).getQuestion());

        } else {
            textView.setText("Unknown Question type");

        }

        return rowView;
    }
}
