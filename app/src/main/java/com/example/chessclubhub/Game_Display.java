package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Game_Display extends AppCompatActivity {

    Button gameDateView, gameEventNameView, gameEventSiteView, gameWhiteView, gameBlackView, gameMovesTitle, gameMovesView;
    Button gameBackToDetailButton;
    Button gameExportButton;

    Button gameEditButton, gameDeleteButton;

    Game currGame;

    //Used when displaying a single game
    public static final String GAME_TO_DISPLAY = "individual game to display";

    //Used when displaying the list of games played in an event
    public static final String DISPLAY_GAMES = "games to display";
    public static String displayGameEvent = " ";

    int gameId;

    DatabaseReference storedGames = FirebaseDatabase.getInstance().getReference().child("games");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);

        Intent intent = getIntent();
        gameId = intent.getIntExtra(GAME_TO_DISPLAY,0);
        currGame= Game.GameList.get(gameId);

        gameBackToDetailButton = (Button)  findViewById(R.id.gameBackToDetailButton);
        gameBackToDetailButton.setOnClickListener(v1 ->{
            displayGameEvent = currGame.getName();
            SendUserToGameDetail();
        });

        gameExportButton = (Button) findViewById(R.id.gameExportButton);
        gameExportButton.setOnClickListener(v2 -> {
            exportGame();
        });

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

        if(currGame.getResult().equals("1-0")){
            gameWhiteView.setText(currGame.getWhite() + getString(R.string.won));
            gameBlackView.setText(currGame.getBlack() + getString(R.string.lost));
        }
        else if(currGame.getResult().equals("0-1")){
            gameWhiteView.setText(currGame.getWhite() + getString(R.string.lost));
            gameBlackView.setText(currGame.getBlack() + getString(R.string.won));
        }
        else {
            gameWhiteView.setText(currGame.getWhite() + getString(R.string.draw));
            gameBlackView.setText(currGame.getBlack() + getString(R.string.draw));
        }

        gameMovesView.setText(currGame.getMoves());

        gameMovesTitle.setText(R.string.game_moves_title_text);

        gameEditButton = (Button) findViewById(R.id.gameEditButton);
        gameEditButton.setOnClickListener(v3 -> {
            EditGame();
        });

        gameDeleteButton = (Button) findViewById(R.id.gameDeleteButton);
        gameDeleteButton.setOnClickListener(v4 -> {
            DeleteGame();
        });

        if(LoginActivity.loggedIn){
            gameEditButton.setVisibility(View.VISIBLE);
            gameDeleteButton.setVisibility(View.VISIBLE);
        }
        else {
            gameEditButton.setVisibility(View.GONE);
            gameDeleteButton.setVisibility(View.GONE);
        }


    }
    private void SendUserToGameDetail() {
        Intent detailIntent = new Intent(this, GameDetail.class);
        detailIntent.putExtra(DISPLAY_GAMES,displayGameEvent);
        startActivity(detailIntent);
    }

    private void exportGame(){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Game Data", currGame.toString());
        clipboard.setPrimaryClip(clip);

        final int DURATION = Toast.LENGTH_LONG;
        Context context = getApplicationContext();
        Toast debugger = Toast.makeText(context,".PGN Copied to Clipboard",DURATION);
        debugger.show();
    }

    private void DeleteGame(){
        Game.GameList.remove(currGame);
        storedGames.child("game"+gameId).removeValue();

        Context context = getApplicationContext();
        int DURATION = Toast.LENGTH_LONG;
        Toast deleteToast = Toast.makeText(context, "Game deleted!",DURATION);
        deleteToast.show();

        SendUserToGameDetail();
    }

    private void EditGame(){
        Intent editIntent = new Intent(this, GamePostActivity.class);
        editIntent.putExtra(GAME_TO_DISPLAY,gameId);
        startActivity(editIntent);
    }
}