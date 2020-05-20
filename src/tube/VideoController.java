package tube;

import java.util.*;

public class VideoController {
    private Video video;
    private VideoView videoView;

    public VideoController() {
        //test
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment("Ali", "OLAMAAZ"));
        comments.add(new Comment("Ali", "OLAMAAZ"));
        comments.add(new Comment("Ali", "OLAMAAZ"));
        comments.add(new Comment("Ali", "OLAMAAZ"));
        comments.add(new Comment("Ali", "OLAMAAZ"));
        comments.add(new Comment("Ali", "OLAMAAZ"));
        comments.add(new Comment("Ali", "OLAMAAZ"));

        this.video = new Video(12, "title", "content", new Date(), 2, 3, comments);
        this.videoView = new VideoView(this, video);
        video.addObserver(videoView);
    }

    public void onLike(){
        video.like();
    }

    public void onDislike(){
        video.dislike();
    }

    public void onComment(String comment){
        video.addComment(new Comment("efe",comment));
    }

}
