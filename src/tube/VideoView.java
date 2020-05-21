package tube;

import org.w3c.dom.Document;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
import java.util.*;
import java.util.List;

public class VideoView implements Observer {
    private FrameManager frame;
    private JPanel panel;
    private Video video;
    private JTextField commentField;
    private JButton dislikeButton;
    private JButton likeButton;
    private JButton commentButton;
    private JLabel likeCount;
    private JLabel dislikeCount;
    private JList<Comment> commentJList;

    public VideoView(FrameManager frame, Video video) {
        this.video = video;
        this.frame = frame;
        showVideoView();
        video.addObserver(this);

    }

    public void showVideoView(){
        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        JLabel videoTitle = new JLabel(video.getTitle());
        videoTitle.setBounds(10, 10, 80, 25);
        panel.add(videoTitle);

        JTextArea content = new JTextArea(video.getContent());
        content.setBounds(10, 40, 400, 225);
        panel.add(content);

        videoTitle = new JLabel(video.getTitle());
        videoTitle.setBounds(10, 10, 80, 25);
        panel.add(videoTitle);

        likeCount = new JLabel((video.getLikes() + ""));
        likeCount.setBounds(100, 290, 80, 25);
        panel.add(likeCount);

        dislikeCount = new JLabel((video.getDislikes() + ""));
        dislikeCount.setBounds(230, 290, 80, 25);
        panel.add(dislikeCount);

        dislikeButton = new JButton("Dislike");
        dislikeButton.setBounds(140, 290, 80, 25);
        panel.add(dislikeButton);

        likeButton = new JButton("Like");
        likeButton.setBounds(10, 290, 80, 25);
        panel.add(likeButton);

        commentField = new JTextField(20);
        commentField.setBounds(10, 340, 300, 25);
        panel.add(commentField);

        commentButton = new JButton("Comment");
        commentButton.setBounds(320, 340, 90, 25);
        panel.add(commentButton);

        commentJList = new JList<>(reverseList(video.getComments()));

        JScrollPane scrollCommentPane = new JScrollPane(commentJList);
        scrollCommentPane.setBounds(10, 380, 400, 250);
        panel.add(scrollCommentPane);

        frame.setNewPanel(panel);
    }

    public void addDislikeActionListener(ActionListener actionListener) {
        dislikeButton.addActionListener(actionListener);
    }

    public void addLikeActionListener(ActionListener actionListener) {
        likeButton.addActionListener(actionListener);
    }

    public void addCommentActionListener(ActionListener actionListener) {
        commentButton.addActionListener(actionListener);
    }

    public String getCommentText(){
        return commentField.getText();
    }

    @Override
    public void update(Observable o, Object arg) {
        video = (Video) o;
        likeCount.setText(video.getLikes() + "");
        dislikeCount.setText(video.getDislikes() + "");
        commentJList.setListData(reverseList(video.getComments()));
    }

    private Comment[] reverseList(List<Comment> commentList){
        List<Comment> reversed = new ArrayList<>(commentList);
        Collections.reverse(reversed);
        Comment[] comments = {};
        comments = reversed.toArray(comments);
        return comments;
    }
}
