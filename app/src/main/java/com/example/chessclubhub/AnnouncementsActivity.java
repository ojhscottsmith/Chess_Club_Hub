package com.example.chessclubhub;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.chessclubhub.databinding.ActivityAnnouncementsBinding;

public class AnnouncementsActivity extends AppCompatActivity {
    ImageButton home;
    Button postinfo;
    Button postinfo2;
    Button postinfo3;

    private AppBarConfiguration appBarConfiguration;
    private ActivityAnnouncementsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAnnouncementsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        postinfo = (Button) findViewById(R.id.post_info);
        postinfo2 = (Button) findViewById(R.id.post_info2);
        postinfo3 = (Button) findViewById(R.id.post_info3);
        home = findViewById(R.id.home_page);
        postinfo.setOnClickListener(v1 ->{
            SendUserToPost_Display();
        });
        home.setOnClickListener(v2 ->{
            SendUserToMainActivity();
        });

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_announcements);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToPostActivity();
            }
        });
    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(AnnouncementsActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }

    private void SendUserToPost_Display() {
        Intent mainIntent = new Intent(AnnouncementsActivity.this, Post_Display.class);
        startActivity(mainIntent);
    }

    private void SendUserToPostActivity() {
        Intent addNewPostIntent = new Intent(AnnouncementsActivity.this, PostActivity.class);
        startActivity(addNewPostIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_announcements);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}