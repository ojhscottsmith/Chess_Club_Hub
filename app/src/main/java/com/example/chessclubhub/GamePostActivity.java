package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GamePostActivity extends AppCompatActivity {

    Button gameLogTab;
    EditText game_date_edit, game_name_edit, game_site_edit, game_white_edit, game_black_edit, game_result_edit, game_moves_edit;
    Button gameSaveButton;


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
}