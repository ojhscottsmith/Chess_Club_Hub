package com.example.chessclubhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declare needed components
    TextView titleView;
    Button announcementBrief;
    Button eventsBrief;

    //For testing purposes
    final int DURATION = Toast.LENGTH_LONG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding components with view
        titleView = findViewById(R.id.titleView);
        announcementBrief = findViewById(R.id.announcementBrief);
        eventsBrief = findViewById(R.id.eventsBrief);

        announcementBrief.setOnClickListener(this);
        eventsBrief.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //Upon selecting each icon in the menu bar,
        //the color of the icon corresponding to the chosen menu changes.
        //To stand out from the other pages.

        switch(item.getItemId()){
            case R.id.home_page:
                componentCalled("Redirects to home page");
                return true;

            case R.id.game_page:
                componentCalled("Redirects to game page");
                return true;

            case R.id.event_page:
                componentCalled("Redirects to events page");
                return true;

            case R.id.announcement_page:
                componentCalled("Redirects to announcements page");
                return true;

            case R.id.login_page:
                componentCalled("Redirects to admin login page");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.announcementBrief:
                //Redirect to the newest announcement
                componentCalled("Redirects to most recent announcement");
                break;

            case R.id.eventsBrief:
                //redirect to the events list
                componentCalled("Redirects to events list");
                break;
        }
    }

    //Testing if interaction with component functions
    void componentCalled(String displayText){
        Context context = getApplicationContext();

        Toast debugger = Toast.makeText(context,displayText,DURATION);
        debugger.show();
    }


}