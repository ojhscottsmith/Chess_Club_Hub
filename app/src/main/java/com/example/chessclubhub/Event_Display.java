package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Event_Display extends AppCompatActivity {

    Button eventTitleView, eventDateView, eventContentView;
    Button eventBackButton;

    Button eventDeleteButton;

    public static final String DISPLAY_EVENT = "event to display";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_display);
        eventBackButton = (Button)  findViewById(R.id.eventBackButton);
        eventBackButton.setOnClickListener(v1 ->{
            SendUserToEventsActivity();
        });

        Intent intent = getIntent();
        int eventId = intent.getIntExtra(DISPLAY_EVENT,0);
        Event currEvent= Event.EventList.get(eventId);

        eventTitleView = (Button) findViewById(R.id.eventTitleView);
        eventDateView = (Button) findViewById(R.id.eventDateView);
        eventContentView = (Button) findViewById(R.id.eventContentView);



        eventTitleView.setClickable(false);
        eventDateView.setClickable(false);
        eventContentView.setClickable(false);

        eventTitleView.setText(currEvent.getTitle());
        eventDateView.setText(currEvent.getDate());
        eventContentView.setText(currEvent.getContent());
    }
    private void SendUserToEventsActivity() {
        Intent mainIntent = new Intent(this, EventsActivity.class);
        startActivity(mainIntent);
    }
}