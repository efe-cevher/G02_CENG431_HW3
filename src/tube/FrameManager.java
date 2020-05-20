package tube;

import javax.swing.*;

public class FrameManager {

    JFrame frame;

    public FrameManager() {
        this.frame = new JFrame();
        frame = new JFrame("IztechTube");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void start(){
        //LoginView loginView = new LoginView();
    }


}
