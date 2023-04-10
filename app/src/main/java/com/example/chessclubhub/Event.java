package com.example.chessclubhub;

import java.util.*;

//The event class, which defines the structure of a Chess Club event.
//Contains the following attributes:
// * String date: the date the event is planned
// * String title: the title of the event
// * String content: description of the event itself

public class Event {

    //Declaring the object fields
    public String date, title, content;

    //List object to store multiple Event objects
    public static ArrayList<Event> EventList = new ArrayList<>();

    //Constructors
    public Event(){}

    public Event(String date, String title, String content){
        this.date = date;
        this.title = title;
        this.content = content;
    }

    //Accessors and Mutators
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return date + " – " + title;
    }

}
