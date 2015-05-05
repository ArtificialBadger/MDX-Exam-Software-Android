package com.mdxsoftware.mdxtesting.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mdxsoftware.mdxtesting.Adapters.QuestionAdapter;
import com.mdxsoftware.mdxtesting.Constants;
import com.mdxsoftware.mdxtesting.DataModel.Exam;
import com.mdxsoftware.mdxtesting.DataModel.MatchingQuestion;
import com.mdxsoftware.mdxtesting.DataModel.MultipleChoiceQuestion;
import com.mdxsoftware.mdxtesting.DataModel.Question;
import com.mdxsoftware.mdxtesting.DataModel.ShortAnswerQuestion;
import com.mdxsoftware.mdxtesting.DataModel.Team;
import com.mdxsoftware.mdxtesting.Fragments.QuestionFragments.MatchingFragment;
import com.mdxsoftware.mdxtesting.Fragments.QuestionFragments.MultipleChoiceFragment;
import com.mdxsoftware.mdxtesting.Fragments.QuestionFragments.QuestionFragment;
import com.mdxsoftware.mdxtesting.Fragments.QuestionFragments.ShortAnswerFragment;
import com.mdxsoftware.mdxtesting.R;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * The activity to take the test
 */
public class TestActivity extends Activity implements QuestionFragment.OnFragmentInteractionListener, ListView.OnItemClickListener {

    // The exam that the team is currently taking
    private Exam exam;

    // The Team that is taking the exam
    private Team team;

    // The ListView for the questions on the test
    private ListView questionListView;

    // The TextView notifying the user how much time they have left on their exam
    private TextView timeRemainingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Don't let the screen dim while this activity is active
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Sets the views to objects on the screen
        this.questionListView = (ListView) findViewById(R.id.question_list_view);
        this.timeRemainingTextView = (TextView) findViewById(R.id.time_remaining_text_view);

        // Reads and casts the extras into usable Objects
        Intent intent = getIntent();
        this.exam = (Exam) intent.getSerializableExtra(Constants.EXAM_EXTRA_TAG);
        this.team = (Team) intent.getSerializableExtra(Constants.TEAM_EXTRA_TAG);

        this.getActionBar().setTitle(exam.getExamTitle() + " - " + this.team.getTeamName());

        this.questionListView.setAdapter(new QuestionAdapter(exam.getQuestionList()));
        this.questionListView.setOnItemClickListener(this);

        questionListView.requestFocusFromTouch();
        questionListView.setSelection(0);
        questionListView.performItemClick(
                questionListView.getChildAt(0),
                0,
                questionListView.getAdapter().getItemId(0));

        setUpTimeRemainingTextView();
    }

    /**
     * Sets up the Time Remaining Text View so that the user knows how much time is left for them to take the exam.
     * TextView updates every 1000 milliseconds, it is not uncommon for the clock to skip a second here and there, it is just syncing.
     */
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
                //TODO: What happens after the time runs out?
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        //TODO: Back pressed while taking exam
        Toast.makeText(this, "The back button is disabled while taking an exam", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Question question = (Question) parent.getAdapter().getItem(position);

        switch (question.getType()) {
            case MultipleChoice:
                this.getFragmentManager().beginTransaction().replace(R.id.question_frame, MultipleChoiceFragment.newInstance(question)).commit();
                break;
            case ShortAnswer:
                this.getFragmentManager().beginTransaction().replace(R.id.question_frame, ShortAnswerFragment.newInstance(question)).commit();
                break;
            case Matching:
                this.getFragmentManager().beginTransaction().replace(R.id.question_frame, MatchingFragment.newInstance(question)).commit();
                break;
            default:
                Toast.makeText(this, question.getType().toString(), Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onAnswerChanged(Question question) {
        switch (question.getType()) {
            case MultipleChoice:
                Toast.makeText(this, ((MultipleChoiceQuestion) question).getEnteredAnswerIndex() + " is the index of the answer", Toast.LENGTH_SHORT).show();
                break;
            case ShortAnswer:
                Toast.makeText(this, ((ShortAnswerQuestion) question).getEnteredAnswer(), Toast.LENGTH_SHORT).show();
                break;
            case Matching:
                Toast.makeText(this, ((MatchingQuestion) question).getQuestion(), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, question.getType().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
