package com.mdxsoftware.mdxtesting.Activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.ExamResponse;
import com.mdxsoftware.mdxtesting.DataModel.ShortAnswerQuestion;
import com.mdxsoftware.mdxtesting.DataModel.Team;
import com.mdxsoftware.mdxtesting.Dialogs.SelectTeamDialogFragment;
import com.mdxsoftware.mdxtesting.R;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
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
//        questionList.add(new MultipleChoiceQuestion("Who wrote the song \"Pittsburgh\"?", answers, 0));
//        questionList.add(new MultipleChoiceQuestion("Oliver Sykes is the lead singer for which band?", answers, 2));
//        questionList.add(new MultipleChoiceQuestion("The lead singer of which band partnered with Kellin Quinn to make \"King for a Day\"?", answers, 4));
        questionList.add(new MultipleChoiceQuestion("Question 1", answers, ""));
        questionList.add(new MultipleChoiceQuestion("This is Question 2", answers, ""));
        questionList.add(new MultipleChoiceQuestion("This is the third and final question", answers, ""));
        questionList.add(new ShortAnswerQuestion("This is a short answer question?", "This is the suggested answer for the short answer question."));
        questionList.add(new ShortAnswerQuestion("Compare and contrast the Clenshaw-Curtis and Gaussian quadratures", "Gaussian quadrature will most likely provide a more accurate result, but requires finding the kth root of a Legendre polynomial which is not feasible in all cases."));

        long duration = 3600000l;
        Date from = Calendar.getInstance().getTime();
        Date to = new Date(from.getTime() + duration);

        ArrayList<Exam> exams = new ArrayList<Exam>();
        exams.add(new Exam("Astronomy", "A-GUID", questionList, from, to, duration));
        exams.add(new Exam("Fitness", "F-GUID", questionList, from, to, duration));
        exams.add(new Exam("Math Easy", "ME-GUID", questionList, from, to, duration));
        exams.add(new Exam("Math Hard", "MH-GUID", questionList, from, to, duration));
        exams.add(new Exam("Boomilever", "B-GUID", questionList, from, to, duration));
        exams.add(new Exam("Code Busters", "CB-GUID", questionList, from, to, duration));
        exams.add(new Exam("Metal Quiz", "MQ-GUID", questionList, from, to, duration));

        // Setting the GridView, giving it an adapter, and setting the onClick of the adapter
        this.testsGridView = (GridView) findViewById(R.id.test_grid_view);
        this.testsGridView.setAdapter(new TestAdapter(this, exams));
        this.testsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Temporary Info until the webservice is set up
                Team[] teams = {new Team("Menomonie", 1), new Team("Wausau West", 2), new Team("Boyceville Blue", 3), new Team("Wausau East", 12)};

                // Creates a dialog fragment, that will have the user pick their team and open the test activity
                SelectTeamDialogFragment selectTeamDialogFragment = new SelectTeamDialogFragment();
                selectTeamDialogFragment.setArguments(teams, (Exam) parent.getAdapter().getItem(position));
                selectTeamDialogFragment.show(getFragmentManager(), Constants.TEAM_SELECTION_DIALOG_TAG);
            }
        });

        new HttpRequestTask().execute();

    }

    //TODO When QR code is scanned, call the endpoint with the GUID and IP address, then populate the GridView

    /**
     * So that the user cannot back out of the activity
     */
    @Override
    public void onBackPressed() {
        //Toast.makeText(this, "Back was pressed", Toast.LENGTH_SHORT).show();
        new HttpRequestTask().execute();
    }

    private void examResponseReceived(ExamResponse examResponse) {
        List<Exam> examList = new ArrayList<Exam>();
        examList.add(new Exam(examResponse));
        ((TestAdapter) this.testsGridView.getAdapter()).updateExams(examList);
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, ExamResponse> {
        @Override
        protected ExamResponse doInBackground(Void... params) {
            try {
                final String extension = String.format("%s/%s", "ExamRetrieval", Constants.TEST_EXAM_GUID);
                final String url = String.format(Constants.BASE_URL, "192.168.1.181", extension);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ExamResponse examResponse = restTemplate.getForObject(url, ExamResponse.class);
                return examResponse;
            } catch (Exception e) {
                Log.e(this.getClass().getSimpleName(), e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(ExamResponse examResponse) {
            examResponseReceived(examResponse);
        }

    }
}
