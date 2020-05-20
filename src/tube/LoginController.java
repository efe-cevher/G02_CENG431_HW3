package tube;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginController {

    LoginView loginView;

    public LoginController() {
        this.loginView = new LoginView(this);
    }

    public void checkCredentials(String username, char[] password) {
        //get data from somewhere
        //if(alright)
            //User user = new User()
            //go somewhere
        //else
            //loginView.securityCheckFailed();

        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment("Ali", "OLAMAAZ"));
        comments.add(new Comment("Ali", "Merhaba Vidyonu vidomda gosterebilirmiyim sagul"));
        comments.add(new Comment("Ali", "Merhaba Vidyonu vidomda gosterebilirmiyim sagul"));
        comments.add(new Comment("Ali", "Merhaba Vidyonu vidomda gosterebilirmiyim sagul"));
        comments.add(new Comment("Ali", "Merhaba Vidyonu vidomda gosterebilirmiyim sagul"));
        comments.add(new Comment("Ali", "Merhaba Vidyonu vidomda gosterebilirmiyim sagul"));
        comments.add(new Comment("Ali", "Merhaba Vidyonu vidomda gosterebilirmiyim sagul"));
        Video video = new Video(12, "title", "content", new Date(), 2, 3, comments);
        VideoView videoView = new VideoView(loginView.getFrame(), video);

        VideoController videoController = new VideoController(video, videoView);
        System.out.println(username);
        System.out.println(password);

    }

}
