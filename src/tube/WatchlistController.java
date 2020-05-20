package tube;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WatchlistController {

    private WatchlistView watchlistView;
    private Watchlist watchlist;

    public WatchlistController(Watchlist watchlist){
        this.watchlistView = new WatchlistView(watchlist, this);
        this.watchlist = watchlist;
    }


    public void setWatchlist(Watchlist watchlist){
        this.watchlist = watchlist;
    }

    public Watchlist getWatchlist() { return watchlist; }

    public WatchlistView getWatchlistView() { return watchlistView; }

    public void onAddVideo(){
        int id = Integer.parseInt(watchlistView.getUserInput("Video id:"));
        String title = watchlistView.getUserInput("Video title:");
        String content = watchlistView.getUserInput("Video content:");
        Date date = new Date();
        int likes = 0;
        int dislikes = 0;
        Map<String, String> comments = new HashMap<String, String>();
        watchlist.add(new Video(id, title, content, date, likes, dislikes, comments));
    }

    public void onRemoveVideo(){
        int id = Integer.parseInt(watchlistView.getUserInput("Video id:"));
        watchlist.remove(id);
    }
}
