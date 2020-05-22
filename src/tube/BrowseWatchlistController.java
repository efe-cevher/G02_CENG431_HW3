package tube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrowseWatchlistController {
    private User currentUser;
    private BrowseWatchlistView browseWatchlistView;
    private List<Watchlist> allWatchlists;
    private DataHandler dataHandler;

    public BrowseWatchlistController(User user, BrowseWatchlistView browseWatchlistView) {
        this.currentUser = user;
        this.browseWatchlistView = browseWatchlistView;
        this.dataHandler = new DataHandler();

        user.addObserver(browseWatchlistView);
        user.addObserver(dataHandler);

        browseWatchlistView.addCreateWatchlistButton(new createWatchlistActionListener());
        browseWatchlistView.addOpenWatchlistButton(new openWatchlistActionListener());
        browseWatchlistView.addMainMenuActionListener(new mainMenuActionListener());
        browseWatchlistView.addOpenWatchlistMouseListener(new openWatchlistMouseListener());

        this.allWatchlists = new ArrayList<>();
        allWatchlists.addAll(user.getWatchlists());
        for(String username: currentUser.getFollowing()){
            allWatchlists.addAll(dataHandler.getUser(username).getWatchlists());
        }
    }

    private class createWatchlistActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = browseWatchlistView.getUserInput("Watch List title:");
            currentUser.addWatchlist(new Watchlist(new ArrayList<>(),title));
        }
    }

    private class openWatchlistActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = browseWatchlistView.getSelectedListIndex();
            Watchlist watchlist = allWatchlists.get(i);
            WatchlistView watchlistView = new WatchlistView(browseWatchlistView.getFrame(), watchlist);
            WatchlistController watchlistController = new WatchlistController(watchlistView, watchlist, currentUser);
        }
    }

    private class mainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuView menuView = new MenuView(browseWatchlistView.getFrame());
            MenuController menuController = new MenuController(menuView, currentUser);
        }
    }

    private  class openWatchlistMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int i = browseWatchlistView.getSelectedListIndex();

            Watchlist watchlist = allWatchlists.get(i);
            WatchlistView watchlistView = new WatchlistView(browseWatchlistView.getFrame(), watchlist);
            WatchlistController watchlistController = new WatchlistController(watchlistView, watchlist, currentUser);
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

}
