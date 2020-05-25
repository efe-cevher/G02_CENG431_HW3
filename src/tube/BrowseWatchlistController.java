package tube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class BrowseWatchlistController {
    private final SessionManager session;
    private final BrowseWatchlistView browseWatchlistView;
    private List<Watchlist> allWatchlists;
    private final UserHandler dataHandler;

    public BrowseWatchlistController(BrowseWatchlistView browseWatchlistView, SessionManager session) {
        this.session = session;
        this.browseWatchlistView = browseWatchlistView;
        this.dataHandler = new UserHandler();
        this.allWatchlists = getAllWatchlists();

        session.getUser().addObserver(browseWatchlistView);
        session.getUser().addObserver(dataHandler);

        browseWatchlistView.setFollowingsWatchlists(getFollowingsWatchlists());

        browseWatchlistView.addCreateWatchlistButton(new createWatchlistActionListener());
        browseWatchlistView.addOpenWatchlistButton(new openWatchlistActionListener());
        browseWatchlistView.addMainMenuActionListener(new mainMenuActionListener());
        browseWatchlistView.addOpenWatchlistMouseListener(new openWatchlistMouseListener());

    }

    private class createWatchlistActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = browseWatchlistView.getUserInput("Watch List title:");
            session.getUser().addWatchlist(new Watchlist(new ArrayList<>(),title));
            allWatchlists = getAllWatchlists();
        }
    }

    private class openWatchlistActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = browseWatchlistView.getSelectedListIndex();
            Watchlist watchlist = allWatchlists.get(i);
            session.openWatchlist(watchlist);
        }
    }

    private class mainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            session.openMainMenu();
        }
    }

    private  class openWatchlistMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int i = browseWatchlistView.getSelectedListIndex();
            Watchlist watchlist = allWatchlists.get(i);

            session.openWatchlist(watchlist);
        }
        @Override
        public void mousePressed(MouseEvent e) { }
        @Override
        public void mouseReleased(MouseEvent e) { }
        @Override
        public void mouseEntered(MouseEvent e) { }
        @Override
        public void mouseExited(MouseEvent e) { }
    }

    private List<Watchlist> getAllWatchlists() {
        List<Watchlist> allWatchlists = new ArrayList<>();
        allWatchlists.addAll(session.getUser().getWatchlists());
        List<Watchlist> followingsWatchlists = getFollowingsWatchlists();
        allWatchlists.addAll(followingsWatchlists);
        return allWatchlists;
    }

    private List<Watchlist> getFollowingsWatchlists(){
        List<Watchlist> followingsWatchlists = new ArrayList<>();
        for(String username: session.getUser().getFollowing()){
            followingsWatchlists.addAll(dataHandler.get(username).getWatchlists());
        }
        return followingsWatchlists;
    }

}
