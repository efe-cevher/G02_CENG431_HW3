package tube;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoController {
    private Video video;
    private VideoView videoView;
    private DataHandler dataHandler;

    public VideoController(Video video, VideoView videoView) {

        this.video = video;
        this.videoView = videoView;
        this.dataHandler = new DataHandler();

        videoView.addDislikeActionListener(new DislikeActionListener());
        videoView.addLikeActionListener(new LikeActionListener());
        videoView.addCommentActionListener(new CommentActionListener());
    }

    private class DislikeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            video.dislike();
            dataHandler.putVideo(video);
        }
    }

    private class LikeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            video.like();
            dataHandler.putVideo(video);
        }
    }

    private class CommentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commentText = videoView.getCommentText();
            video.addComment(new Comment("efe", commentText));
            dataHandler.putVideo(video);
        }
    }

}
