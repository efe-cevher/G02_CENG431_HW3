package tube;

import java.util.*;

public class Video extends Observable {

    private int id;
    private String title;
    private String content;
    private Date date;
    private int likes;
    private int dislikes;
    private Map<String,String> comments;
    private List<Observer> observers;

    public Video(int id, String title, String content, Date date, int likes, int dislikes, Map<String, String> comments, List<Observer> observers) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
        this.comments = comments;
        this.observers = observers;
    }


}
