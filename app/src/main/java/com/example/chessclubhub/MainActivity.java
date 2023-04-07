package com.example.chessclubhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DISPLAY_ANNOUNCEMENT = "announcement to display";

    //Declare needed components
    TextView titleView;
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

        //Binding components with view
        //titleView = findViewById(R.id.titleView);
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
        eventsBrief.setOnClickListener(this);

        homePage.setOnClickListener(this);
        gamePage.setOnClickListener(this);
        eventPage.setOnClickListener(this);
        announcementPage.setOnClickListener(this);
        loginPage.setOnClickListener(this);

    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menubar, menu);
//        return true;
//    }

//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();

        //Upon selecting each icon in the menu bar,
        //the color of the icon corresponding to the chosen menu changes.
        //To stand out from the other pages.

//        switch(item.getItemId()){
//            case R.id.home_page:
//                componentCalled("Redirects to home page");
//                return true;
//
//            case R.id.game_page:
//                componentCalled("Redirects to game page");
//                return true;
//
//            case R.id.event_page:
//                componentCalled("Redirects to events page");
//                return true;
//
//            case R.id.announcement_page:
//        if(id == R.id.announcementBrief){
//            SendUserToAnnouncementsActivity();
//        }
//        componentCalled("Redirects to announcements page");
//
//            case R.id.login_page:
//                componentCalled("Redirects to admin login page");
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//        return super.onOptionsItemSelected(item);
//    }
    private void SendUserToAnnouncementsActivity() {
        Intent mainIntent = new Intent(MainActivity.this, AnnouncementsActivity.class);
        startActivity(mainIntent);
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