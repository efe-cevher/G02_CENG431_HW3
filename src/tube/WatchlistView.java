package tube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class WatchlistView implements Observer {

    private Watchlist watchlist;
    private JButton watchVideoButton, mainMenuButton, removeVideoButton;
    private FrameManager frame;
    private JScrollPane scrollPane;
    private JList<String> videos;
    private DefaultListModel<String> videoModels;
    private JPanel panel;
    private JLabel label;

    public WatchlistView(FrameManager frame, Watchlist watchlist) {
        this.frame = frame;
        this.watchlist = watchlist;

        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        JLabel videoTitle = new JLabel(watchlist.getName());
        videoTitle.setBounds(10, 10, 80, 25);
        panel.add(videoTitle);

        videos = new JList<String>();
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 450, 250);
        panel.add(scrollPane);

        showWatchlist();

        removeVideoButton = new JButton("Delete video");
        removeVideoButton.setBounds(160, 320, 120, 35);
        panel.add(removeVideoButton);

        watchVideoButton = new JButton("Watch");
        watchVideoButton.setBounds(10, 320, 120, 35);
        panel.add(watchVideoButton);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(310, 320, 120, 35);
        panel.add(mainMenuButton);

        frame.setNewPanel(panel);
    }


    public void showWatchlist(){
        String[] videoListArr = new String[watchlist.getVideos().size()];
        int i = 0;
        for(Integer id: watchlist.getVideos()){
            videoListArr[i] = "<html><body>" + id + "<br>" + "</span></body></html>}";
            i++;
        }
        videos = new JList<>(videoListArr);
        scrollPane.setViewportView(videos);
    }


    @Override
    //Whenever the watchlist changes, update the view
    public void update(Observable o, Object arg) {
        this.watchlist = (Watchlist) o;
        showWatchlist();
    }

    //Get user input from a popup
    public String getUserInput(String message){
        return JOptionPane.showInputDialog(message);
    }

    public void addDeleteVideoActionListener(MouseListener mouseListener) {
        removeVideoButton.addMouseListener(mouseListener);
    }

    public void addGoToVideoActionListener(MouseListener mouseListener) {
        watchVideoButton.addMouseListener(mouseListener);
    }

    public void addMainMenuActionListener(ActionListener actionListener) {
        mainMenuButton.addActionListener(actionListener);
    }

    public int getSelectedListIndex(){
        return videos.getSelectedIndex();
    }

    public FrameManager getFrame() { return frame; }

    public static void main(String[] args) {

        List<Integer> videos = new ArrayList<>();

        Video video = new Video(1, "why", "are you gay", new Date(), 0, 0, null);
        Video video2 = new Video(2, "why", "are you gay", new Date(), 0, 0, null);
        Video video3 = new Video(3, "why", "are you gay", new Date(), 0, 0, null);
        Video video4 = new Video(4, "why", "are you gay", new Date(), 0, 0, null);
        Video video5 = new Video(5, "why", "are you gay", new Date(), 0, 0, null);
        Video video6 = new Video(6, "why", "are you gay", new Date(), 0, 0, null);

        videos.add(video.getId());
        videos.add(video2.getId());
        videos.add(video3.getId());
        videos.add(video4.getId());
        videos.add(video5.getId());
        videos.add(video6.getId());

        Watchlist w = new Watchlist(videos, "gayisgay");

        WatchlistView watchlistView = new WatchlistView(new FrameManager(), w);
        WatchlistController wc = new WatchlistController(watchlistView, w);
    }

}
