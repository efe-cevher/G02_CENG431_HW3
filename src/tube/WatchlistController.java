package tube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class WatchlistController {

    private WatchlistView watchlistView;
    private Watchlist watchlist;

    public WatchlistController(Watchlist watchlist, WatchlistView watchlistView){
        this.watchlistView = watchlistView;
        this.watchlist = watchlist;
        watchlist.addObserver(watchlistView);
        watchlistView.addGoToVideoActionListener(new goToVideoActionListener());
        watchlistView.addMainMenuActionListener(new mainMenuActionListener());
        watchlistView.addLogOutActionListener(new logOutActionListener());
        watchlistView.addAddVideoActionListener(new addVideoActionListener());
        watchlistView.addDeleteVideoActionListener(new deleteVideoActionListener());
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

    private class addVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class deleteVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    private class goToVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {}
    }

    private class mainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {}
    }

    private class logOutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {}
    }
}
