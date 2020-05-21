package tube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class WatchlistView implements Observer {

    private Watchlist watchlist;
    private JButton goToVideoButton, mainMenuButton, logoutButton, addVideoButton, removeVideoButton;
    private FrameManager frame;
    private JScrollPane scrollPane;
    private JList<String> videos;
    private DefaultListModel<String> videoModels;
    private JPanel panel;
    private JLabel label;

    public WatchlistView(FrameManager frame, Watchlist watchlist) {
        this.frame = frame;
        this.watchlist = watchlist;
        showWatchlist();
    }


    public void showWatchlist(){
        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        JLabel videoTitle = new JLabel(watchlist.getName());
        videoTitle.setBounds(10, 10, 80, 25);
        panel.add(videoTitle);

        DefaultListModel<String> videoModels = new DefaultListModel<>();
        for(Video v : watchlist.getVideos()){
            videoModels.addElement("id: " + v.getId() + "   title: " + v.getTitle());
        }
        videos = new JList<String>(videoModels);
        JScrollPane scrollPane = new JScrollPane(videos);
        scrollPane.setBounds(10, 40, 450, 250);
        panel.add(scrollPane);

        addVideoButton = new JButton("Add video");
        addVideoButton.setBounds(120, 320, 120, 25);
        panel.add(addVideoButton);

        removeVideoButton = new JButton("Delete video");
        removeVideoButton.setBounds(270, 320, 120, 25);
        panel.add(removeVideoButton);

        goToVideoButton = new JButton("Watch");
        goToVideoButton.setBounds(10, 320, 80, 25);
        panel.add(goToVideoButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(10, 400, 120, 25);
        panel.add(mainMenuButton);

        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(140, 400, 80, 25);
        panel.add(logoutButton);

        frame.setNewPanel(panel);
    }


    @Override
    //Whenever the watchlist changes, update the view
    public void update(Observable o, Object arg) {
        watchlist = (Watchlist) o;
        showWatchlist();
    }

    //Get user input from a popup
    public String getUserInput(String message){
        return JOptionPane.showInputDialog(message);
    }

    public void addAddVideoActionListener(ActionListener actionListener) {
        addVideoButton.addActionListener(actionListener);
    }

    public void addDeleteVideoActionListener(ActionListener actionListener) {
        removeVideoButton.addActionListener(actionListener);
    }

    public void addGoToVideoActionListener(ActionListener actionListener) {
        goToVideoButton.addActionListener(actionListener);
    }

    public void addMainMenuActionListener(ActionListener actionListener) {
        mainMenuButton.addActionListener(actionListener);
    }

    public void addLogOutActionListener(ActionListener actionListener) {
        logoutButton.addActionListener(actionListener);
    }

    public static void main(String[] args) {

        List<Video> videos = new ArrayList<Video>();

        Video video = new Video(1, "why", "are you gay", new Date(), 0, 0, null);
        Video video2 = new Video(1, "why", "are you gay", new Date(), 0, 0, null);
        Video video3 = new Video(1, "why", "are you gay", new Date(), 0, 0, null);
        Video video4 = new Video(1, "why", "are you gay", new Date(), 0, 0, null);
        Video video5 = new Video(1, "why", "are you gay", new Date(), 0, 0, null);
        Video video6 = new Video(1, "why", "are you gay", new Date(), 0, 0, null);

        videos.add(video);
        videos.add(video2);
        videos.add(video3);
        videos.add(video4);
        videos.add(video5);
        videos.add(video6);

        Watchlist w = new Watchlist(videos, "gayisgay");
        WatchlistView watchlistView = new WatchlistView(new FrameManager(), w);
    }

}
