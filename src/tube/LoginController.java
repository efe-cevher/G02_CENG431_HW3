package tube;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginController {

    LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        loginView.addLoginActionListener(new LoginActionListener());

    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkCredentials(loginView.getUsername(), loginView.getPassword());
        }
    }


    private void checkCredentials(String username, char[] password) {
        DataHandler dataHandler = new DataHandler();
        User user = dataHandler.getUser(username);

        if(user.getPassword().equals(String.valueOf(password))){
            MenuView menuView = new MenuView(loginView.getFrame());
            MenuController menuController = new MenuController(menuView, user);
        }
        else{
            loginView.securityCheckFailed();
        }

        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment("Ali", "OLAMAAZ"));
        comments.add(new Comment("Ali", "Merhaba Vidyonu vidomda gosterebilirmiyim sagul"));
        Video video = new Video(12, "title", "content", new Date(), 2, 3, comments);
        VideoView videoView = new VideoView(loginView.getFrame(), video);

        VideoController videoController = new VideoController(video, videoView);
        System.out.println(username);
        System.out.println(password);

    }

}
