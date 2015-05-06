package com.mdxsoftware.mdxtesting.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mdxsoftware.mdxtesting.Adapters.TestAdapter;
import com.mdxsoftware.mdxtesting.Constants;
import com.mdxsoftware.mdxtesting.DataModel.Exam;
import com.mdxsoftware.mdxtesting.DataModel.MatchingQuestion;
import com.mdxsoftware.mdxtesting.DataModel.MultipleChoiceQuestion;
import com.mdxsoftware.mdxtesting.DataModel.Question;
import com.mdxsoftware.mdxtesting.DataModel.ResponseObjects.ExamResponse;
import com.mdxsoftware.mdxtesting.DataModel.ShortAnswerQuestion;
import com.mdxsoftware.mdxtesting.Dialogs.SelectTeamDialogFragment;
import com.mdxsoftware.mdxtesting.R;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Activity for the user to select what test they want to take
 */
public class TestSelectionActivity extends Activity {

    // The girdView with each item representing a test
    private GridView testsGridView;

    /**
     * Called by the OS when the activity is first started
     * Does some basic setup of the gridView
     *
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

        List<String> mulAnswers = new ArrayList<>();
        List<String> choices = new ArrayList<>();
        mulAnswers.add("Red");
        choices.add("Blue");
        mulAnswers.add("Android");
        choices.add("iOS");
        mulAnswers.add("Ford");
        choices.add("Subaru");
        mulAnswers.add("Gauss");
        choices.add("Euler");
        mulAnswers.add("Steel");
        choices.add("Iron");

        List<Question> questionList = new ArrayList<Question>();
        questionList.add(new MultipleChoiceQuestion("Question 1", answers));
        questionList.add(new MultipleChoiceQuestion("This is Question 2", answers));
        questionList.add(new MultipleChoiceQuestion("This is the third and final question", answers));
        questionList.add(new ShortAnswerQuestion("This is a short answer question?"));
        questionList.add(new ShortAnswerQuestion("Compare and contrast the Clenshaw-Curtis and Gaussian quadratures"));
        questionList.add(new MatchingQuestion("Match the items that are most similar", choices, mulAnswers, new LinkedHashMap<String, String>()));

        long duration = 3600000l;

        ArrayList<Exam> exams = new ArrayList<Exam>();
        exams.add(new Exam("Astronomy", "A-GUID", questionList, duration));
        exams.add(new Exam("Fitness", "F-GUID", questionList, duration));
        exams.add(new Exam("Math Easy", "ME-GUID", questionList, duration));
        exams.add(new Exam("Math Hard", "MH-GUID", questionList, duration));
        exams.add(new Exam("Boomilever", "B-GUID", questionList, duration));
        exams.add(new Exam("Code Busters", "CB-GUID", questionList, duration));
        exams.add(new Exam("Metal Quiz", "MQ-GUID", questionList, duration));

        // Setting the GridView, giving it an adapter, and setting the onClick of the adapter
        this.testsGridView = (GridView) findViewById(R.id.test_grid_view);
        this.testsGridView.setAdapter(new TestAdapter(this, exams));
        this.testsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Creates a dialog fragment, that will have the user pick their team and open the test activity
                SelectTeamDialogFragment selectTeamDialogFragment = new SelectTeamDialogFragment();
                selectTeamDialogFragment.setArguments((Exam) parent.getAdapter().getItem(position));
                selectTeamDialogFragment.show(getFragmentManager(), Constants.TEAM_SELECTION_DIALOG_TAG);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test_selection_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scan_qr_code:
                this.scanQRCode();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                String[] parsedTuringData = result.getContents().split(":");
                new TestRequestTask().execute(parsedTuringData);

            } else if (resultCode == RESULT_CANCELED) {

                Toast.makeText(this, "QR Scanning cancelled", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void scanQRCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan a Turing generated QR Code");
        integrator.initiateScan();
    }

    /**
     * So that the user cannot back out of the activity
     */
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back was pressed", Toast.LENGTH_SHORT).show();
    }

    private void examResponseReceived(ExamResponse examResponse) {
        List<Exam> examList = new ArrayList<Exam>();
        examList.add(new Exam(examResponse));
        ((TestAdapter) this.testsGridView.getAdapter()).updateExams(examList);
    }

    private class TestRequestTask extends AsyncTask<String, Void, ExamResponse> {
        @Override
        protected ExamResponse doInBackground(String... params) {
            try {
                String extension, url;
                String ipAddress, examGuid;
                if (params.length >= 2) {
                    ipAddress = params[0];
                    examGuid = params[1];
                } else {
                    Log.e(this.getClass().getSimpleName(), "IP and ExamGuid are not present, unable to complete request");
                    return null;
                }

                extension = String.format("%s/%s", Constants.EXAM_RETRIEVAL_EXTENSION, examGuid);
                url = String.format(Constants.BASE_URL, ipAddress, extension);

                Log.i(this.getClass().getSimpleName(), "About to request Test from " + url);

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                return restTemplate.getForObject(url, ExamResponse.class);

            } catch (Exception e) {
                Log.e(this.getClass().getSimpleName(), e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(ExamResponse examResponse) {
            if (examResponse != null) {
                examResponseReceived(examResponse);
            } else {
                Log.e(this.getClass().getSimpleName(), "No ExamResponse object received from endpoint");
            }
        }

    }
}
