package com.example.chessclubhub;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    ImageButton homePage, gamePage, eventPage, announcementPage, loginPage;

    EditText usernameEdit, passwordEdit;

    Button loginButton, logoutButton, loginState;

    //App-wide flag to determine login status
    static boolean loggedIn = false;

    HashMap<String, String> cchLogins = new HashMap<>();

    DatabaseReference storedLogins = FirebaseDatabase.getInstance().getReference().child("logins");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ValueEventListener gamesValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String currUser = String.valueOf(ds.child("username").getValue());
                    String currPass = String.valueOf(ds.child("password").getValue());

                    cchLogins.put(currUser,currPass);
                }

                homePage = (ImageButton) findViewById(R.id.home_page);
                homePage.setOnClickListener(v1 -> {
                    SendUserToHomePage();
                });

                gamePage = (ImageButton) findViewById(R.id.game_page);
                gamePage.setOnClickListener(v2 -> {
                    SendUserToGamePage();
                });

                eventPage = (ImageButton) findViewById(R.id.event_page);
                eventPage.setOnClickListener(v3 -> {
                    SendUserToEventPage();
                });

                announcementPage = (ImageButton) findViewById(R.id.announcement_page);
                announcementPage.setOnClickListener(v4 -> {
                    SendUserToAnnouncementPage();
                });

                loginButton = (Button) findViewById(R.id.loginButton);
                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            String enteredUsername = usernameEdit.getText().toString();
                            String enteredPassword = passwordEdit.getText().toString();

                            Context context = getApplicationContext();
                            final int DURATION = Toast.LENGTH_LONG;

                            if (cchLogins.get(enteredUsername).equals(enteredPassword)) {
                                Toast successToast = Toast.makeText(context, "Successful Admin Login!", DURATION);
                                successToast.show();
                                loginState.setBackgroundColor(getColor(R.color.cch_green));
                                loggedIn = true;

                                loginButton.setVisibility(View.GONE);
                                logoutButton.setVisibility(View.VISIBLE);

                                usernameEdit.getText().clear();
                                passwordEdit.getText().clear();
                            } else {
                                Toast failToast = Toast.makeText(context, "Incorrect credentials, try again", DURATION);
                                failToast.show();
                            }
                        }
                        catch(Exception e){
                            Context context = getApplicationContext();
                            int DURATION = Toast.LENGTH_LONG;
                            Toast successToast = Toast.makeText(context,"Incorrect credentials entered, try again",DURATION);
                            successToast.show();
                        }
                    }
                });

                logoutButton = (Button) findViewById(R.id.logoutButton);
                logoutButton.setOnClickListener(v5 -> {
                    Logout();
                });

                loginState = (Button) findViewById(R.id.loginState);
                usernameEdit = (EditText) findViewById(R.id.login_user_edit);
                passwordEdit = (EditText) findViewById(R.id.login_pwd_edit);

                if(loggedIn){
                    loginState.setBackgroundColor(getColor(R.color.cch_green));
                    loginButton.setVisibility(View.GONE);
                    logoutButton.setVisibility(View.VISIBLE);
                }
                else {
                    loginState.setBackgroundColor(getColor(R.color.cch_maroon));
                    logoutButton.setVisibility(View.GONE);
                    loginButton.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };
        storedLogins.addListenerForSingleValueEvent(gamesValueListener);


    }

    private void SendUserToHomePage(){
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
    }

    private void SendUserToGamePage(){
        Intent gameIntent = new Intent(this, GamesListActivity.class);
        startActivity(gameIntent);
    }

    private void SendUserToEventPage(){
        Intent eventIntent = new Intent(this, EventsActivity.class);
        startActivity(eventIntent);
    }

    private void SendUserToAnnouncementPage(){
        Intent announcementIntent = new Intent(this, AnnouncementsActivity.class);
        startActivity(announcementIntent);
    }

    private void Logout() {
        Context context = getApplicationContext();
        final int DURATION = Toast.LENGTH_LONG;
        loggedIn = false;

        Toast successToast = Toast.makeText(context,"Logged out!",DURATION);
        successToast.show();

        logoutButton.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
        loginState.setBackgroundColor(getColor(R.color.cch_maroon));
    }
}