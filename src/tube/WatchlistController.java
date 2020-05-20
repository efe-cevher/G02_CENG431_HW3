package tube;

import java.util.*;

public class WatchlistController {

    private WatchlistView watchlistView;
    private Watchlist watchlist;

    public WatchlistController(Watchlist watchlist){
        this.watchlistView = new WatchlistView(watchlist, this);
        this.watchlist = watchlist;
        watchlist.addObserver(watchlistView);
    }

    public void onAddVideo(){
        int id = Integer.parseInt(watchlistView.getUserInput("Video id:"));
        String title = watchlistView.getUserInput("Video title:");
        String content = watchlistView.getUserInput("Video content:");
        Date date = new Date();
        int likes = 0;
        int dislikes = 0;
        List<Comment> comments = new ArrayList<Comment>();
        watchlist.add(new Video(id, title, content, date, likes, dislikes, comments));
    }

    public void onRemoveVideo(){
        int id = Integer.parseInt(watchlistView.getUserInput("Video id:"));
        watchlist.remove(id);
    }
}
