package com.mdxsoftware.mdxtesting.Adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.mdxsoftware.mdxtesting.DataModel.Exam;
import com.mdxsoftware.mdxtesting.DataModel.Team;
import com.mdxsoftware.mdxtesting.R;

/**
 * Created by Isaac on 4/7/2015.
 */
public class TestAdapter extends BaseAdapter {

    private Context context;

    private Exam[] exams;

    public TestAdapter(Context context, Exam[] exams) {
        this.context = context;
        this.exams = exams;
    }

    @Override
    public int getCount() {
        return this.exams.length;
    }

    @Override
    public Object getItem(int position) {
        return exams[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(this.context);
            textView.setTextAppearance(this.context, R.style.MDXTextView);
            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setPadding(8, 32, 8, 32);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.rounded_corners_button);
            textView.setTextColor(context.getResources().getColor(R.color.black));
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(exams[position].getExamTitle());
        return textView;
    }
}
