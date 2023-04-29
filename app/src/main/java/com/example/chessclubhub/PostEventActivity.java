package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostEventActivity extends AppCompatActivity {

    Button eventBack;
    Button eventPost;
    EditText eventTitleEdit, eventDateEdit, eventContentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);
        eventPost = (Button) findViewById(R.id.postEventButton);
        eventPost.setOnClickListener(v1 -> {
            PostEvent();
        });

        eventTitleEdit = (EditText) findViewById(R.id.eventTitleEdit);
        eventDateEdit = (EditText) findViewById(R.id.eventDateEdit);
        eventContentEdit = (EditText) findViewById(R.id.eventContentEdit);

        eventBack = (Button)  findViewById(R.id.eventPostBackButton);
        eventBack.setOnClickListener(v2 ->{
            SendUserToEventsActivity();
        });


    }

    private void PostEvent() {
        String eventTitle = eventTitleEdit.getText().toString();
        String eventDate = eventDateEdit.getText().toString();
        String eventContent = eventContentEdit.getText().toString();

        Event newEvent = new Event(eventDate, eventTitle, eventContent);
        Event.EventList.add(newEvent);
    }
    private void SendUserToEventsActivity() {
        Intent mainIntent = new Intent(this, EventsActivity.class);
        startActivity(mainIntent);
    }
}