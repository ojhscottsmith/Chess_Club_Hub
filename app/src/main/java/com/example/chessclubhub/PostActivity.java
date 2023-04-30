package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostActivity extends AppCompatActivity {
    Button back;
    Button post;
    EditText announcement_title_edit, announcement_author_edit, announcement_content_edit;

    Button edit;

    public static final String DISPLAY_ANNOUNCEMENT = "announcement to display";

    int announcementId = -1;

    DatabaseReference storedAnnouncements = FirebaseDatabase.getInstance().getReference().child("announcements");

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

        edit = (Button) findViewById(R.id.editAnnouncementButton);
        edit.setOnClickListener(v3 -> {
            SaveChanges();
        });

        Intent intent = getIntent();
        announcementId = intent.getIntExtra(DISPLAY_ANNOUNCEMENT,-1);

        if(announcementId == -1) {
            post.setVisibility(View.VISIBLE);
            edit.setVisibility(View.GONE);
        }
        else {
            Announcement currAnnouncement = Announcement.AnnouncementList.get(announcementId);
            post.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);

            announcement_title_edit.setText(currAnnouncement.getTitle());
            announcement_author_edit.setText(currAnnouncement.getAuthor());
            announcement_content_edit.setText(currAnnouncement.getContent());
        }

    }

    private void postAnnouncementEvent() {
        String currDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        String currTime = new SimpleDateFormat("HH:mm").format(new Date());
        String title = announcement_title_edit.getText().toString();
        String author = announcement_author_edit.getText().toString();
        String content = announcement_content_edit.getText().toString();

        Announcement newAnnouncement = new Announcement(currDate,currTime,title,author,content);
        Announcement.AnnouncementList.add(newAnnouncement);

        announcementId = Announcement.AnnouncementList.indexOf(newAnnouncement);
        storedAnnouncements.child("announcement"+announcementId).setValue(newAnnouncement);

    }
    private void SendUserToAnnouncementsActivity() {
        Intent mainIntent = new Intent(PostActivity.this, AnnouncementsActivity.class);
        startActivity(mainIntent);
    }

    private void SaveChanges(){
        Announcement currAnnouncement = Announcement.AnnouncementList.get(announcementId);
        String currDate = currAnnouncement.getDate();
        String currTime = currAnnouncement.getTime();
        String title = announcement_title_edit.getText().toString();
        String author = announcement_author_edit.getText().toString();
        String content = announcement_content_edit.getText().toString();

        Announcement newAnnouncement = new Announcement(currDate,currTime,title,author,content);
        Announcement.AnnouncementList.set(announcementId,newAnnouncement);

        storedAnnouncements.child("announcement"+announcementId).setValue(newAnnouncement);

        SendUserToAnnouncementsActivity();
    }
}