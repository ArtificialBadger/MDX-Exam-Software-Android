package com.mdxsoftware.mdxtesting.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.mdxsoftware.mdxtesting.Activities.TestActivity;
import com.mdxsoftware.mdxtesting.Constants;
import com.mdxsoftware.mdxtesting.DataModel.Exam;
import com.mdxsoftware.mdxtesting.DataModel.Team;
import com.mdxsoftware.mdxtesting.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Isaac on 4/7/2015.
 */

/**
 * A dialog fragment that allows the user to select their team for a specific test, launches the TestActivity when a team is selected
 */
public class SelectTeamDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    // The Teams to list
    private Team[] teams;

    // The exam that was clicked on
    private Exam exam;

    /**
     * Used in place of a constructor
     * Very important that this is called
     * @param teams The teams to list
     * @param exam The exam to open
     */
    public void setArguments(Team[] teams, Exam exam)
    {
        this.teams = teams;
        this.exam = exam;
    }


    /**
     * Called by the android OS after show() is called programmatically
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Creates and fills a temporary ArrayList with the names of the teams
        List<String> teamNames = new ArrayList<String>(teams.length);
        for (Team team : this.teams) {
            teamNames.add(team.getTeamName());
        }

        // Creates the AlertDialog builder and sets the title and items to select
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Select Team for " + exam.getExamTitle())
                .setItems(teamNames.toArray(new String[teamNames.size()]), this); // sets the item listener to this

        return builder.create();
    }

    /**
     * Launches the TestActivity and puts the exam and selected test as extras
     * @param dialog The Dialog that was clicked
     * @param which The index of the item selected
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent intent = new Intent(getActivity().getApplicationContext(), TestActivity.class);
        intent.putExtra(Constants.EXAM_EXTRA_TAG, exam);
        intent.putExtra(Constants.TEAM_EXTRA_TAG, teams[which]);
        startActivity(intent);
    }
}
