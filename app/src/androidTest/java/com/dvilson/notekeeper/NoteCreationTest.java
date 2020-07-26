package com.dvilson.notekeeper;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.core.AllOf;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.*;
import static  androidx.test.espresso.matcher.ViewMatchers.*;
import static  androidx.test.espresso.action.ViewActions.*;
import static  androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static  androidx.test.espresso.action.ViewActions.pressBack;
import static org.hamcrest.Matchers.*;
import static  androidx.test.espresso.assertion.ViewAssertions.*;


@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {
    DataManager mDataManager;
    @BeforeClass
    public void classSetUp(){

        mDataManager = DataManager.getInstance();

    }

    @Rule
    public ActivityTestRule<NoteListActivity>  mNoteListActivityRule =
            new ActivityTestRule<>(NoteListActivity.class);


    @Test

    public void createNewNote(){
        final CourseInfo course = mDataManager.getCourse("java_lang");
        final String noteTitle = "This is the title ";
        final String noteText = "This is the body of our test note";


//        ViewInteraction fabNewNote  = onView(withId(R.id.fab));
//        fabNewNote.perform(click());


        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.spinner_courses)).perform(click());
        onData(allOf(instanceOf(CourseInfo.class),equalTo(course))).perform(click());

        onView(withId(R.id.spinner_courses)).check(matches(withSpinnerText(containsString(course.getTitle()))));


        onView(withId(R.id.editText_title)).perform(typeText(noteTitle))
                .check(matches(withText(containsString(noteTitle))));

        onView(withId(R.id.editText_content)).perform(typeText(noteText),
                closeSoftKeyboard());
        onView(withId(R.id.editText_content)).check(matches(withText(containsString(noteText))));



        pressBack();

        int noteIndex = mDataManager.getNotes().size() -1;
        NoteInfo note  = mDataManager.getNotes().get(noteIndex);
        assertEquals(course, note.getCourse());
        assertEquals(noteTitle,note.getTitle());
        assertEquals(noteText,note.getText());



    }



}