package com.example.chessclubhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class PostActivity extends AppCompatActivity {
    Button back;
    Button post;
    EditText announcementtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        post = (Button) findViewById(R.id.post_button);
        announcementtext = (EditText) findViewById(R.id.announcement_text);

        back = (Button)  findViewById(R.id.back_button);
        back.setOnClickListener(v1 ->{
            SendUserToAnnouncementsActivity();
        });


    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        if(id == R.id.back_button){
//            SendUserToAnnouncementsActivity();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void SendUserToAnnouncementsActivity() {
        Intent mainIntent = new Intent(PostActivity.this, AnnouncementsActivity.class);
        startActivity(mainIntent);
    }
}