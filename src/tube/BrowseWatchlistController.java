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

    public BrowseWatchlistController(User user, BrowseWatchlistView browseWatchlistView) {
        this.currentUser = user;
        this.browseWatchlistView = browseWatchlistView;

        user.addObserver(browseWatchlistView);
        browseWatchlistView.addCreateWatchlistButton(new createWatchlistActionListener());
        browseWatchlistView.addOpenWatchlistButton(new openWatchlistActionListener());
        browseWatchlistView.addMainMenuActionListener(new mainMenuActionListener());
        browseWatchlistView.addOpenWatchlistMouseListener(new openWatchlistMouseListener());
    }

    private class createWatchlistActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = browseWatchlistView.getUserInput("Watch List title:");
            currentUser.addWatchlist(new Watchlist(new ArrayList<>(),title));

            //problematic
            DataHandler dataHandler = new DataHandler();
            dataHandler.putUser(currentUser);
        }
    }

    private class openWatchlistActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = browseWatchlistView.getSelectedListIndex();
            Watchlist watchlist = currentUser.getAllWatchLists().get(i);
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
            Watchlist watchlist = currentUser.getAllWatchLists().get(i);
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


    public static void main(String[] args) {
        List<Integer> videos1 = new ArrayList<>();
        List<Integer> videos2 = new ArrayList<>();
        List<Integer> videos3 = new ArrayList<>();

        Video video = new Video(1, "why1", "are you gay", new Date(), 0, 0, null);
        Video video2 = new Video(2, "why2", "are you gay", new Date(), 0, 0, null);
        Video video3 = new Video(3, "why3", "are you gay", new Date(), 0, 0, null);
        Video video4 = new Video(4, "why4", "are you gay", new Date(), 0, 0, null);
        Video video5 = new Video(5, "why5", "are you gay", new Date(), 0, 0, null);
        Video video6 = new Video(6, "why6", "are you gay", new Date(), 0, 0, null);

        videos1.add(video.getId());
        videos1.add(video2.getId());
        videos2.add(video3.getId());
        videos2.add(video4.getId());
        videos3.add(video5.getId());
        videos3.add(video6.getId());

        Watchlist w1 = new Watchlist(videos1, "gayisgay1");
        Watchlist w2 = new Watchlist(videos2, "gayisgay2");
        Watchlist w3 = new Watchlist(videos3, "gayisgay3");

        List<Watchlist> watchlists1 = new ArrayList<>();
        List<Watchlist> watchlists2 = new ArrayList<>();

        watchlists1.add(w1);
        watchlists2.add(w2);
        watchlists2.add(w3);

        List<User> users = new ArrayList<>();

        User user2 = new User("heyyy2",null,null,null,null,null,watchlists2);
        User user3 = new User("heyyy3",null,null,null,null,null,watchlists2);
        User user4 = new User("heyyy4",null,null,null,null,null,watchlists2);
        User user5 = new User("heyyy5",null,null,null,null,null,watchlists2);
        User user6 = new User("heyyy6",null,null,null,null,null,watchlists2);

        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        User user1 = new User("heyyy1",null,users,null,null,null,watchlists1);

        BrowseWatchlistView browseWatchlistView = new BrowseWatchlistView(new FrameManager(),user1);
        new BrowseWatchlistController(user1, browseWatchlistView);
    }

}
