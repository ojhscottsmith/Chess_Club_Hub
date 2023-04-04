package com.example.chessclubhub;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.chessclubhub.databinding.ActivityAnnouncementsBinding;

public class AnnouncementsActivity extends AppCompatActivity {
    Button postinfo;
    Button postinfo2;
    Button postinfo3;

    private AppBarConfiguration appBarConfiguration;
    private ActivityAnnouncementsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postinfo = (Button) findViewById(R.id.post_info);
        postinfo2 = (Button) findViewById(R.id.post_info2);
        postinfo3 = (Button) findViewById(R.id.post_info3);

        binding = ActivityAnnouncementsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_announcements);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToPostActivity();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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