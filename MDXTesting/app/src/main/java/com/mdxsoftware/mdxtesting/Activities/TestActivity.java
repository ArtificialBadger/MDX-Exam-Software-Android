package com.mdxsoftware.mdxtesting.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mdxsoftware.mdxtesting.Adapters.QuestionAdapter;
import com.mdxsoftware.mdxtesting.Constants;
import com.mdxsoftware.mdxtesting.DataModel.Exam;
import com.mdxsoftware.mdxtesting.DataModel.Team;
import com.mdxsoftware.mdxtesting.R;

/**
 * The activity to take the test
 */
public class TestActivity extends Activity {

    // The exam that the team is currently taking
    private Exam exam;

    // The Team that is taking the exam
    private Team team;

    // The frame for switching out the question fragments
    private FrameLayout questionFrame;

    // The ListView for the questions on the test
    private ListView questionListView;

    //TODO remove this textView
    private TextView tempTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Don't let the screen dim while this activity is active
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Sets the views to objects on the screen
        this.questionListView = (ListView) findViewById(R.id.question_list_view);
        this.questionFrame = (FrameLayout) findViewById(R.id.question_frame);
        this.tempTextView = (TextView) findViewById(R.id.temp_text_view);

        // Reads and casts the extras into usable Data
        Intent intent = getIntent();
        this.exam = (Exam) intent.getSerializableExtra(Constants.EXAM_EXTRA_TAG);
        this.team = (Team) intent.getSerializableExtra(Constants.TEAM_EXTRA_TAG);

        this.questionListView.setAdapter(new QuestionAdapter(exam.getQuestionList()));


        this.tempTextView.setText(exam.getExamTitle() + " : " + exam.getExamID() + "\n" + team.getTeamName() + " : " + team.getTeamID());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
