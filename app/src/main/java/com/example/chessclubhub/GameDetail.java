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

public class GameDetail extends AppCompatActivity {

    //Declaring the interactive components
    ImageButton home;
    ImageButton events;
    ImageButton announcement;
    LinearLayout buttonLayout;

    Button gameRecorderTab;
    Button gameBackToListButton;

    public static final String DISPLAY_GAMES = "games to display";

    //Used when displaying a single game
    public static final String GAME_TO_DISPLAY = "individual game to display";

    public static int displayId = 0;

    //Binding purposes
    private AppBarConfiguration appBarConfiguration;
    private ActivityAnnouncementsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);


        buttonLayout = (LinearLayout) findViewById(R.id.gamesPlayedLayout);

        Intent intent = getIntent();
        String chosenEvent = intent.getStringExtra(DISPLAY_GAMES);

        //Add games to Scroll View
        //When one is clicked, directs user to a more detailed page for the game
        for(int i = 0; i < Game.GameList.size();i++) {
            if (Game.GameList.get(i).getName().equals(chosenEvent)) {

                Button tester = new Button(this);
                tester.setWidth(buttonLayout.getWidth());
                tester.setId(i);
                tester.setText(Game.GameList.get(i).white + " (W) vs " + Game.GameList.get(i).black + " (B)");
                tester.setBackgroundColor(getColor(R.color.chess));
                tester.setTextColor(getColor(R.color.white));
                tester.setOnClickListener(v1 -> {
                    displayId = tester.getId();
                    SendUserToGameDisplay();
                });
                buttonLayout.addView(tester);
                Space buffer = new Space(this);
                buffer.setMinimumHeight(tester.getMinimumHeight());
                buttonLayout.addView(buffer);

            }
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

        gameBackToListButton = (Button) findViewById(R.id.gameBackToListButton);
        gameBackToListButton.setOnClickListener(v5 -> {
            SendUserToGameList();
        });

    }

    //Event that directs user back to home page
    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    private void SendUserToAnnouncementsActivity() {
        Intent announcementsIntent = new Intent(this,AnnouncementsActivity.class);
        startActivity(announcementsIntent);
    }

    //Event that directs user to a more detailed view of a single game
    private void SendUserToGameDisplay() {
        Intent displayIntent = new Intent(this, Game_Display.class);
        displayIntent.putExtra(GAME_TO_DISPLAY,displayId);
        startActivity(displayIntent);
    }

    //Event that directs user to a view where they can post their own game
    private void SendUserToGameRecorder() {
        Intent gameRecorderIntent = new Intent(this, GamePostActivity.class);
        startActivity(gameRecorderIntent);
    }

    private void SendUserToEventsActivity(){
        Intent eventIntent = new Intent(this, EventsActivity.class);
        startActivity(eventIntent);
    }

    private void SendUserToGameList() {
        Intent gameListIntent = new Intent(this,GamesListActivity.class);
        startActivity(gameListIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_announcements);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

