package tube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchlistController {

    private final WatchlistView watchlistView;
    private final Watchlist watchlist;
    private SessionManager session;

    public WatchlistController(WatchlistView watchlistView, Watchlist watchlist, SessionManager session){
        this.watchlistView = watchlistView;
        this.watchlist = watchlist;
        this.session = session;

        watchlist.addObserver(watchlistView);
        watchlistView.addGoToVideoActionListener(new goToVideoActionListener());
        watchlistView.addMainMenuActionListener(new mainMenuActionListener());
        watchlistView.addDeleteVideoActionListener(new deleteVideoActionListener());
    }

    //Event performed whenever the user selects a video and clicks on 'Delete video' button
    private class deleteVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(session.getUser().getWatchlists().contains(watchlist)) {
                int videoIndex = watchlistView.getSelectedListIndex();
                int videoId = watchlist.getVideos().get(videoIndex);
                watchlist.remove(videoId);
            }else{
                //TODO FEEDBACK
                //Not your watchlist! cant remove
            }
        }
    }

    //Event performed whenever the user selects a video and clicks on 'Watch' button
    private class goToVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = watchlistView.getSelectedListIndex();
            Integer videoId = watchlist.getVideos().get(i);
            //video from videoId?
            VideoHandler videoHandler = new VideoHandler();
            Video selectedVideo = videoHandler.get(videoId);
            session.openVideo(selectedVideo);
        }
    }

    //TODO CHANGE BUTTON TO "BACK" TO BROWSE PLAYLISTS
    private class mainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openBrowseWatchlists();
        }
    }

}
