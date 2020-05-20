package tube;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class VideoController {
    private Video video;
    private VideoView videoView;

    public VideoController(Video video, VideoView videoView) {

        this.video = video;
        this.videoView = videoView;

        video.addObserver(videoView);

        videoView.addDislikeActionListener(new dislikeActionListener());
        videoView.addLikeActionListener(new likeActionListener());
        videoView.addCommentActionListener(new commentActionListener());
    }

    private class dislikeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            video.dislike();
        }
    }

    private class likeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            video.like();
        }
    }

    private class commentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commentText = videoView.getCommentText();
            video.addComment(new Comment("efe", commentText));
        }
    }

}
