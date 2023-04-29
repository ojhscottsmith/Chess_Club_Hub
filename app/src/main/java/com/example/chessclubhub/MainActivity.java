package com.example.chessclubhub;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

//Controller class for the Home page
//Defines logic for the components defined in activity_main.xml

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DISPLAY_ANNOUNCEMENT = "announcement to display";

    //Declare needed components
    Button announcementBrief;
    Button eventsBrief;

    //Defining menu bar ourselves, to allow for more customization
    ImageButton homePage;
    ImageButton gamePage;
    ImageButton eventPage;
    ImageButton announcementPage;
    ImageButton loginPage;

    //For briefs
    String lastAnnouncementTitle;
    String newEvents;

    //For testing purposes
    final int DURATION = Toast.LENGTH_LONG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Announcement.AnnouncementList.size()==0) {
            Announcement.AnnouncementList.add(new Announcement("04/08/2022", "17:30", "Chess in VLB", "Bruce Wayne", "Join me and some friends for Chess in VLB"));
            Announcement.AnnouncementList.add(new Announcement("05/01/2022", "12:00", "Chess in Lesher Lounge", "Elizabeth Carol", "Chess in Lesher Lounge, be there or be square"));
            Announcement.AnnouncementList.add(new Announcement("04/10/2022", "10:00", "South Lounge Tournament", "Sucy Timberlake", "We are having a Chess Tournament in South Lounge. Anyone is welcome!"));
        }

        if(Event.EventList.size()==0) {
            Event.EventList.add(new Event("02/18/2023", "Standing Stone Tournament", "The monthly SSCC Tournament! Join us in the Cafe to play tournament-style chess games, in the round robin format, and get a chance to win $30 in store credit!"));
            Event.EventList.add(new Event("03/01/2023", "Charity Chess Night", "In this month's campus tournament, along with the normal prizes given for first, second, and third, we will also give all funds from the ticket sales to UNICEF. Join the tournament for a cause!"));
            Event.EventList.add(new Event("06/27/2023", "June Blitz", "A fun one: the Chess Alliance will be going to Pittsburgh for a USCF-sponsored tournament! Sign up here: https://rb.gy/tdmnp"));
        }

        if(Game.GameList.size()==0){
            Game.GameList.add(new Game("10/01/1885","Opera Game", "Paris, France", "Karl II", "Paul Morphy", "1-0", "1. e4 e5 2. Nf3 d6 3. d4 Bg4 4. dxe5 Bxf3 5. Qxf3 dxe5 6. Bc4 Nf6 7. Qb3 Qe7 8. Nc3 c6 9. Bg5 b5 10. Nxb5 cxb5 11. Bxb5+ Nbd7 12. 0-0-0 Rd8 13. Rxd7 Rxd7 14. Rd1 Qe6 15. Bxd7+ Nxd7 16. Qb8+ Nxb8 17. Rd8#"));
            Game.GameList.add(new Game("04/20/2023","Chess.com","Chess.com", "EzyFreeezy", "vinash101", "1/2-1/2", "1. e4 c6 2. d4 d5 3. e5 Bf5 4. Bd3 Bxd3 5. Qxd3 e6 6. Qb3 Qb6 7. Qg3 Qxd4 8. Qe3 Bc5 9. Qxd4 Bxd4 10. f4 Ne7 11. Nf3 Bb6 12. Nc3 O-O 13. Na4 Nd7 14. Nxb6 Nxb6 15. Bd2 Rfd8 16. O-O-O d4 17. Bb4 Nf5 18. g4 a5 19. Bc5 Ne3 20. Rxd4 Nbc4 21. b3 Rxd4 22. Nxd4 b6 23. bxc4 bxc5 24. Nxc6 Nxg4 25. h3 Nf2 26. Rh2 Ne4 27. h4 Nc3 28. Kb2 Na4+ 29. Kb3 Nb6 30. h5 h6 31. c3 a4+ 32. Kc2 Nxc4 33. Kd3 Nb6 34. Rb2 Ra6 35. Nb8 c4+ 36. Kc2 a3 37. Rb1 Ra7 38. Rxb6 Kf8 39. Nc6 Ra8 40. Rb8+ Rxb8 41. Nxb8 Ke8 42. Nc6 Kd7 43. Na5 Ke7 44. Nxc4 f6 45. exf6+ Kxf6 46. Nxa3 g6 47. hxg6 Kxg6 48. c4 h5 49. c5 h4 50. c6 h3 51. c7 h2 52. c8=Q h1=Q 53. Qxe6+ Kg7 54. Qe7+ Kg6 55. Qe6+ Kg7 56. Qg4+ Kf7 57. Qd7+ Kg6 58. f5+ Kf6 59. Nc4 Qe4+ 60. Kb3 Qxf5 61. Qxf5+ Kxf5 62. a4 Ke6 63. a5 Kd7 64. Kb4 Kc7 65. Kb5 Kb7 66. a6+ Ka7 67. Na5 Kb8 68. Nc6+ Ka8 69. Kb6"));
            Game.GameList.add(new Game("04/19/2023","Chess.com","Chess.com","martin-779","EzyFreeezy","1-0","1. d4 e6 2. Nf3 Nc6 3. Bf4 Bd6 4. e3 Bxf4 5. exf4 Nf6 6. Bd3 O-O 7. c3 b6 8. O-O Nh5 9. Ng5 g6 10. Nd2 Nxf4 11. Qg4 Nxd3 12. Qh4 h6 13. Qxh6 Re8 14. Qh7+ Kf8 15. Qxf7#"));
        }

        //Binding components with view
        announcementBrief = findViewById(R.id.announcementBrief);
        eventsBrief = findViewById(R.id.eventsBrief);

        homePage = findViewById(R.id.home_page);
        gamePage = findViewById(R.id.game_page);
        eventPage = findViewById(R.id.event_page);
        announcementPage = findViewById(R.id.announcement_page);
        loginPage = findViewById(R.id.login_page);

        announcementBrief.setOnClickListener(this);
        lastAnnouncementTitle = Announcement.AnnouncementList.get(Announcement.AnnouncementList.size()-1).getTitle();
        announcementBrief.setText(getString(R.string.home_page_announcement_title) + "\n" + lastAnnouncementTitle);
        newEvents = Event.EventList.get(Event.EventList.size()-1).toString() + "\n" + Event.EventList.get(Event.EventList.size()-2).toString();
        eventsBrief.setOnClickListener(this);
        eventsBrief.setText(getString(R.string.home_page_event_title) + "\n" + newEvents);

        homePage.setOnClickListener(this);
        gamePage.setOnClickListener(this);
        eventPage.setOnClickListener(this);
        announcementPage.setOnClickListener(this);
        loginPage.setOnClickListener(this);

    }

    private void SendUserToAnnouncementsActivity() {
        Intent mainIntent = new Intent(MainActivity.this, AnnouncementsActivity.class);
        startActivity(mainIntent);
    }

    private void SendUserToEventsActivity(){
        Intent eventBriefIntent = new Intent(this, EventsActivity.class);
        startActivity(eventBriefIntent);
    }

    private void SendUserToGameActivity() {
        Intent mainIntent = new Intent(this, GamesListActivity.class);
        startActivity(mainIntent);
    }

    private void SendUserToLoginActivity(){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.announcementBrief:
                //Redirect to the newest announcement
                Intent announcementBriefIntent = new Intent(MainActivity.this,Post_Display.class);
                announcementBriefIntent.putExtra(DISPLAY_ANNOUNCEMENT,Announcement.AnnouncementList.size()-1);
                componentCalled("Redirects to most recent announcement");
                startActivity(announcementBriefIntent);
                break;

            case R.id.eventsBrief:
                //redirect to the events list
                Intent eventBriefIntent = new Intent(this, EventsActivity.class);
                startActivity(eventBriefIntent);
                componentCalled("Redirects to events list");
                break;

            case R.id.home_page:
                componentCalled("Redirects to home page");
                break;

            case R.id.game_page:
                SendUserToGameActivity();
                componentCalled("Redirects to game page");
                break;

            case R.id.event_page:
                componentCalled("Redirects to events page");
                SendUserToEventsActivity();
                break;

            case R.id.announcement_page:
                SendUserToAnnouncementsActivity();
                componentCalled("Redirects to announcements page");
                break;

            case R.id.login_page:
                SendUserToLoginActivity();
                componentCalled("Redirects to admin login page");
                break;
        }
    }

    //Testing if interaction with component functions
    void componentCalled(String displayText){
        Context context = getApplicationContext();

        Toast debugger = Toast.makeText(context,displayText,DURATION);
        debugger.show();
    }
}