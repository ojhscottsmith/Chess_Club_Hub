package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Event_Display extends AppCompatActivity {

    Button eventTitleView, eventDateView, eventContentView;
    Button eventBackButton;

    Button eventEditButton, eventDeleteButton;

    public static final String DISPLAY_EVENT = "event to display";

    Event currEvent;

    int eventId;

    DatabaseReference storedEvents = FirebaseDatabase.getInstance().getReference().child("events");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_display);
        eventBackButton = (Button)  findViewById(R.id.eventBackButton);
        eventBackButton.setOnClickListener(v1 ->{
            SendUserToEventsActivity();
        });

        Intent intent = getIntent();
        eventId = intent.getIntExtra(DISPLAY_EVENT,0);
        currEvent= Event.EventList.get(eventId);

        eventTitleView = (Button) findViewById(R.id.eventTitleView);
        eventDateView = (Button) findViewById(R.id.eventDateView);
        eventContentView = (Button) findViewById(R.id.eventContentView);

        eventTitleView.setClickable(false);
        eventDateView.setClickable(false);
        eventContentView.setClickable(false);

        eventTitleView.setText(currEvent.getTitle());
        eventDateView.setText(currEvent.getDate());
        eventContentView.setText(currEvent.getContent());

        eventEditButton = (Button) findViewById(R.id.editEventButton);
        eventEditButton.setOnClickListener(v2 -> {
            EditEvent();
        });

        eventDeleteButton = (Button) findViewById(R.id.deleteEventButton);
        eventDeleteButton.setOnClickListener(v3 -> {
            DeleteEvent();
        });

        if(LoginActivity.loggedIn){
            eventEditButton.setVisibility(View.VISIBLE);
            eventDeleteButton.setVisibility(View.VISIBLE);
        }
        else {
            eventEditButton.setVisibility(View.GONE);
            eventDeleteButton.setVisibility(View.GONE);
        }
    }
    private void SendUserToEventsActivity() {
        Intent mainIntent = new Intent(this, EventsActivity.class);
        startActivity(mainIntent);
    }

    private void EditEvent() {
        Intent editIntent = new Intent(this, PostEventActivity.class);
        editIntent.putExtra(DISPLAY_EVENT, eventId);
        startActivity(editIntent);
    }

    private void DeleteEvent() {
        Event.EventList.remove(currEvent);
        storedEvents.child("event"+eventId).removeValue();
        SendUserToEventsActivity();
    }
}