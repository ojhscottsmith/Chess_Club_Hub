package com.example.chessclubhub;

import java.util.*;

public class Announcement {

    private String date, time, title, author, content;

    static public ArrayList<Announcement> AnnouncementList = new ArrayList<>();

    public Announcement(){}

    public Announcement(String date, String time, String title, String author, String content){

        this.date = date;
        this.time = time;
        this.title = title;
        this.author = author;
        this.content = content;

    }

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

    public void addAnnouncement(String date, String time, String title, String author, String content){
        Announcement newAnnouncement = new Announcement(date,time,title,author,content);
        AnnouncementList.add(newAnnouncement);
    }

}
