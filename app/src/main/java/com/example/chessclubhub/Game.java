package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class Game {

    //Declaring the object fields
    public String date, name, site, black, white, result;
    public String[] moves = new String[200]; //Hard-limit for the number of moves

    //List object to store multiple Event objects
    public static ArrayList<Game> GameList = new ArrayList<>();

    //Constructors
    public Game(){}

    public Game(String date, String name, String site, String black, String white, String result, String[] moves){
        this.date = date;
        this.name = name;
        this.site = site;
        this.black = black;
        this.white = white;
        this.result = result;
        this.moves = moves;
    }

    //Accessors and Mutators
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getBlack() {
        return black;
    }

    public void setBlack(String black) {
        this.black = black;
    }

    public String getWhite() {
        return white;
    }

    public void setWhite(String white) {
        this.white = white;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String[] getMoves() {
        return moves;
    }

    public void setMoves(String[] moves) {
        this.moves = moves;
    }


}