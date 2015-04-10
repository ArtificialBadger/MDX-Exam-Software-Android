package com.mdxsoftware.mdxtesting.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.mdxsoftware.mdxtesting.Adapters.TestAdapter;
import com.mdxsoftware.mdxtesting.Constants;
import com.mdxsoftware.mdxtesting.DataModel.Exam;
import com.mdxsoftware.mdxtesting.DataModel.MultipleChoiceQuestion;
import com.mdxsoftware.mdxtesting.DataModel.Question;
import com.mdxsoftware.mdxtesting.DataModel.Team;
import com.mdxsoftware.mdxtesting.Dialogs.SelectTeamDialogFragment;
import com.mdxsoftware.mdxtesting.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Activity for the user to select what test they want to take
 */
public class TestSelectionActivity extends Activity{

    // The girdView with each item representing a test
    private GridView testsGridView;

    /**
     * Called by the OS when the activity is first started
     * Does some basic setup of the gridView
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);

        // While this activity is active, the screen will not dim
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        List<String> answers = new ArrayList<String>();
        answers.add("The Amity Affliction");
        answers.add("A Day To Remember");
        answers.add("Bring me the Horizon");
        answers.add("Of Mice and Men");
        answers.add("Pierce the Veil");

        List<Question> questionList = new ArrayList<Question>();
        questionList.add(new MultipleChoiceQuestion("Who wrote the song \"Pittsburgh\"?", answers, 0));
        questionList.add(new MultipleChoiceQuestion("Oliver Sykes is the lead singer for which band?", answers, 2));
        questionList.add(new MultipleChoiceQuestion("The lead singer of which band partnered with Kellin Quinn to make \"King for a Day\"?", answers, 4));

        ArrayList<Exam> exams = new ArrayList<Exam>();
        exams.add(new Exam("FirstExam", "1GUID", questionList, null, null));
        exams.add(new Exam("SecondExam", "2GUID", questionList, null, null));
        exams.add(new Exam("Astronomy", "3GUID", null, null, null));
        exams.add(new Exam("Fitness", "4GUID", null, null, null));
        exams.add(new Exam("Math Easy", "5GUID", null, null, null));
        exams.add(new Exam("Math Hard", "6GUID", null, null, null));
        exams.add(new Exam("Boomilever", "7GUID", null, null, null));
        exams.add(new Exam("Code Busters", "8GUID", null, null, null));
        exams.add(new Exam("Metal Quiz", "9GUID", questionList, null, null));

        // Setting the GridView, giving it an adapter, and setting the onClick of the adapter
        this.testsGridView = (GridView) findViewById(R.id.test_grid_view);
        this.testsGridView.setAdapter(new TestAdapter(this, exams.toArray(new Exam[exams.size()])));
        this.testsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Temporary Info until the webservice is set up
                Team[] teams = {new Team("TEAM 1", 1), new Team("Team 2", 2), new Team("Team 3", 3)};

                // Creates a dialog fragment, that will have the user pick their team and open the test activity
                SelectTeamDialogFragment selectTeamDialogFragment = new SelectTeamDialogFragment();
                selectTeamDialogFragment.setArguments(teams, (Exam) parent.getAdapter().getItem(position));
                selectTeamDialogFragment.show(getFragmentManager(), Constants.TEAM_SELECTION_DIALOG_TAG);
            }
        });

    }

    /**
     * So that the user cannot back out of the activity
     */
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back was pressed", Toast.LENGTH_SHORT).show();
    }
}
