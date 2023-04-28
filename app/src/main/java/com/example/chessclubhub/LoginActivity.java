package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ImageButton homePage, gamePage, eventPage, announcementPage, loginPage;

    EditText usernameEdit, passwordEdit;

    Button loginButton, loginState;

    //App-wide flag to determine login status
    static boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        loginButton.setOnClickListener(v5 -> {
            AuthenticateLogin();
        });

        loginState = (Button) findViewById(R.id.loginState);
        usernameEdit = (EditText) findViewById(R.id.login_user_edit);
        passwordEdit = (EditText) findViewById(R.id.login_pwd_edit);

        if(loggedIn) loginState.setBackgroundColor(getColor(R.color.cch_green));
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

    private void AuthenticateLogin() {
        String enteredUsername = usernameEdit.getText().toString();
        String enteredPassword = passwordEdit.getText().toString();

        Context context = getApplicationContext();
        final int DURATION = Toast.LENGTH_LONG;

        if(enteredUsername.equals(getString(R.string.example_username)) && enteredPassword.equals(getString(R.string.example_password))){
            Toast successToast = Toast.makeText(context,"Succesful User Login!",DURATION);
            successToast.show();
            loginState.setBackgroundColor(getColor(R.color.cch_green));
            loggedIn = true;
        }
        else {
            Toast failToast = Toast.makeText(context,"Incorrect credentials, try again",DURATION);
            failToast.show();
        }
    }
}