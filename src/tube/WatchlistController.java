package tube;

import java.awt.*;
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
        watchlistView.addDeleteVideoActionListener(new deleteVideoActionListener());
    }


    private class addVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class deleteVideoActionListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int videoIndex = watchlistView.getSelectedListIndex();
            int videoId = watchlist.getVideos().get(videoIndex);
            watchlist.remove(videoId);
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


    private class goToVideoActionListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int i = watchlistView.getSelectedListIndex();
            Integer videoId = watchlist.getVideos().get(i);
            //video from videoId?
            DataHandler dataHandler = new DataHandler();
            Video selectedVideo = dataHandler.getVideo(videoId);
            VideoView videoView = new VideoView(watchlistView.getFrame(), selectedVideo);
            VideoController videoController = new VideoController(selectedVideo, videoView);
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
            MenuView mainMenu = new MenuView(watchlistView.getFrame());
        }
    }

}
