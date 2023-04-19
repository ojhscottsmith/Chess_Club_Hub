package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class Game {

    //Declaring the object fields
    public String date, title, content, author, time;

    //List object to store multiple Event objects
    public static ArrayList<Game> GameList = new ArrayList<>();

    //Constructors
    public Game(){}

    public Game(String date, String time, String title, String author, String content){
        this.date = date;
        this.time = time;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    //Accessors and Mutators
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}