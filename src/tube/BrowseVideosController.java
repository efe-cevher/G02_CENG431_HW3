package tube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class BrowseVideosController {

    SessionManager session;
    BrowseVideosView browseVideosView;
    VideoHandler videoHandler;

    public BrowseVideosController(BrowseVideosView browseVideosView, SessionManager session) {
        this.session = session;
        this.browseVideosView = browseVideosView;
        this.videoHandler = new VideoHandler();

        Map<Integer,Video> videoMap = videoHandler.getDataMap();
        browseVideosView.setVideolist(new ArrayList<>(videoMap.values()));
        browseVideosView.addOpenVideoActionListener(new OpenVideoActionListener());
        browseVideosView.addMainMenuActionListener(new MainMenuActionListener());
    }

    private class OpenVideoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openVideo(videoHandler.get(browseVideosView.getSelectedListIndex()), null);
        }
    }

    private class MainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openMainMenu();
        }
    }

}
