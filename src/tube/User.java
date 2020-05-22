package tube;

import java.util.*;
import javax.xml.bind.annotation.*;
import java.util.Observer;

@XmlRootElement(name = "user")
@XmlType(propOrder={"username", "password", "followings", "followers", "likedVideos", "dislikedVideos", "watchlists"})
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends Observable {


    private String username;

    private String password;

    @XmlElementWrapper(name="followings")
    @XmlElement(name="following")
    private List<User> followings;

    @XmlElementWrapper(name="followers")
    @XmlElement(name="follower")
    private List<User> followers;


    @XmlElementWrapper(name="likedVideos")
    @XmlElement(name="likedVideo")
    private List<Integer> likedVideos;


    @XmlElementWrapper(name="dislikedVideos")
    @XmlElement(name="dislikedVideo")
    private List<Integer> dislikedVideos;

    @XmlElementWrapper(name="watchlists")
    @XmlElement(name="watchlist")
    private List<Watchlist> watchlists;

    public User(String username, String password, List<User> following, List<User> followers, List<Integer> likedVideos, List<Integer> dislikedVideos, List<Watchlist> watchlists) {
        this.username = username;
        this.password = password;
        this.followings = following;
        this.followers = followers;
        this.likedVideos = likedVideos;
        this.dislikedVideos = dislikedVideos;
        this.watchlists = watchlists;
    }

    public User(){}


    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    /*public void setPassword(String password) {
        this.password = password;
    }*/


    public List<User> getFollowing() {
        return followings;
    }



    public void setFollowing(List<User> following) {
        this.followings = following;
    }


    public List<User> getFollowers() {
        return followers;
    }



    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }


    public List<Integer> getLikedVideos() {
        return likedVideos;
    }



    public void setLikedVideos(List<Integer> likedVideos) {
        this.likedVideos = likedVideos;
    }


    public List<Integer> getDislikedVideos() {
        return dislikedVideos;
    }



    public void setDislikedVideos(List<Integer> dislikedVideos) {
        this.dislikedVideos = dislikedVideos;
    }


    public List<Watchlist> getWatchlists() {
        return watchlists;
    }


    public List<Watchlist> getAllWatchLists(){
        List<Watchlist> watchlists = new ArrayList<>(getWatchlists());
        for(User usr : getFollowing()){
            watchlists.addAll(usr.getWatchlists());
        }
        return watchlists;
    }



    public void setWatchlists(List<Watchlist> watchlists) {
        this.watchlists = watchlists;
        setChanged();
        notifyObservers();
    }


    public void addWatchlist(Watchlist watchlist) {
        this.watchlists.add(watchlist);
        setChanged();
        notifyObservers();
    }

}
