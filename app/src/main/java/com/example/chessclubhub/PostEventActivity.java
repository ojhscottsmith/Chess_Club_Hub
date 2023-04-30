package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

public class PostEventActivity extends AppCompatActivity {

    Button eventBack;
    Button eventPost;

    Button eventEdit;
    EditText eventTitleEdit, eventDateEdit, eventContentEdit;

    public static final String DISPLAY_EVENT = "event to display";

    int eventId = -1;

    boolean fieldIsBlank;

    DatabaseReference storedEvents = FirebaseDatabase.getInstance().getReference().child("events");

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

        eventEdit = (Button) findViewById(R.id.editEventButton);
        eventEdit.setOnClickListener(v3 -> {
            EditEvent();
        });

        Intent intent = getIntent();
        eventId = intent.getIntExtra(DISPLAY_EVENT,-1);

        if(eventId == -1) {
            eventPost.setVisibility(View.VISIBLE);
            eventEdit.setVisibility(View.GONE);
        }
        else {
            Event currEvent = Event.EventList.get(eventId);
            eventPost.setVisibility(View.GONE);
            eventEdit.setVisibility(View.VISIBLE);

            eventTitleEdit.setText(currEvent.getTitle());
            eventDateEdit.setText(currEvent.getDate());
            eventContentEdit.setText(currEvent.getContent());
        }

    }

    private void PostEvent() {
        fieldIsBlank = checkForBlankFields();

        Context context = getApplicationContext();
        int DURATION = Toast.LENGTH_LONG;

        if(fieldIsBlank){
            Toast failToast = Toast.makeText(context,"One or more fields are blank\nTry again",DURATION);
            failToast.show();
        }

        else {

            try {
                SimpleDateFormat eventDateFormatter = new SimpleDateFormat("MM/dd/yyyy");
                eventDateFormatter.setLenient(false);
                Date stringAsDate = eventDateFormatter.parse(eventDateEdit.getText().toString());

                String eventTitle = eventTitleEdit.getText().toString();
                String eventDate = eventDateEdit.getText().toString();
                String eventContent = eventContentEdit.getText().toString();

                Event newEvent = new Event(eventDate, eventTitle, eventContent);
                Event.EventList.add(newEvent);

                eventId = Event.EventList.indexOf(newEvent);
                storedEvents.child("event" + eventId).setValue(newEvent);

                Toast successToast = Toast.makeText(context, "Event Posted!", DURATION);
                successToast.show();
                SendUserToEventsActivity();
            }
            catch(ParseException e) {
                Toast failToast = Toast.makeText(context,"Incorrect date format\nUse MM/dd/yyyy format",DURATION);
                failToast.show();
            }

        }
    }
    private void SendUserToEventsActivity() {
        Intent mainIntent = new Intent(this, EventsActivity.class);
        startActivity(mainIntent);
    }

    private void EditEvent(){
        fieldIsBlank = checkForBlankFields();

        Context context = getApplicationContext();
        int DURATION = Toast.LENGTH_LONG;

        if(fieldIsBlank){
            Toast failToast = Toast.makeText(context,"One or more fields are blank\nTry again",DURATION);
            failToast.show();
        }

        else {

            try {
                SimpleDateFormat eventDateFormatter = new SimpleDateFormat("MM/dd/yyyy");
                eventDateFormatter.setLenient(false);
                Date stringAsDate = eventDateFormatter.parse(eventDateEdit.getText().toString());

                String eventTitle = eventTitleEdit.getText().toString();
                String eventDate = eventDateEdit.getText().toString();
                String eventContent = eventContentEdit.getText().toString();

                Event editedEvent = new Event(eventDate, eventTitle, eventContent);

                Event.EventList.set(eventId, editedEvent);

                storedEvents.child("event" + eventId).setValue(editedEvent);

                Toast successToast = Toast.makeText(context, "Changes saved!", DURATION);
                successToast.show();

                Intent eventDisplayIntent = new Intent(this, Event_Display.class);
                eventDisplayIntent.putExtra(DISPLAY_EVENT, eventId);
                startActivity(eventDisplayIntent);
            }
            catch(ParseException e){
                Toast failToast = Toast.makeText(context,"Incorrect date format\nUse MM/dd/yyyy format",DURATION);
                failToast.show();
            }
        }

    }

    boolean checkForBlankFields() {
        EditText[] eventFields = {eventTitleEdit,eventDateEdit,eventContentEdit};
        boolean emptyFieldFlag = false;
        for(int i = 0; i < eventFields.length; i++){
            if(eventFields[i].getText().toString().trim().equals("")) emptyFieldFlag = true;
        }
        return emptyFieldFlag;
    }
}