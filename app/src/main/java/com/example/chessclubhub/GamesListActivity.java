package com.example.chessclubhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Space;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.chessclubhub.databinding.ActivityAnnouncementsBinding;

import java.util.ArrayList;
import java.util.HashSet;

public class GamesListActivity extends AppCompatActivity {
    HashSet<String> gameEventNames = new HashSet<>();

    //Declaring the interactive components
    ImageButton home;
    ImageButton events;
    ImageButton announcement;

    Button gameRecorderTab;

    LinearLayout buttonLayout;


    //Used when displaying the list of games played in an event
    public static final String DISPLAY_GAMES = "games to display";
    public static String displayGameEvent = " ";

    //Binding purposes
    private AppBarConfiguration appBarConfiguration;
    private ActivityAnnouncementsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Bind the components
        setContentView(R.layout.activity_games_list);

        buttonLayout = (LinearLayout) findViewById(R.id.gameEventsLayout);

        //Add announcements to Scroll View
        //When one is clicked, directs user to a more detailed page for the announcement
        for(int i = 0; i < Game.GameList.size();i++) {

            final String GAME_NAME = Game.GameList.get(i).getName();

            if(!gameEventNames.contains(GAME_NAME)){
                Button tester = new Button(this);
//                tester.setWidth(buttonLayout.getWidth());

                tester.setText(GAME_NAME);
                tester.setBackgroundColor(getColor(R.color.chess));
                tester.setTextColor(getColor(R.color.white));
                tester.setOnClickListener(v1 -> {
                    displayGameEvent = GAME_NAME;
                    SendUserToGamesDetail();
                });
                buttonLayout.addView(tester);
                Space buffer = new Space(this);
                buffer.setMinimumHeight(tester.getMinimumHeight());
                buttonLayout.addView(buffer);
            }

            gameEventNames.add(GAME_NAME);
        }


        //Setting logic for menu-bar components
        home = findViewById(R.id.home_page);

        home.setOnClickListener(v2 ->{
            SendUserToMainActivity();
        });

        announcement = (ImageButton) findViewById(R.id.announcement_page);
        announcement.setOnClickListener(v3 -> {
            SendUserToAnnouncementsActivity();
        });

        events = (ImageButton) findViewById(R.id.event_page);
        events.setOnClickListener(v3 -> {
            SendUserToEventsActivity();
        });

        gameRecorderTab = (Button) findViewById(R.id.gameRecorderTab);
        gameRecorderTab.setOnClickListener(v4 -> {
            SendUserToGameRecorder();
        });

    }

    //Event that directs user back to home page
    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(GamesListActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }

    private void SendUserToEventsActivity() {
        Intent eventIntent = new Intent(this, EventsActivity.class);
        startActivity(eventIntent);
    }

    private void SendUserToAnnouncementsActivity() {
        Intent announcementsIntent = new Intent(this, AnnouncementsActivity.class);
        startActivity(announcementsIntent);
    }

    //Event that directs user to a more detailed view of games played in a single event
    private void SendUserToGamesDetail() {
        Intent gameDetailIntent = new Intent(this, GameDetail.class);
        gameDetailIntent.putExtra(DISPLAY_GAMES,displayGameEvent);
        startActivity(gameDetailIntent);
    }

    //Event that directs user to a view where they can post their own game
    private void SendUserToGameRecorder() {
        Intent gameRecorderIntent = new Intent(this, GamePostActivity.class);
        startActivity(gameRecorderIntent);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_announcements);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

