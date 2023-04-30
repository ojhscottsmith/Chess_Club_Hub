package com.example.chessclubhub;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.*;

//Controller class for the Home page
//Defines logic for the components defined in activity_main.xml

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String DISPLAY_ANNOUNCEMENT = "announcement to display";

    //Declare needed components
    Button announcementBrief;
    Button eventsBrief;

    //Defining menu bar ourselves, to allow for more customization
    ImageButton homePage;
    ImageButton gamePage;
    ImageButton eventPage;
    ImageButton announcementPage;
    ImageButton loginPage;

    //For briefs
//   String lastAnnouncementTitle;
    String newEvents;

    //For testing purposes
    final int DURATION = Toast.LENGTH_LONG;

    FirebaseDatabase cchDatabase;

    DatabaseReference storedGames;
    DatabaseReference storedAnnouncements;
    DatabaseReference storedEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding components with view
        announcementBrief = findViewById(R.id.announcementBrief);
        eventsBrief = findViewById(R.id.eventsBrief);

        homePage = findViewById(R.id.home_page);
        gamePage = findViewById(R.id.game_page);
        eventPage = findViewById(R.id.event_page);
        announcementPage = findViewById(R.id.announcement_page);
        loginPage = findViewById(R.id.login_page);

        cchDatabase = FirebaseDatabase.getInstance();

        Announcement.AnnouncementList.clear();
        readAnnouncementData(new DataCallback() {
            @Override
            public void onDataCallback(String value) {
                announcementBrief.setText(getString(R.string.home_page_announcement_title) + "\n" + value);
            }
        });

        Event.EventList.clear();
        readEventsData(new DataCallback() {
            @Override
            public void onDataCallback(String value){
                String[] titlesArr = value.split("\n");
                String eventsBriefContent = getString(R.string.home_page_event_title) + "\n";
                if(titlesArr.length>0){
                    eventsBriefContent = eventsBriefContent.concat(titlesArr[titlesArr.length-1] + "\n");
                    if(titlesArr.length>1) eventsBriefContent = eventsBriefContent.concat(titlesArr[titlesArr.length-2] + "\n");
                }
                eventsBrief.setText(eventsBriefContent);
            }
        });

        Game.GameList.clear();

        storedGames = cchDatabase.getReference().child("games");
        ValueEventListener gamesValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String gameDate = String.valueOf(ds.child("date").getValue());
                    String gameEventTitle = String.valueOf(ds.child("name").getValue());
                    String gameEventSite = String.valueOf(ds.child("site").getValue());
                    String gameBlack = String.valueOf(ds.child("black").getValue());
                    String gameWhite = String.valueOf(ds.child("white").getValue());
                    String gameResult = String.valueOf(ds.child("result").getValue());
                    String gameMoves = String.valueOf(ds.child("moves").getValue());

                    Game newGame = new Game(gameDate,gameEventTitle,gameEventSite,gameBlack,gameWhite,gameResult,gameMoves);
                    Game.GameList.add(newGame);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };
        storedGames.addListenerForSingleValueEvent(gamesValueListener);

        announcementBrief.setOnClickListener(this);
        eventsBrief.setOnClickListener(this);

        homePage.setOnClickListener(this);
        gamePage.setOnClickListener(this);
        eventPage.setOnClickListener(this);
        announcementPage.setOnClickListener(this);
        loginPage.setOnClickListener(this);

    }

    private void SendUserToAnnouncementsActivity() {
        Intent mainIntent = new Intent(MainActivity.this, AnnouncementsActivity.class);
        startActivity(mainIntent);
    }

    private void SendUserToEventsActivity(){
        Intent eventBriefIntent = new Intent(this, EventsActivity.class);
        startActivity(eventBriefIntent);
    }

    private void SendUserToGameActivity() {
        Intent mainIntent = new Intent(this, GamesListActivity.class);
        startActivity(mainIntent);
    }

    private void SendUserToLoginActivity(){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.announcementBrief:
                //Redirect to the newest announcement
                Intent announcementBriefIntent = new Intent(MainActivity.this,Post_Display.class);
                announcementBriefIntent.putExtra(DISPLAY_ANNOUNCEMENT,Announcement.AnnouncementList.size()-1);
                componentCalled("Redirects to most recent announcement");
                startActivity(announcementBriefIntent);
                break;

            case R.id.eventsBrief:
                //redirect to the events list
                Intent eventBriefIntent = new Intent(this, EventsActivity.class);
                startActivity(eventBriefIntent);
                componentCalled("Redirects to events list");
                break;

            case R.id.home_page:
                componentCalled("Redirects to home page");
                break;

            case R.id.game_page:
                SendUserToGameActivity();
                componentCalled("Redirects to game page");
                break;

            case R.id.event_page:
                componentCalled("Redirects to events page");
                SendUserToEventsActivity();
                break;

            case R.id.announcement_page:
                SendUserToAnnouncementsActivity();
                componentCalled("Redirects to announcements page");
                break;

            case R.id.login_page:
                SendUserToLoginActivity();
                componentCalled("Redirects to admin login page");
                break;
        }
    }

    //Testing if interaction with component functions
    void componentCalled(String displayText){
        Context context = getApplicationContext();

        Toast debugger = Toast.makeText(context,displayText,DURATION);
        debugger.show();
    }

    void readAnnouncementData(DataCallback dataCallback) {
        //Getting announcements from database
        storedAnnouncements = cchDatabase.getReference().child("announcements");
        ValueEventListener announcementsValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String lastAnnouncementTitle = " ";

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    String announcementDate = String.valueOf(ds.child("date").getValue());
                    String announcementTime = String.valueOf(ds.child("time").getValue());
                    String announcementTitle = String.valueOf(ds.child("title").getValue());
                    String announcementAuthor = String.valueOf(ds.child("author").getValue());
                    String announcementContent = String.valueOf(ds.child("content").getValue());

                    Announcement newAnnouncement = new Announcement(announcementDate,announcementTime,announcementTitle,announcementAuthor,announcementContent);
                    Announcement.AnnouncementList.add(newAnnouncement);

                    lastAnnouncementTitle = announcementTitle;
                }

                dataCallback.onDataCallback(lastAnnouncementTitle);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };
        storedAnnouncements.addListenerForSingleValueEvent(announcementsValueListener);
    }

    void readEventsData(DataCallback dataCallback){
        //Getting announcements from database
        storedEvents = cchDatabase.getReference().child("events");
        ValueEventListener eventsValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String lastEventTitles = "";

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String eventDate = String.valueOf(ds.child("date").getValue());
                    String eventTitle = String.valueOf(ds.child("title").getValue());
                    String eventContent = String.valueOf(ds.child("content").getValue());

                    Event newEvent = new Event(eventDate,eventTitle,eventContent);
                    Event.EventList.add(newEvent);

                    String currHeader = eventDate + " - " + eventTitle + "\n";

                    lastEventTitles = lastEventTitles.concat(currHeader);
                }

                dataCallback.onDataCallback(lastEventTitles);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };
        storedEvents.addListenerForSingleValueEvent(eventsValueListener);
    }
}