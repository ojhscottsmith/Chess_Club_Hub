package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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
    String lastAnnouncementTitle;
    String newEvents;

    //For testing purposes
    final int DURATION = Toast.LENGTH_LONG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Announcement.AnnouncementList.size()==0) {
            Announcement.AnnouncementList.add(new Announcement("04/08/2022", "17:30", "Chess in VLB", "Bruce Wayne", "Join me and some friends for Chess in VLB"));
            Announcement.AnnouncementList.add(new Announcement("05/01/2022", "12:00", "Chess in Lesher Lounge", "Elizabeth Carol", "Chess in Lesher Lounge, be there or be square"));
            Announcement.AnnouncementList.add(new Announcement("04/10/2022", "10:00", "South Lounge Tournament", "Sucy Timberlake", "We are having a Chess Tournament in South Lounge. Anyone is welcome!"));
        }

        if(Event.EventList.size()==0) {
            Event.EventList.add(new Event("02/18/2023", "Standing Stone Tournament", "The monthly SSCC Tournament! Join us in the Cafe to play tournament-style chess games, in the round robin format, and get a chance to win $30 in store credit!"));
            Event.EventList.add(new Event("03/01/2023", "Charity Chess Night", "In this month's campus tournament, along with the normal prizes given for first, second, and third, we will also give all funds from the ticket sales to UNICEF. Join the tournament for a cause!"));
            Event.EventList.add(new Event("06/27/2023", "June Blitz", "A fun one: the Chess Alliance will be going to Pittsburgh for a USCF-sponsored tournament! Sign up here: https://rb.gy/tdmnp"));
        }

        //Binding components with view
        announcementBrief = findViewById(R.id.announcementBrief);
        eventsBrief = findViewById(R.id.eventsBrief);

        homePage = findViewById(R.id.home_page);
        gamePage = findViewById(R.id.game_page);
        eventPage = findViewById(R.id.event_page);
        announcementPage = findViewById(R.id.announcement_page);
        loginPage = findViewById(R.id.login_page);

        announcementBrief.setOnClickListener(this);
        lastAnnouncementTitle = Announcement.AnnouncementList.get(Announcement.AnnouncementList.size()-1).getTitle();
        announcementBrief.setText(getString(R.string.home_page_announcement_title) + "\n" + lastAnnouncementTitle);
        newEvents = Event.EventList.get(Event.EventList.size()-1).toString() + "\n" + Event.EventList.get(Event.EventList.size()-2).toString();
        eventsBrief.setOnClickListener(this);
        eventsBrief.setText(getString(R.string.home_page_event_title) + "\n" + newEvents);

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
}