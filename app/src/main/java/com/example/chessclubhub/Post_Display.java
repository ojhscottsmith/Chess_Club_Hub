package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Post_Display extends AppCompatActivity {
    Button back;

    public static final String DISPLAY_ANNOUNCEMENT = "announcement to display";

    Announcement currAnnouncement;
    int announcementId;

    Button announcementDateView,announcementTimeView,announcementTitleView,announcementAuthorView,announcementContentView;

    Button announcementEditButton, announcementDeleteButton;

    DatabaseReference storedAnnouncements = FirebaseDatabase.getInstance().getReference().child("announcements");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_display);
        back = (Button)  findViewById(R.id.back_button2);
        back.setOnClickListener(v1 ->{
            SendUserToAnnouncementsActivity();
        });

        Intent intent = getIntent();
        announcementId = intent.getIntExtra(DISPLAY_ANNOUNCEMENT,0);
        currAnnouncement = Announcement.AnnouncementList.get(announcementId);

        announcementDateView = (Button) findViewById(R.id.announcementDateView);
        announcementTimeView = (Button) findViewById(R.id.announcementTimeView);
        announcementTitleView = (Button) findViewById(R.id.announcementTitleView);
        announcementAuthorView = (Button) findViewById(R.id.announcementAuthorView);
        announcementContentView = (Button) findViewById(R.id.announcementContentView);

        announcementDateView.setClickable(false);
        announcementTimeView.setClickable(false);
        announcementTitleView.setClickable(false);
        announcementAuthorView.setClickable(false);
        announcementContentView.setClickable(false);

        announcementDateView.setText(currAnnouncement.getDate());
        announcementTimeView.setText(currAnnouncement.getTime());
        announcementTitleView.setText(currAnnouncement.getTitle());
        announcementAuthorView.setText(currAnnouncement.getAuthor());
        announcementContentView.setText(currAnnouncement.getContent());

        announcementEditButton = (Button) findViewById(R.id.editAnnouncementButton);
        announcementEditButton.setOnClickListener(v5 -> {
            EditAnnouncement();
        });

        announcementDeleteButton = (Button) findViewById(R.id.deleteAnnouncementButton);
        announcementDeleteButton.setOnClickListener(v6 -> {
            DeleteAnnouncement();
        });

        if(LoginActivity.loggedIn){
            announcementEditButton.setVisibility(View.VISIBLE);
            announcementDeleteButton.setVisibility(View.VISIBLE);
        }
        else {
            announcementEditButton.setVisibility(View.GONE);
            announcementDeleteButton.setVisibility(View.GONE);
        }
    }
    private void SendUserToAnnouncementsActivity() {
        Intent mainIntent = new Intent(Post_Display.this, AnnouncementsActivity.class);
        startActivity(mainIntent);
    }

    private void EditAnnouncement(){
        Intent editIntent = new Intent(this, PostActivity.class);
        editIntent.putExtra(DISPLAY_ANNOUNCEMENT, announcementId);
        startActivity(editIntent);
    }

    private void DeleteAnnouncement(){
        Announcement.AnnouncementList.remove(currAnnouncement);
        storedAnnouncements.child("announcement"+announcementId).removeValue();
        SendUserToAnnouncementsActivity();
    }
}