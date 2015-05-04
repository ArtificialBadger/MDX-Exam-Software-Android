package com.mdxsoftware.mdxtesting;

/**
 * Created by Isaac on 4/7/2015.
 */
public class Constants {

    /**
     * Human Readable Versions of the Question Types
     */

    public static final String MULTIPLE_CHOICE = "Multiple Choice";

    public static final String SHORT_ANSWER = "Short Answer";

    public static final String MATCHING = "Matching";

    public static final String ALL_THAT_APPLY = "All That Apply";

    public static final String UNKNOWN_QUESTION_TYPE = "Unknown Question Type";

    /**
     *  Tags for Dialogs
    **/

    // Tag for the Team Selection Dialog
    public static final String TEAM_SELECTION_DIALOG_TAG = "TEAM_SELECTION_DIALOG_TAG";

    /**
     * Tags for passing extras with an intent
    **/

    // Extra Tag for passing a Team
    public static final String TEAM_EXTRA_TAG = "TEAM_EXTRA_TAG";

    // Extra Tag for passing an Exam
    public static final String EXAM_EXTRA_TAG = "EXAM_EXTRA_TAG";

    // Base URL for endpoint communication
    public static final String BASE_URL = "http://%s:3579/MDX/%s";

    // Extention on the Base Url when requesting an Exam from the server
    public static final String EXAM_RETRIEVAL_EXTENSION = "ExamRetrieval";
}
