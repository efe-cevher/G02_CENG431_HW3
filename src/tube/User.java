package tube;

import java.util.List;
import java.util.Observer;

public class User {
    private String username;
    private String password;
    private List<User> following;
    private List<User> followers;
    private List<Integer> likedVideos;
    private List<Integer> dislikedVideos;
    private List<Watchlist> watchlists;
    private List<Observer> observers;

    public User(String username, String password, List<User> following, List<User> followers, List<Integer> likedVideos, List<Integer> dislikedVideos, List<Watchlist> watchlists, List<Observer> observers) {
        this.username = username;
        this.password = password;
        this.following = following;
        this.followers = followers;
        this.likedVideos = likedVideos;
        this.dislikedVideos = dislikedVideos;
        this.watchlists = watchlists;
        this.observers = observers;
    }
}
