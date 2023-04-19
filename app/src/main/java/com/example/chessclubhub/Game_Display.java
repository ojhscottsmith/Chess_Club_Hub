package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Game_Display extends AppCompatActivity {

    Button gameTitleView, gameDateView, gameContentView;
    Button gameBackButton;

    public static final String DISPLAY_GAME = "game to display";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);
        gameBackButton = (Button)  findViewById(R.id.gameBackButton);
        gameBackButton.setOnClickListener(v1 ->{
            SendUserTogamesActivity();
        });

        Intent intent = getIntent();
        int gameId = intent.getIntExtra(DISPLAY_GAME,0);
        Game currgame= Game.GameList.get(gameId);

        gameTitleView = (Button) findViewById(R.id.gameTitleView);
        gameDateView = (Button) findViewById(R.id.gameDateView);
        gameContentView = (Button) findViewById(R.id.gameContentView);

        gameTitleView.setClickable(false);
        gameDateView.setClickable(false);
        gameContentView.setClickable(false);

        gameTitleView.setText(currgame.getTitle());
        gameDateView.setText(currgame.getDate());
        gameContentView.setText(currgame.getContent());
    }
    private void SendUserTogamesActivity() {
        Intent mainIntent = new Intent(this, GamesActivity.class);
        startActivity(mainIntent);
    }
}