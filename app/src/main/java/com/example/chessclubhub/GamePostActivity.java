package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GamePostActivity extends AppCompatActivity {

    ImageButton home, event, announcement, login;
    Button gameLogTab, gameRecorderTab;
    EditText game_date_edit, game_name_edit, game_site_edit, game_white_edit, game_black_edit, game_result_edit, game_moves_edit;
    Button gameSaveButton;

    Button gameSaveChangesButton;

    Button gameBackButton;

    public static final String GAME_TO_DISPLAY = "individual game to display";

    int gameId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_post);

        gameSaveButton = (Button) findViewById(R.id.gameSaveButton);
        gameSaveButton.setOnClickListener(v1 -> {
            postGameEvent();
        });

        game_date_edit = (EditText) findViewById(R.id.gameDateEditText);
        game_name_edit = (EditText) findViewById(R.id.gameNameEditText);
        game_site_edit = (EditText) findViewById(R.id.gameSiteEditText);
        game_white_edit = (EditText) findViewById(R.id.gameWhiteEditText);
        game_black_edit = (EditText) findViewById(R.id.gameBlackEditText);
        game_result_edit = (EditText) findViewById(R.id.gameResultEditText);
        game_moves_edit = (EditText) findViewById(R.id.gameMovesEditText);

        gameLogTab = (Button)  findViewById(R.id.gameLogTab);
        gameLogTab.setOnClickListener(v2 ->{
            SendUserToGameLog();
        });

        gameRecorderTab = (Button) findViewById(R.id.gameRecorderTab);

        home = (ImageButton) findViewById(R.id.home_page);
        home.setOnClickListener(v1 -> {
            SendUserToHomeActivity();
        });

        event = (ImageButton) findViewById(R.id.event_page);
        event.setOnClickListener(v2 -> {
            SendUserToEventsActivity();
        });

        announcement = (ImageButton) findViewById(R.id.announcement_page);
        announcement.setOnClickListener(v3 -> {
            SendUserToAnnouncementsActivity();
        });

        login = (ImageButton) findViewById(R.id.login_page);
        login.setOnClickListener(v4 -> {
            SendUserToLoginActivity();
        });

        gameSaveChangesButton = (Button) findViewById(R.id.gameSaveChanges);
        gameSaveChangesButton.setOnClickListener(v5 -> {
            SaveChanges();
        });

        gameBackButton = (Button) findViewById(R.id.gameBackButton);
        gameBackButton.setOnClickListener(v6 -> {
            SendUserBackToGame();
        });

        Intent intent = getIntent();
        gameId = intent.getIntExtra(GAME_TO_DISPLAY,-1);

        if(gameId == -1){
            gameSaveChangesButton.setVisibility(View.GONE);
            gameBackButton.setVisibility(View.GONE);
            gameSaveButton.setVisibility(View.VISIBLE);
            gameRecorderTab.setVisibility(View.VISIBLE);
            gameLogTab.setVisibility(View.VISIBLE);
        }
        else {
            gameSaveChangesButton.setVisibility(View.VISIBLE);
            gameBackButton.setVisibility(View.VISIBLE);
            gameSaveButton.setVisibility(View.GONE);
            gameRecorderTab.setVisibility(View.GONE);
            gameLogTab.setVisibility(View.GONE);

            Game currGame = Game.GameList.get(gameId);

            game_date_edit.setText(currGame.getDate());
            game_name_edit.setText(currGame.getName());
            game_site_edit.setText(currGame.getSite());
            game_white_edit.setText(currGame.getWhite());
            game_black_edit.setText(currGame.getBlack());
            game_result_edit.setText(currGame.getResult());
            game_moves_edit.setText(currGame.getMoves());;
        }

    }

    private void postGameEvent() {
        String gameDate = game_date_edit.getText().toString();
        String gameName = game_name_edit.getText().toString();
        String gameSite = game_site_edit.getText().toString();
        String gameWhite = game_white_edit.getText().toString();
        String gameBlack = game_black_edit.getText().toString();
        String gameResult = game_result_edit.getText().toString();
        String gameMoves = game_moves_edit.getText().toString();

        Game newGame = new Game(gameDate,gameName,gameSite,gameBlack,gameWhite,gameResult,gameMoves);
        Game.GameList.add(newGame);
    }

    private void SendUserToGameLog() {
        Intent gameLogIntent = new Intent(this, GamesListActivity.class);
        startActivity(gameLogIntent);

        final int DURATION = Toast.LENGTH_LONG;
        Context context = getApplicationContext();
        Toast debugger = Toast.makeText(context,"Game Posted!",DURATION);
        debugger.show();
    }
    private void SendUserToLoginActivity(){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }

    private void SendUserToEventsActivity() {
        Intent displayIntent = new Intent(this,EventsActivity.class);
        startActivity(displayIntent);
    }

    private void SendUserToGamePostActivity(){
        Intent displayIntent = new Intent(this,GamePostActivity.class);
        startActivity(displayIntent);
    }

    private void SendUserToHomeActivity(){
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
    }

    private void SendUserToAnnouncementsActivity(){
        Intent announcementIntent = new Intent(this, AnnouncementsActivity.class);
        startActivity(announcementIntent);
    }

    private void SendUserBackToGame(){
        Intent gameDisplayIntent = new Intent(this, Game_Display.class);
        gameDisplayIntent.putExtra(GAME_TO_DISPLAY,gameId);
        startActivity(gameDisplayIntent);
    }

    private void SaveChanges(){
        String gameDate = game_date_edit.getText().toString();
        String gameName = game_name_edit.getText().toString();
        String gameSite = game_site_edit.getText().toString();
        String gameWhite = game_white_edit.getText().toString();
        String gameBlack = game_black_edit.getText().toString();
        String gameResult = game_result_edit.getText().toString();
        String gameMoves = game_moves_edit.getText().toString();

        Game newGame = new Game(gameDate,gameName,gameSite,gameBlack,gameWhite,gameResult,gameMoves);
        Game.GameList.set(gameId, newGame);
        SendUserToGameLog();
    }
}