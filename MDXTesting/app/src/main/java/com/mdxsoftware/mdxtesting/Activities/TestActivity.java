package com.mdxsoftware.mdxtesting.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

    // The TextView notifying the user how much time they have left on their exam
    private TextView timeRemainingTextView;

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
        this.timeRemainingTextView = (TextView) findViewById(R.id.time_remaining_text_view);

        // Reads and casts the extras into usable Data
        Intent intent = getIntent();
        this.exam = (Exam) intent.getSerializableExtra(Constants.EXAM_EXTRA_TAG);
        this.team = (Team) intent.getSerializableExtra(Constants.TEAM_EXTRA_TAG);

        this.questionListView.setAdapter(new QuestionAdapter(exam.getQuestionList()));

        setUpTimeRemainingTextView();

        this.tempTextView.setText(exam.getExamTitle() + " : " + exam.getExamID() + "\n" + team.getTeamName() + " : " + team.getTeamID());
    }

    private void setUpTimeRemainingTextView()
    {
        new CountDownTimer(exam.getValidTo().getTime() - Calendar.getInstance().getTimeInMillis(), 1000)//exam.getValidTo().getTime(), 1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {

                String minutesSecondsRemaining = String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                );
                timeRemainingTextView.setText(minutesSecondsRemaining);
            }

            @Override
            public void onFinish() {
                timeRemainingTextView.setText("Finish");
            }
        }.start();
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
