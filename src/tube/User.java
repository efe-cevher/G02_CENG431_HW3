package tube;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlType(propOrder={"username", "password", "followings", "followers", "likedVideos", "dislikedVideos", "watchlists"})
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends Observable {

    private String username;

    private String password;

    @XmlElementWrapper(name="followings")
    @XmlElement(name="following")
    private List<String> followings;

    @XmlElementWrapper(name="followers")
    @XmlElement(name="follower")
    private List<String> followers;


    @XmlElementWrapper(name="likedVideos")
    @XmlElement(name="likedVideo")
    private List<Integer> likedVideos;


    @XmlElementWrapper(name="dislikedVideos")
    @XmlElement(name="dislikedVideo")
    private List<Integer> dislikedVideos;

    @XmlElementWrapper(name="watchlists")
    @XmlElement(name="watchlist")
    private List<Watchlist> watchlists;

    public User(String username, String password, List<String> following, List<String> followers, List<Integer> likedVideos, List<Integer> dislikedVideos, List<Watchlist> watchlists) {
        this.username = username;
        this.password = password;
        this.followings = following;
        this.followers = followers;
        this.likedVideos = likedVideos;
        this.dislikedVideos = dislikedVideos;
        this.watchlists = watchlists;
    }

    public User(User user){
        this(user.getUsername(), user.getPassword(), user.getFollowing(), user.getFollowers(), user.getLikedVideos(), user.getDislikedVideos(), user.getWatchlists());
    }

    public User(){
        this("no-name", "123456", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getFollowing() {
        List<String> copyFollowings = new ArrayList<>(followings.size());
        copyFollowings.addAll(followings);
        return copyFollowings;
    }


    public List<String> getFollowers(){
        List<String> copyFollowers = new ArrayList<>(followers.size());
        copyFollowers.addAll(followers);
        return copyFollowers;
    }

    public boolean removeFromDislikes(int videoId){
        if(dislikedVideos.contains(videoId)){
            dislikedVideos.remove((Integer) videoId);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean removeFromLikes(int videoId){
        if(likedVideos.contains(videoId)){
            likedVideos.remove((Integer) videoId);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean addToLikes(int videoId){
        if(!likedVideos.contains(videoId)){
            likedVideos.add(videoId);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    public boolean addToDislikes(int videoId){
        if(!dislikedVideos.contains(videoId)){
            dislikedVideos.add(videoId);
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

    public void follow(String user){
        if(!followings.contains(user)){
            followings.add(user);
            setChanged();
            notifyObservers();
        }
    }

    public List<Watchlist> getWatchlists() {
        return watchlists;
    }

    public void setWatchlists(List<Watchlist> watchlists) {
        if(watchlists == null){
            throw new IllegalArgumentException("List of watchlists cannot be null");
        }
        this.watchlists = watchlists;
        setChanged();
        notifyObservers();
    }

    public void unfollow(String user) {
        if(followings.contains(user)){
            followings.remove(user);
            setChanged();
            notifyObservers();
        }
    }

    public void addWatchlist(Watchlist watchlist) {
        if(watchlist == null){
            throw new IllegalArgumentException("Watchlist to add cannot be null.");
        }
        this.watchlists.add(watchlist);
        setChanged();
        notifyObservers();
    }

    public void addFollower(String user) {
        if(!followers.contains(user)){
            followers.add(user);
            setChanged();
            notifyObservers();
        }
    }

    public void removeFollower(String user) {
        if(followers.contains(user)){
            followers.remove(user);
            setChanged();
            notifyObservers();
        }
    }

    public List<Integer> getLikedVideos(){
        List<Integer> copyLikedVideos = new ArrayList<>(likedVideos.size());
        copyLikedVideos.addAll(likedVideos);
        return copyLikedVideos;
    }

    public List<Integer> getDislikedVideos(){
        List<Integer> copyDislikedVideos = new ArrayList<>(dislikedVideos.size());
        copyDislikedVideos.addAll(dislikedVideos);
        return copyDislikedVideos;
    }

}
