package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Game_Display extends AppCompatActivity {

    Button gameDateView, gameEventNameView, gameEventSiteView, gameWhiteView, gameBlackView, gameMovesTitle, gameMovesView;
    Button gameBackToDetailButton;
//    Button gameRecorderTab;
    Button gameExportButton;

    Game currGame;

    //Used when displaying a single game
    public static final String GAME_TO_DISPLAY = "individual game to display";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);
        gameBackToDetailButton = (Button)  findViewById(R.id.gameBackToDetailButton);
        gameBackToDetailButton.setOnClickListener(v1 ->{
            SendUserToGameDetail();
        });

        gameExportButton = (Button) findViewById(R.id.gameExportButton);
        gameExportButton.setOnClickListener(v2 -> {
            exportGame();
        });

//        gameRecorderTab = (Button) findViewById(R.id.gameRecorderTab);
//        gameRecorderTab.setOnClickListener(v3 -> {
//            SendUserToGameRecorder();
//        });

        Intent intent = getIntent();
        int gameId = intent.getIntExtra(GAME_TO_DISPLAY,0);
        currGame= Game.GameList.get(gameId);

        gameDateView = (Button) findViewById(R.id.gameDateView);
        gameEventNameView = (Button) findViewById(R.id.gameNameView);
        gameEventSiteView = (Button) findViewById(R.id.gameSiteView);
        gameWhiteView= (Button) findViewById(R.id.gameWhiteView);
        gameBlackView = (Button) findViewById(R.id.gameBlackView);
        gameMovesTitle = (Button) findViewById(R.id.gameMoveTitleView);
        gameMovesView = (Button) findViewById(R.id.gameMovesView);


        gameDateView.setClickable(false);
        gameEventNameView.setClickable(false);
        gameEventSiteView.setClickable(false);
        gameWhiteView.setClickable(false);
        gameBlackView.setClickable(false);
        gameMovesTitle.setClickable(false);
        gameMovesView.setClickable(false);

        gameDateView.setText(currGame.getDate());
        gameEventNameView.setText(currGame.getName());
        gameEventSiteView.setText(currGame.getSite());
        gameWhiteView.setText(currGame.getWhite());
        gameBlackView.setText(currGame.getBlack());
        gameMovesView.setText(currGame.getMoves());

        gameMovesTitle.setText(R.string.game_moves_title_text);
    }
    private void SendUserToGameDetail() {
        Intent detailIntent = new Intent(this, GameDetail.class);
        startActivity(detailIntent);
    }
//
//    Event that directs user to a view where they can post their own game
//    private void SendUserToGameRecorder() {
//        Intent gameRecorderIntent = new Intent(this, GamePostActivity.class);
//        startActivity(gameRecorderIntent);
//    }

    private void exportGame(){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Game Data", currGame.toString());
        clipboard.setPrimaryClip(clip);

        final int DURATION = Toast.LENGTH_LONG;
        Context context = getApplicationContext();
        Toast debugger = Toast.makeText(context,".PGN Copied to Clipboard",DURATION);
        debugger.show();
    }
}