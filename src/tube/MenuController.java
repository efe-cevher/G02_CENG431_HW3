package tube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuController {
    MenuView menuView;

    public MenuController(MenuView menuView) {
        this.menuView = menuView;
        menuView.addBrowseUsersActionListener(new BrowseUsersActionListener());
        menuView.addBrowseWatchlistsActionListener(new BrowseWatchlistsActionListener());
        menuView.addLogoutActionListener(new LogoutActionListener());
    }

    private class BrowseUsersActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //BrowseUsersView browseUsersView = new BrowseUsersView(menuView.getFrame(), user);
            //BrowseUsersController browseUsersController = new BrowseUsersController(browseUsersView);
        }
    }

    private class BrowseWatchlistsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //BrowseWatchlistView browseWatchlistView = new BrowseWatchlistView(menuView.getFrame(), user);
        }
    }

    private class LogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
