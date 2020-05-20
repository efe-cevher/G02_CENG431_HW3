package tube;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class VideoView implements Observer {
    private VideoController controller;
    private Observable video;
    private JTextField commentField;
    private JPanel panel;
    private JFrame frame;

    public VideoView(VideoController controller, Observable video) {
        this.controller = controller;
        this.video = video;
        frame = new JFrame("IztechTube");
        frame.setSize(800, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        showVideoView();
    }

    public void showVideoView(){
        panel = new JPanel(new GridLayout(3, 1));
        panel.setLayout(null);

        JLabel videoTitle = new JLabel(((Video) video).getTitle());
        videoTitle.setBounds(10, 10, 80, 25);
        panel.add(videoTitle);

        JTextArea content = new JTextArea(((Video) video).getContent());
        content.setBounds(10, 40, 200, 150);
        panel.add(content);

        videoTitle = new JLabel(((Video)video).getTitle());
        videoTitle.setBounds(10, 10, 80, 25);
        panel.add(videoTitle);

        JButton likeButton = new JButton("Like");
        likeButton.setBounds(10, 200, 80, 25);
        panel.add(likeButton);
        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickLike();
                System.out.println("LIKE");
            }
        });

        JLabel likeCount = new JLabel(((Video)video).getLikes() + "");

        JButton dislikeButton = new JButton("Dislike");
        dislikeButton.setBounds(120, 200, 80, 25);
        panel.add(dislikeButton);
        dislikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickDislike();
                System.out.println("DISLIKE");
            }
        });

        commentField = new JTextField(20);
        commentField.setBounds(10, 250, 200, 25);
        panel.add(commentField);

        JButton commentButton = new JButton("Comment");
        commentButton.setBounds(120, 280, 90, 25);
        panel.add(commentButton);
        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClickComment(commentField.getText());
                System.out.println(commentField.getText());
            }
        });

        List<Comment> commentList = ((Video)video).getComments();
        Comment[] comments = {};
        JList<Comment> commentJList = new JList<>(commentList.toArray(comments));

        JScrollPane scrollCommentPane = new JScrollPane(commentJList);
        scrollCommentPane.setBounds(10, 320, 300, 160);
        panel.add(scrollCommentPane);

        frame.getContentPane().removeAll();
        frame.add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

    }

    public void onClickLike(){
        controller.onDislike();
    }

    public void onClickDislike(){
        controller.onDislike();
    }

    public void onClickComment(String comment){
        controller.onComment(comment);
        update(video, "");
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("yay");
        this.video = o;
        showVideoView();

    }
}
