package iztechtube.watchlistbrowser;

import iztechtube.FrameManager;
import iztechtube.usersession.User;
import iztechtube.watchlistvideosbrowser.Watchlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class BrowseWatchlistView implements Observer {
    private JPanel panel;
    private JButton mainMenuButton;
    private JButton backButton;
    private JButton createWatchlistButton;
    private JButton openWatchlistButton;
    private JScrollPane scrollPane;
    private JList<String> jWatchlists;
    private FrameManager frame;
    private User user;
    private List<Watchlist> followingsWatchlists;

    public BrowseWatchlistView(FrameManager frame, User user) {
        this.frame = frame;
        this.user = user;
        this.followingsWatchlists = new ArrayList<>();

        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        JLabel title = new JLabel("Watchlists");
        title.setBounds(10, 10, 80, 25);
        panel.add(title);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 420, 500);
        panel.add(scrollPane);

        jWatchlists = new JList<>();
        scrollPane.setViewportView(jWatchlists);
        setWatchlists();

        openWatchlistButton = new JButton("Open Watchlist");
        openWatchlistButton.setBounds(10, 560, 130, 35);
        panel.add(openWatchlistButton);

        createWatchlistButton = new JButton("Create Watchlist");
        createWatchlistButton.setBounds(160, 560, 130, 35);
        panel.add(createWatchlistButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(310, 560, 120, 35);
        panel.add(mainMenuButton);

        frame.setNewPanel(panel);
    }

    private void setWatchlists() {
        List<Watchlist> allWatchlists = new ArrayList();
        allWatchlists.addAll(user.getWatchlists());
        allWatchlists.addAll(followingsWatchlists);
        String[] watchlistsArr = new String[allWatchlists.size()];
        int i = 0;
        for(Watchlist wl: allWatchlists){
            watchlistsArr[i] = "<html><body>" + wl.getName() + "<br>" + " " + "<br>" + "</span></body></html>}";
            i++;
        }
        jWatchlists.setListData(watchlistsArr);
    }

    //Get user input from a popup
    public String getUserInput(String message){
        return JOptionPane.showInputDialog(message);
    }

    public void displayMessage(String message){ JOptionPane.showMessageDialog(frame.getFrame(), message); }

    public void addOpenWatchlistButton(ActionListener actionListener) {
        openWatchlistButton.addActionListener(actionListener);
    }

    public void addCreateWatchlistButton(ActionListener actionListener) {
        createWatchlistButton.addActionListener(actionListener);
    }

    public void addMainMenuActionListener(ActionListener actionListener) {
        mainMenuButton.addActionListener(actionListener);
    }

    public int getSelectedListIndex(){
        return jWatchlists.getSelectedIndex();
    }

    public void setFollowingsWatchlists(List<Watchlist> watchlists){
        this.followingsWatchlists = watchlists;
        setWatchlists();
    }

    @Override
    public void update(Observable o, Object arg) {
        user = (User) o;
        setWatchlists();
    }
}
