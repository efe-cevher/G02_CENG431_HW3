package tube;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Watchlist extends Observable {
    private String name;
    private List<Video> videos;

    public Watchlist(List<Video> videos, String name) {
        this.videos = new ArrayList<Video>(videos.size());
        this.videos.addAll(videos);
        this.name = name;
    }

    public String getName() { return this.name; }


    //Return a copy of videos
    public List<Video> getVideos(){
        return new ArrayList<Video>(videos);
    }

    //Add a video to the watchlist
    public void add(Video video){
        videos.add(video);
        setChanged();
        notifyObservers(getVideos());
    }

    //Remove video from the watchlist
    public void remove(int id){
        videos.removeIf(video -> video.getId() == id);
        setChanged();
        notifyObservers(getVideos());
    }

    @Override
    public String toString() {
        return "Watchlist " + "name='" + name;
    }
}
