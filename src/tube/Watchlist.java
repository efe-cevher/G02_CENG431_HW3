package tube;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

@XmlRootElement
public class Watchlist extends Observable {

    @XmlElement
    private String name;

    @XmlElementWrapper(name = "videos")
    @XmlElement(name = "video")
    private List<Integer> videos;

    public Watchlist(List<Integer> videos, String name) {
        this.videos = new ArrayList<>(videos.size());
        this.videos.addAll(videos);
        this.name = name;
    }

    public Watchlist() {
    }

    public String getName() {
        return this.name;
    }

    //Return a copy of videos
    public List<Integer> getVideos() {
        return new ArrayList<>(videos);
    }

    //Add a video to the watchlist
    public void add(Integer videoId) {
        videos.add(videoId);
        setChanged();
        notifyObservers(getVideos());
    }

    //Remove video from the watchlist
    public void remove(int id) {
        videos.removeIf(videoId -> videoId == id);
        setChanged();
        notifyObservers(this);
    }

    @Override
    public String toString() {
        return "Watchlist " + "name='" + name;
    }

}
