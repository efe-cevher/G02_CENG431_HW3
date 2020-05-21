package tube;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class User extends Observable {
    private String username;
    private String password;
    private List<User> following;
    private List<User> followers;
    private List<Integer> likedVideos;
    private List<Integer> dislikedVideos;
    private List<Watchlist> watchlists;

    public User(String username, String password, List<User> following, List<User> followers, List<Integer> likedVideos, List<Integer> dislikedVideos, List<Watchlist> watchlists) {
        this.username = username;
        this.password = password;
        this.following = following;
        this.followers = followers;
        this.likedVideos = likedVideos;
        this.dislikedVideos = dislikedVideos;
        this.watchlists = watchlists;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
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
