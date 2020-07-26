package com.dvilson.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private NoteRecyclerAdapter mNoteRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerViewitem;
    private GridLayoutManager mGridLayoutManager;
    private CourseRecyclerAdapter mCourseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NoteActivity.class));
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        initializeDisplayContent();
    }



    @Override
    protected void onResume() {
        super.onResume();
        //mAdapterNotes.notifyDataSetChanged();
        mNoteRecyclerAdapter.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {


       /* final ListView listNotes =(ListView) findViewById(R.id.list_notes);

        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        mAdapterNotes = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,notes);

        listNotes.setAdapter(mAdapterNotes);

        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(NoteListActivity.this,NoteActivity.class);

                //NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);

                intent.putExtra(NoteActivity.NOTE_POSITION,position);

                startActivity(intent);

            }
        });
         */

        mRecyclerViewitem = (RecyclerView) findViewById(R.id.rv_notes);

        mLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this,2);


        List<NoteInfo> notes  = DataManager.getInstance().getNotes();
        List<CourseInfo> courses = DataManager.getInstance().getCourses();

        mNoteRecyclerAdapter = new NoteRecyclerAdapter(this, notes);
        displayNotes();

        mCourseRecyclerAdapter = new CourseRecyclerAdapter(this, courses);

    }

    private void displayNotes() {
        mRecyclerViewitem.setLayoutManager(mLayoutManager);
        mRecyclerViewitem.setAdapter(mNoteRecyclerAdapter);
        selectionItemChecked(R.id.nav_notes);

    }

    private void selectionItemChecked(int id) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(id).setChecked(true);

    }

    private  void displayCourses(){
        mRecyclerViewitem.setLayoutManager(mGridLayoutManager);
        mRecyclerViewitem.setAdapter(mCourseRecyclerAdapter);
        selectionItemChecked(R.id.nav_courses);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notes) {
            displayNotes();
        } else if (id == R.id.nav_courses) {
            displayCourses();


        } else if (id == R.id.nav_share) {
            handleSelection(R.string.nav_share_text);

        } else if (id == R.id.nav_send) {
            handleSelection(R.string.nav_send_text);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void handleSelection(int id ) {

        View view = findViewById(R.id.rv_notes);
        Snackbar.make(view,id,Snackbar.LENGTH_LONG).show();
    }
}
