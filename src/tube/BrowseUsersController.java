package tube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseUsersController {

    private final BrowseUsersView browseUsersView;
    private final User currentUser;
    private final DataHandler dataHandler;

    public BrowseUsersController(BrowseUsersView browseUsersView, User user){
        this.dataHandler =  new DataHandler();
        this.browseUsersView = browseUsersView;
        this.currentUser = user;

        user.addObserver(dataHandler);
        user.addObserver(browseUsersView);

        browseUsersView.addFollowActionListener(new FollowActionListener());
        browseUsersView.addUnfollowActionListener(new UnfollowActionListener());
        browseUsersView.addMainMenuActionListener(new MainMenuActionListener());
    }

    private class FollowActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User targetUser = dataHandler.getUser(browseUsersView.getSelectedUsername());
            currentUser.follow(targetUser.getUsername());
            targetUser.addFollower(currentUser.getUsername());
        }
    }

    private class UnfollowActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User targetUser = dataHandler.getUser(browseUsersView.getSelectedUsername());
            currentUser.unfollow(targetUser.getUsername());
            targetUser.removeFollower(currentUser.getUsername());
        }
    }

    private class MainMenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MenuView menuView = new MenuView(browseUsersView.getFrame());
            MenuController menuController = new MenuController(menuView, currentUser);
        }
    }
}
