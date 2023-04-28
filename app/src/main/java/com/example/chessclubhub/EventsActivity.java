package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Space;

public class EventsActivity extends AppCompatActivity {

    ImageButton home;
    ImageButton announcements;

    ImageButton game;

    ImageButton login;
    LinearLayout buttonLayout;

    public static final String DISPLAY_EVENT = "event to display";

    public static int displayId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        buttonLayout = (LinearLayout) findViewById(R.id.eventButtonLayout);

        //Add announcements to Scroll View
        //When one is clicked, directs user to a more detailed page for the announcement
        for(int i = 0; i < Event.EventList.size();i++){
            Button tester = new Button(this);
            tester.setId(i);
            tester.setWidth(buttonLayout.getWidth());
            tester.setText(Event.EventList.get(i).toString());
            tester.setBackgroundColor(getColor(R.color.chess));
            tester.setTextColor(getColor(R.color.white));
            tester.setOnClickListener(v1 -> {
                displayId = tester.getId();
                SendUserToEvent_Display();
            });
            buttonLayout.addView(tester);
            Space buffer = new Space(this);
            buffer.setMinimumHeight(tester.getMinimumHeight());
            buttonLayout.addView(buffer);
        }

        home = (ImageButton) findViewById(R.id.home_page);
        announcements = (ImageButton) findViewById(R.id.announcement_page);
        game = (ImageButton) findViewById(R.id.game_page);
        login = (ImageButton) findViewById(R.id.login_page);

        home.setOnClickListener(v1 -> {
            SendUserToHomeActivity();
        });

        announcements.setOnClickListener(v2 -> {
            SendUserToAnnouncementsActivity();
        });

        game.setOnClickListener(v3 -> {
            SendUserToGamePage();
        });

        login.setOnClickListener(v4 -> {
            SendUserToLoginActivity();
        });

    }

    private void SendUserToGamePage(){
        Intent gameIntent = new Intent(this, GamesListActivity.class);
        startActivity(gameIntent);
    }

    private void SendUserToEvent_Display(){
        Intent displayIntent = new Intent(this,Event_Display.class);
        displayIntent.putExtra(DISPLAY_EVENT,displayId);
        startActivity(displayIntent);
    }

    private void SendUserToHomeActivity(){
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
    }

    private void SendUserToAnnouncementsActivity(){
        Intent announcementIntent = new Intent(this, AnnouncementsActivity.class);
        startActivity(announcementIntent);
    }
    private void SendUserToLoginActivity(){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }
}