package tube;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class WatchlistView implements Observer {

    private Watchlist watchlist;
    private WatchlistController watchlistController;
    private List<JButton> redirectButtons;
    private List<JLabel> videoLabels;
    private JFrame frame;
    private JTable videos;

    public WatchlistView(Watchlist watchlist, WatchlistController watchlistController) {
        frame = new JFrame("IZTECHTube");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        this.watchlist = watchlist;
        this.watchlistController = watchlistController;
        showWatchlists();
    }

    public void showWatchlists(){
        /*String[] columns = { "id", "title", "content", "Go to video" };
        Object[][] data = new Object[watchlist.getVideos().size()][4];
        JButton directButton = new JButton("Go to video");
        List<Video> videos = new ArrayList<Video>(watchlist.getVideos());
        for(int i=0; i<watchlist.getVideos().size(); i++){
            data[i] = new Object{videos.get(i).getId(), watchlist.getVideos().get(i).getTitle(), videos.get(i).getContent(), };
        }
        videos = new JTable(, columns);*/
    }


    //Whenever the watchlist changes, update the view
    public void update(Observable o, Object arg) {
        watchlist = ((Watchlist)arg);
        showWatchlists();
    }

    //Get user input from a popup
    public String getUserInput(String message){
        return JOptionPane.showInputDialog(message);
    }

    //'Add a video' is clicked
    public void onClickAdd(){ watchlistController.onAddVideo(); }

    //'Remove a video' is clicked
    public void onClickRemove(){ watchlistController.onRemoveVideo(); }
}
