package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Post_Display extends AppCompatActivity {
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_display);
        back = (Button)  findViewById(R.id.back_button2);
        back.setOnClickListener(v1 ->{
            SendUserToAnnouncementsActivity();
        });
    }
    private void SendUserToAnnouncementsActivity() {
        Intent mainIntent = new Intent(Post_Display.this, AnnouncementsActivity.class);
        startActivity(mainIntent);
    }
}