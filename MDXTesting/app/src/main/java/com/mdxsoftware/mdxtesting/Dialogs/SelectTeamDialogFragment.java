package com.mdxsoftware.mdxtesting.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.mdxsoftware.mdxtesting.Activities.TestActivity;
import com.mdxsoftware.mdxtesting.Constants;
import com.mdxsoftware.mdxtesting.DataModel.Exam;
import com.mdxsoftware.mdxtesting.DataModel.Team;
import com.mdxsoftware.mdxtesting.R;

/**
 * Created by Isaac on 4/7/2015.
 */

/**
 * A dialog fragment that allows the user to select their team for a specific test, launches the TestActivity when a team is selected
 */
public class SelectTeamDialogFragment extends DialogFragment {

    // The exam that was clicked on
    private Exam exam;

    /**
     * Used in place of a constructor
     * Very important that this is called
     *
     * @param exam  The exam to open
     */
    public void setArguments(Exam exam) {
        this.exam = exam;
    }


    /**
     * Called by the android OS after show() is called programmatically
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.dialog_team_entry, null);

        // Creates the AlertDialog builder and sets the title and items to select
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.team_entry_title).setView(view);

        final EditText idEditText = (EditText) view.findViewById(R.id.team_id_entry);
        final EditText nameEditText = (EditText) view.findViewById(R.id.team_name_entry);

        builder
                .setPositiveButton("Begin Exam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String rawTeamId = idEditText.getText().toString();
                        String teamName = nameEditText.getText().toString();
                        int teamId = -1;

                        try
                        {
                            teamId = Integer.parseInt(rawTeamId);
                            Intent intent = new Intent(getActivity().getApplicationContext(), TestActivity.class);
                            intent.putExtra(Constants.EXAM_EXTRA_TAG, exam);
                            intent.putExtra(Constants.TEAM_EXTRA_TAG, new Team(teamName, teamId));
                            startActivity(intent);
                        }
                        catch (Exception e)
                        {
                            Log.e(this.getClass().getSimpleName(), "An error occurred while parsing the team ID from the user input", e);
                        }

                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SelectTeamDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
