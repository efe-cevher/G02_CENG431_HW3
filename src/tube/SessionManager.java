package tube;

public class SessionManager {
    private User user;
    private FrameManager frame;

    public SessionManager(User currentUser, FrameManager frame) {
        this.user = currentUser;
        this.frame = frame;
    }

    public User getUser() {
        return user;
    }

    public void openMainMenu(){
        MenuView menuView = new MenuView(frame);
        MenuController menuController = new MenuController(menuView, this);
    }

    public void openBrowseUsers(){
        UserHandler dataHandler = new UserHandler();
        BrowseUsersView browseUsersView = new BrowseUsersView(frame, dataHandler.getUsernames(), user);
        BrowseUsersController browseUsersController = new BrowseUsersController(browseUsersView, this);
    }

    public void openBrowseWatchlists(){
        BrowseWatchlistView browseWatchlistView = new BrowseWatchlistView(frame, user);
        BrowseWatchlistController browseWatchlistController = new BrowseWatchlistController(browseWatchlistView, this);
    }

    public void openBrowseAllVideos(){
        BrowseVideosView browseVideosView = new BrowseVideosView(frame);
        BrowseVideosController browseVideosController = new BrowseVideosController(browseVideosView, this);
    }

    public void openWatchlist(Watchlist watchlist){
        WatchlistView watchlistView = new WatchlistView(frame, watchlist);
        WatchlistController watchlistController = new WatchlistController(watchlistView, watchlist, this);
    }

    public void openVideo(Video video, Watchlist watchlist){
        VideoView videoView = new VideoView(frame, video);
        VideoController videoController = new VideoController(video, videoView, watchlist, this);
    }

    public void logout(){
        LoginView loginView = new LoginView(frame);
        LoginController loginController = new LoginController(loginView);
    }

}
