package tube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class WatchlistController {

    private final WatchlistView watchlistView;
    private final Watchlist watchlist;
    private final VideoDataHandler videoHandler;
    private SessionManager session;

    public WatchlistController(WatchlistView watchlistView, Watchlist watchlist, SessionManager session){
        this.watchlistView = watchlistView;
        this.watchlist = watchlist;
        this.session = session;
        this.videoHandler = new VideoDataHandler();

        watchlistView.setVideoNameMap(getVideoNameMap());

        watchlist.addObserver(watchlistView);
        watchlistView.addGoToVideoActionListener(new goToVideoActionListener());
        watchlistView.addBackActionListener(new backActionListener());
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
            if(i<0 || i>= watchlist.getVideos().size()){
                return;
            }
            Integer videoId = watchlist.getVideos().get(i);
            VideoDataHandler videoHandler = new VideoDataHandler();
            Video selectedVideo = videoHandler.get(videoId);
            session.openVideo(selectedVideo, watchlist);
        }
    }

    private class backActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openBrowseWatchlists();
        }
    }

    private Map<Integer,String> getVideoNameMap(){
        Map<Integer,String> videoNameMap = new HashMap<>();
        for(Video video: videoHandler.getDataMap().values()){
            videoNameMap.put(video.getId(), video.getTitle());
        }
        return videoNameMap;
    }
}
