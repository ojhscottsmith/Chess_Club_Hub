package com.example.chessclubhub;

import java.util.*;

//The announcement class, which defines the structure of a Chess Club announcement.
//Contains the following attributes:
// * String date: the date the announcement was posted
// * String time: the time of day the announcement was posted (digital format)
// * String title: the title of the announcement
// * String author: the full name of the person who posted the announcement
// * String content: description of the announcement itself
public class Announcement {

    //Declaring the object fields
    private String date, time, title, author, content;

    //List object to store multiple Announcement objects
    static public ArrayList<Announcement> AnnouncementList = new ArrayList<>();

    //Constructors
    public Announcement(){}
    public Announcement(String date, String time, String title, String author, String content){
        this.date = date;
        this.time = time;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    //Accessors and mutators
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
