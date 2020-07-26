package com.dvilson.notekeeper;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

public class NoteActivityViewModel extends ViewModel {
    public static final String ORIGINNAL_NOTE_COURSE_ID ="com.dvilson.notekeeper.ORIGINNAL_NOTE_COURSE_ ID";
    public static final String ORIGINNAL_NOTE_TITLE ="com.dvilson.notekeeper.ORIGINNAL_NOTE_ID";
    public static final String ORIGINNAL_NOTE_TEXT ="com.dvilson.notekeeper.ORIGINNAL_NOTE_ID";

    public String mOriginalNoteId;
    public String mOriginalNoteTitle;
    public String mOriginalNoteText;
    public boolean mIsNewlyCreated = true;


    public void saveNote(Bundle outState) {

        outState.putString(ORIGINNAL_NOTE_COURSE_ID, mOriginalNoteId);
        outState.putString(ORIGINNAL_NOTE_TITLE,mOriginalNoteTitle);
        outState.putString(ORIGINNAL_NOTE_TEXT,mOriginalNoteText);

    }

    public void restoreState(Bundle inState){

        mOriginalNoteId = inState.getString(ORIGINNAL_NOTE_COURSE_ID);
        mOriginalNoteTitle = inState.getString(ORIGINNAL_NOTE_TITLE);
        mOriginalNoteText = inState.getString(ORIGINNAL_NOTE_TEXT);
    }
}
