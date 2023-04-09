package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostActivity extends AppCompatActivity {
    Button back;
    Button post;
    EditText announcement_title_edit, announcement_author_edit, announcement_content_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        post = (Button) findViewById(R.id.post_button);
        post.setOnClickListener(v1 -> {
            postAnnouncementEvent();
        });

        announcement_title_edit = (EditText) findViewById(R.id.announcement_title_edit);
        announcement_author_edit = (EditText) findViewById(R.id.announcement_author_edit);
        announcement_content_edit = (EditText) findViewById(R.id.announcement_content_edit);

        back = (Button)  findViewById(R.id.back_button);
        back.setOnClickListener(v2 ->{
            SendUserToAnnouncementsActivity();
        });


    }

    private void postAnnouncementEvent() {
        String currDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        String currTime = new SimpleDateFormat("HH:mm").format(new Date());
        String title = announcement_title_edit.getText().toString();
        String author = announcement_author_edit.getText().toString();
        String content = announcement_content_edit.getText().toString();

        Announcement newAnnouncement = new Announcement(currDate,currTime,title,author,content);
        Announcement.AnnouncementList.add(newAnnouncement);

    }
    private void SendUserToAnnouncementsActivity() {
        Intent mainIntent = new Intent(PostActivity.this, AnnouncementsActivity.class);
        startActivity(mainIntent);
    }
}