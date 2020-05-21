package tube;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoController {
    private Video video;
    private VideoView videoView;

    public VideoController(Video video, VideoView videoView) {

        this.video = video;
        this.videoView = videoView;

        video.addObserver(videoView);
        videoView.addDislikeActionListener(new DislikeActionListener());
        videoView.addLikeActionListener(new LikeActionListener());
        videoView.addCommentActionListener(new CommentActionListener());
    }

    private class DislikeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            video.dislike();
        }
    }

    private class LikeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            video.like();
        }
    }

    private class CommentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commentText = videoView.getCommentText();
            video.addComment(new Comment("efe", commentText));
        }
    }

}
