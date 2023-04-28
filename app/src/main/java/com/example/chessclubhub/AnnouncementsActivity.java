package com.example.chessclubhub;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Space;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.chessclubhub.databinding.ActivityAnnouncementsBinding;

//Controller class for the Announcements page
//Defines logic for the components defined in activity_announcements.xml
public class AnnouncementsActivity extends AppCompatActivity {

    //Declaring the interactive components
    ImageButton home;
    ImageButton events;
    ImageButton game;

    ImageButton login;
    LinearLayout buttonLayout;

    //Used when displaying a single announcement
    public static final String DISPLAY_ANNOUNCEMENT = "announcement to display";
    public static int displayId = 0;

    //Binding purposes
    private AppBarConfiguration appBarConfiguration;
    private ActivityAnnouncementsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Bind the components
        binding = ActivityAnnouncementsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);

        //Add announcements to Scroll View
        //When one is clicked, directs user to a more detailed page for the announcement
        for(int i = 0; i < Announcement.AnnouncementList.size();i++){
            Button tester = new Button(this);
            tester.setId(i);
            tester.setWidth(buttonLayout.getWidth());
            tester.setText(Announcement.AnnouncementList.get(i).getTitle());
            tester.setBackgroundColor(getColor(R.color.chess));
            tester.setTextColor(getColor(R.color.white));
            tester.setOnClickListener(v1 -> {
                displayId = tester.getId();
                SendUserToPost_Display();
            });
            buttonLayout.addView(tester);
            Space buffer = new Space(this);
            buffer.setMinimumHeight(tester.getMinimumHeight());
            buttonLayout.addView(buffer);

        }

        //Setting logic for menu-bar components
        home = findViewById(R.id.home_page);

        home.setOnClickListener(v2 ->{
            SendUserToMainActivity();
        });
        game = (ImageButton) findViewById(R.id.game_page);
        game.setOnClickListener(v3 -> {
            SendUserToGamePage();
        });

        events = (ImageButton) findViewById(R.id.event_page);
        events.setOnClickListener(v3 -> {
            SendUserToEventsActivity();
        });

        login = (ImageButton) findViewById(R.id.login_page);
        login.setOnClickListener(v4 -> {
            SendUserToLoginActivity();
        });


        //Logic for Floating Action Button (adding a new announcement)
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToPostActivity();
            }
        });
    }

    //Event that directs user back to home page
    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(AnnouncementsActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }

    private void SendUserToGamePage(){
        Intent gameIntent = new Intent(this, GamesListActivity.class);
        startActivity(gameIntent);
    }

    //Event that directs user to a more detailed view of a single announcement
    private void SendUserToPost_Display() {
        Intent mainIntent = new Intent(AnnouncementsActivity.this, Post_Display.class);
        mainIntent.putExtra(DISPLAY_ANNOUNCEMENT,displayId);
        startActivity(mainIntent);
    }

    //Event that directs user to a view where they can post their own activity
    private void SendUserToPostActivity() {
        Intent addNewPostIntent = new Intent(AnnouncementsActivity.this, PostActivity.class);
        startActivity(addNewPostIntent);
    }

    private void SendUserToEventsActivity(){
        Intent eventIntent = new Intent(this, EventsActivity.class);
        startActivity(eventIntent);
    }

    private void SendUserToLoginActivity(){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_announcements);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}