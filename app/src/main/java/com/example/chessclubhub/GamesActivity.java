package com.example.chessclubhub;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

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

import com.example.chessclubhub.databinding.ActivityGamesBinding;

public class GamesActivity extends AppCompatActivity {
    ImageButton home;
    ImageButton announcements;
    ImageButton events;
    LinearLayout buttonLayout;

    public static final String DISPLAY_GAME = "game to display";

    public static int displayId = 0;
    private AppBarConfiguration appBarConfiguration;
    private ActivityGamesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGamesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        buttonLayout = (LinearLayout) findViewById(R.id.gameButtonLayout);

//        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_games);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        for(int i = 0; i < Game.GameList.size();i++){
            Button tester = new Button(this);
            tester.setId(i);
            tester.setWidth(buttonLayout.getWidth());
            tester.setText(Game.GameList.get(i).getTitle());
            tester.setBackgroundColor(getColor(R.color.chess));
            tester.setTextColor(getColor(R.color.white));
            tester.setOnClickListener(v1 -> {
                displayId = tester.getId();
                SendUserToGame_Post_Display();
            });
            buttonLayout.addView(tester);
            Space buffer = new Space(this);
            buffer.setMinimumHeight(tester.getMinimumHeight());
            buttonLayout.addView(buffer);

        }

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {SendUserToGamePostActivity();}
        });
        home = (ImageButton) findViewById(R.id.home_page);
        announcements = (ImageButton) findViewById(R.id.announcement_page);
        events = (ImageButton) findViewById(R.id.event_page);

        home.setOnClickListener(v1 -> {
            SendUserToHomeActivity();
        });

        announcements.setOnClickListener(v2 -> {
            SendUserToAnnouncementsActivity();
        });
        events.setOnClickListener(v3 ->{
            SendUserToEventsActivity();
        });
    }

    private void SendUserToGame_Post_Display() {
        Intent displayIntent = new Intent(this,Game_Post_Display.class);
        displayIntent.putExtra(DISPLAY_GAME,displayId);
        startActivity(displayIntent);
    }

    private void SendUserToEventsActivity() {
        Intent displayIntent = new Intent(this,EventsActivity.class);
        displayIntent.putExtra(DISPLAY_GAME,displayId);
        startActivity(displayIntent);
    }

    private void SendUserToGamePostActivity(){
        Intent displayIntent = new Intent(this,GamePostActivity.class);
        displayIntent.putExtra(DISPLAY_GAME,displayId);
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_games);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}