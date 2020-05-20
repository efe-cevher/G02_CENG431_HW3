package tube;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Watchlist extends Observable {
    private List<Video> videos;

    public Watchlist(List<Video> videos) {
        this.videos = new ArrayList<Video>(videos.size());
        this.videos.addAll(videos);
    }

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
        for(Video video : videos){
            if(video.getId() == id){
                videos.remove(video);
            }
        }
        setChanged();
        notifyObservers(getVideos());
    }

}
