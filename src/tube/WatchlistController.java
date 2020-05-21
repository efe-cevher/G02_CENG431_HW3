package tube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class WatchlistController {

    private WatchlistView watchlistView;
    private Watchlist watchlist;

    public WatchlistController(WatchlistView watchlistView, Watchlist watchlist){
        this.watchlistView = watchlistView;
        this.watchlist = watchlist;
        watchlist.addObserver(watchlistView);
        watchlistView.addGoToVideoActionListener(new goToVideoActionListener());
        watchlistView.addMainMenuActionListener(new mainMenuActionListener());
        watchlistView.addAddVideoActionListener(new addVideoActionListener());
        watchlistView.addDeleteVideoActionListener(new deleteVideoActionListener());
    }


    private class addVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class deleteVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(watchlistView.getUserInput("Video id:"));
            watchlist.remove(id);
        }
    }


    private class goToVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class mainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
