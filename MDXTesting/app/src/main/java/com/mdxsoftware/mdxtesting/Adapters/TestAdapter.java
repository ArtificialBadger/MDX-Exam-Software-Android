package com.mdxsoftware.mdxtesting.Adapters;

import android.app.ActionBar;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.mdxsoftware.mdxtesting.R;

/**
 * Created by Isaac on 4/7/2015.
 */
public class TestAdapter extends BaseAdapter {

    private Context context;

    private String[] items = {"test 1", "second test", "THE HARD ONE", "Another Test", "Boomilever", "Astronomy"};

    public TestAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
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
            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setPadding(8, 32, 8, 32);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.rounded_corners_button);
            textView.setTextColor(context.getResources().getColor(R.color.black));
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(items[position]);
        return textView;
    }
}
