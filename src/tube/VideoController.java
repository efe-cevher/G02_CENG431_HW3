package tube;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoController {
    private Video video;
    private VideoView videoView;
    private DataHandler dataHandler;
    private User currentUser;

    public VideoController(Video video, VideoView videoView, User user) {

        this.video = video;
        this.videoView = videoView;
        this.dataHandler = new DataHandler();
        this.currentUser = user;

        videoView.addDislikeActionListener(new DislikeActionListener());
        videoView.addLikeActionListener(new LikeActionListener());
        videoView.addCommentActionListener(new CommentActionListener());
    }

    private class LikeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(currentUser.removeFromDislikes(video.getId())){
                video.setDislikes(video.getDislikes() - 1);
            }
            if(currentUser.addToLikes(video.getId())){
                video.setLikes(video.getLikes() + 1);
            }else{
                currentUser.removeFromLikes(video.getId());
                video.setLikes(video.getLikes() - 1);
            }
        }
    }

    private class DislikeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(currentUser.removeFromLikes(video.getId())){
                video.setLikes(video.getLikes() - 1);
            }
            if(currentUser.addToDislikes(video.getId())){
                video.setDislikes(video.getDislikes() + 1);
            }else{
                currentUser.removeFromDislikes(video.getId());
                video.setDislikes(video.getDislikes() - 1);
            }
        }
    }

    private class CommentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commentText = videoView.getCommentText();
            video.addComment(new Comment(currentUser.getUsername(), commentText));
            dataHandler.putVideo(video);
        }
    }

}
