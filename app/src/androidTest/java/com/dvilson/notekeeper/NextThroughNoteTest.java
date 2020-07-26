package com.dvilson.notekeeper;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.core.AllOf;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.*;
import static  androidx.test.espresso.matcher.ViewMatchers.*;
import static  androidx.test.espresso.action.ViewActions.*;
import static  androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static  androidx.test.espresso.action.ViewActions.pressBack;
import static org.hamcrest.Matchers.*;
import static  androidx.test.espresso.assertion.ViewAssertions.*;


public class NextThroughNoteTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityActivityTestRule =
            new ActivityTestRule(MainActivity.class);


    @Test

    public void nextThroughNotes(){

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_notes));

        onView(withId(R.id.rv_notes)).perform(RecyclerViewActions.<RecyclerView.ViewHolder>actionOnItemAtPosition(0,click()));

        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        for (int index = 0; index < notes.size() ; index++){


            NoteInfo note = notes.get(index);

            onView(withId(R.id.spinner_courses)).check(matches(withSpinnerText(note.getCourse().toString())));
            onView(withId(R.id.editText_title)).check(matches(withText(note.getTitle())));
            onView(withId(R.id.editText_content)).check(matches(withText(note.getText())));

            if (index < notes.size() -1 )
                onView(allOf(withId(R.id.action_next),isEnabled())).perform(click());

        }

        onView(withId(R.id.action_next)).check(matches(not(isEnabled())));

        pressBack();



    }


}