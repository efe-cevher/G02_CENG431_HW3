package tube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchlistController {

    private final WatchlistView watchlistView;
    private final Watchlist watchlist;
    private User currentUser;

    public WatchlistController(WatchlistView watchlistView, Watchlist watchlist, User user){
        this.watchlistView = watchlistView;
        this.watchlist = watchlist;
        this.currentUser = user;

        watchlist.addObserver(watchlistView);
        watchlistView.addGoToVideoActionListener(new goToVideoActionListener());
        watchlistView.addMainMenuActionListener(new mainMenuActionListener());
        watchlistView.addDeleteVideoActionListener(new deleteVideoActionListener());
    }

    private class deleteVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(currentUser.getWatchlists().contains(watchlist)) {
                int videoIndex = watchlistView.getSelectedListIndex();
                int videoId = watchlist.getVideos().get(videoIndex);
                watchlist.remove(videoId);
            }else{
                //Not your watchlist! cant remove
            }
        }
    }

    private class goToVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = watchlistView.getSelectedListIndex();
            Integer videoId = watchlist.getVideos().get(i);
            //video from videoId?
            DataHandler dataHandler = new DataHandler();
            Video selectedVideo = dataHandler.getVideo(videoId);
            VideoView videoView = new VideoView(watchlistView.getFrame(), selectedVideo);
            VideoController videoController = new VideoController(selectedVideo, videoView, currentUser);
        }
    }

    private class mainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuView menuView = new MenuView(watchlistView.getFrame());
            MenuController menuController = new MenuController(menuView, currentUser);
        }
    }

}
