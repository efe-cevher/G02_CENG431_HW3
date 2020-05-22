package tube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.*;

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

        frame.setNewPanel(panel);

    }

    private void showLists() {
        String[] watchlistsArr = new String[user.getAllWatchLists().size()];
        int i = 0;
        for(Watchlist wl: user.getAllWatchLists()){
            watchlistsArr[i] = "<html><body>" + wl.getName() + "<br>" + "By: " + user.getUsername() + "<br>" + " " + "<br>" + "</span></body></html>}";
            i++;
        }
        jWatchlists = new JList<>(watchlistsArr);
        scrollPane.setViewportView(jWatchlists);
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

    public void addOpenWatchlistMouseListener(MouseListener mouseListener) {
        jWatchlists.addMouseListener(mouseListener);
    }

    public int getSelectedListIndex(){
        return jWatchlists.getSelectedIndex();
    }

    public FrameManager getFrame() {
        return frame;
    }

    @Override
    public void update(Observable o, Object arg) {
        user = (User) o;
        showLists();
    }
}
