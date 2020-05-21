package tube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class BrowseWatchlistView implements Observer {
    private JPanel panel;
    private JButton mainMenuButton;
    private JButton logoutButton;
    private JButton createWatchlistButton;
    private JButton openWatchlistButton;
    private JScrollPane scrollPane;
    private FrameManager frame;
    private User user;

    public BrowseWatchlistView(FrameManager frame, User user) {
        this.frame = frame;
        this.user = user;

        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 450, 250);
        panel.add(scrollPane);

        showLists();

        openWatchlistButton = new JButton("Open Watchlist");
        openWatchlistButton.setBounds(150, 320, 140, 25);
        panel.add(openWatchlistButton);

        createWatchlistButton = new JButton("Create Watchlist");
        createWatchlistButton.setBounds(10, 320, 140, 25);
        panel.add(createWatchlistButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(10, 400, 120, 25);
        panel.add(mainMenuButton);

        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(140, 400, 80, 25);
        panel.add(logoutButton);

        frame.setNewPanel(panel);
    }

    private void showLists() {
        List<Watchlist> userWatchlists = user.getWatchlists();
        DefaultListModel<String> WatchlistsModels = new DefaultListModel<>();
        StringBuilder s = new StringBuilder();
        for (Watchlist wl:userWatchlists ) {
            s.append(wl.getName());
            s.append(", ");
        }
        WatchlistsModels.addElement("Your WatchLists: " + s);
        for(User usr : user.getFollowing()){
            StringBuilder s1 = new StringBuilder();
            for (Watchlist wl:usr.getWatchlists() ) {
                s1.append(wl.getName());
                s1.append(", ");
            }
            WatchlistsModels.addElement(usr.getUsername() + "'s WatchLists: " + s1);
        }
        JList<String> watchlists = new JList<>(WatchlistsModels);
        scrollPane.setViewportView(watchlists);
    }

    //Get user input from a popup
    public String getUserInput(String message){
        return JOptionPane.showInputDialog(message);
    }

    public void addOpenWatchlistButton(ActionListener actionListener) {
        openWatchlistButton.addActionListener(actionListener);
    }

    public void addCreateWatchlistButton(ActionListener actionListener) {
        createWatchlistButton.addActionListener(actionListener);
    }

    public void addMainMenuActionListener(ActionListener actionListener) {
        mainMenuButton.addActionListener(actionListener);
    }

    public void addLogOutActionListener(ActionListener actionListener) {
        logoutButton.addActionListener(actionListener);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("ad");
        user = (User) o;
        showLists();
    }
}
