package com.mdxsoftware.mdxtesting.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.mdxsoftware.mdxtesting.Adapters.TestAdapter;
import com.mdxsoftware.mdxtesting.ExtraTags;
import com.mdxsoftware.mdxtesting.R;

public class TestSelectionActivity extends Activity{

    private GridView testsGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.testsGridView = (GridView) findViewById(R.id.test_grid_view);
        this.testsGridView.setAdapter(new TestAdapter(this));
        this.testsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                intent.putExtra(ExtraTags.TEMP_TITLE, (String) parent.getAdapter().getItem(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back was pressed", Toast.LENGTH_SHORT).show();
    }
}
