package tube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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


    private class goToVideoActionListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int i = watchlistView.getSelectedListIndex();
            Integer videoId = watchlist.getVideos().get(i);
            //video from videoId?
            //VideoView videoView = new VideoView(watchlistView.getFrame(), videoId);
            //VideoController videoController = new VideoController(videoView, watchlist);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    private class mainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
