package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Post_Display extends AppCompatActivity {
    Button back;

    public static final String DISPLAY_ANNOUNCEMENT = "announcement to display";

    Button announcementDateView,announcementTimeView,announcementTitleView,announcementAuthorView,announcementContentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_display);
        back = (Button)  findViewById(R.id.back_button2);
        back.setOnClickListener(v1 ->{
            SendUserToAnnouncementsActivity();
        });

        Intent intent = getIntent();
        int announcementId = intent.getIntExtra(DISPLAY_ANNOUNCEMENT,0);
        Announcement currAnnouncement = Announcement.AnnouncementList.get(announcementId);

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
    }
    private void SendUserToAnnouncementsActivity() {
        Intent mainIntent = new Intent(Post_Display.this, AnnouncementsActivity.class);
        startActivity(mainIntent);
    }
}