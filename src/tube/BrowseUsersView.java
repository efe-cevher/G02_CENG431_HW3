package tube;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BrowseUsersView {

    private BrowseUsersController browseUsersController;
    private User user;
    private JFrame frame;
    private JList<String> users;
    private DefaultListModel<String> userModels;
    private JButton followButton, unfollowButton, mainMenuButton, logoutButton;
    private JPanel panel1, panel2, panel3;
    private JScrollPane scrollPane;
    private final JLabel label = new JLabel("IZTECHTube Users");

    public BrowseUsersView(BrowseUsersController browseUsersController, User user){
        this.browseUsersController = browseUsersController;
        this.user = user;
        initiateLayout();
    }



    private void initiateLayout(){
        frame = new JFrame("IZTECHTube Users");

        panel1 = new JPanel(new BorderLayout());

        GridLayout gridLayout = new GridLayout(4, 1);
        gridLayout.setVgap(10);
        panel2 = new JPanel(gridLayout);

        panel3 = new JPanel(new GridLayout(2, 1));

        scrollPane = new JScrollPane();

        userModels = new DefaultListModel<String>();
        for(User nextUser: user.getFollowers()){
            userModels.addElement("User:    " + nextUser.getUsername());
        }
        for(User nextUser: user.getFollowing()){
            userModels.addElement("User:    " + nextUser.getUsername());
        }
        users = new JList<String>(userModels);
        scrollPane.setViewportView(users);
        panel1.add(scrollPane);


        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        logoutButton = new JButton("Sign out");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });


        followButton = new JButton("Follow User");
        followButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        unfollowButton = new JButton("Unfollow User");
        unfollowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        panel2.add(followButton);
        panel2.add(unfollowButton);
        panel2.add(mainMenuButton);
        panel2.add(logoutButton);

        panel3.add(panel1);
        panel3.add(panel2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel3);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }


    public static void main(String[] args) {

        User user1 = new User("zekihan","123456", null , null, null, null, null);
        User user2 = new User("efe","123456", null , null, null, null, null);
        User user3 = new User("kaan","123456", null , null, null, null, null);
        User user4 = new User("arman","123456", null , null, null, null, null);
        User user5 = new User("gay","123456", null , null, null, null, null);
        User user6 = new User("baran","123456", null , null, null, null, null);
        User user7 = new User("efea","123456", null , null, null, null, null);
        User user8 = new User("kaan","123456", null , null, null, null, null);
        User user9= new User("kaan","123456", null , null, null, null, null);
        User user10= new User("kaan","123456", null , null, null, null, null);
        User user11 = new User("efe","123456", null , null, null, null, null);
        User user12 = new User("kaan","123456", null , null, null, null, null);
        User user13 = new User("arman","123456", null , null, null, null, null);
        User user14 = new User("gay","123456", null , null, null, null, null);
        User user15 = new User("baran","123456", null , null, null, null, null);
        User user16 = new User("efea","123456", null , null, null, null, null);
        User user17 = new User("kaan","123456", null , null, null, null, null);
        User user18 = new User("kaan","123456", null , null, null, null, null);
        User user19 = new User("kaan","123456", null , null, null, null, null);
        User user20 = new User("efe","123456", null , null, null, null, null);
        User user21 = new User("kaan","123456", null , null, null, null, null);
        User user22 = new User("arman","123456", null , null, null, null, null);
        User user23 = new User("gay","123456", null , null, null, null, null);
        User user24 = new User("baran","123456", null , null, null, null, null);
        User user25 = new User("efea","123456", null , null, null, null, null);
        User user26 = new User("kaan","123456", null , null, null, null, null);
        User user27 = new User("kaan","123456", null , null, null, null, null);
        User user28 = new User("kaan","123456", null , null, null, null, null);
        User user29 = new User("efe","123456", null , null, null, null, null);
        User user30 = new User("kaan","123456", null , null, null, null, null);
        User user31 = new User("arman","123456", null , null, null, null, null);
        User user32 = new User("gay","123456", null , null, null, null, null);
        User user33 = new User("baran","123456", null , null, null, null, null);
        User user34 = new User("efea","123456", null , null, null, null, null);
        User user35 = new User("kaan","123456", null , null, null, null, null);
        User user36 = new User("kaan","123456", null , null, null, null, null);
        User user37= new User("kaan","123456", null , null, null, null, null);
        User user38= new User("kaan","123456", null , null, null, null, null);
        User user39 = new User("kaan","123456", null , null, null, null, null);
        User user40 = new User("kaan","123456", null , null, null, null, null);

        List<User> following = new ArrayList<User>();
        following.add(user1);
        following.add(user2);
        following.add(user3);
        following.add(user4);
        following.add(user5);
        following.add(user6);
        following.add(user7);
        following.add(user8);
        following.add(user9);
        following.add(user10);
        following.add(user11);
        following.add(user12);
        following.add(user13);
        following.add(user14);
        following.add(user15);
        following.add(user16);
        following.add(user17);
        following.add(user18);
        following.add(user19);
        following.add(user20);

        List<User> follower = new ArrayList<User>();
        follower.add(user21);
        follower.add(user22);
        follower.add(user23);
        follower.add(user24);
        follower.add(user25);
        follower.add(user26);
        follower.add(user27);
        follower.add(user28);
        follower.add(user29);
        follower.add(user30);
        follower.add(user31);
        follower.add(user32);
        follower.add(user33);
        follower.add(user34);
        follower.add(user35);
        follower.add(user36);
        follower.add(user37);
        follower.add(user38);
        follower.add(user39);
        follower.add(user40);

        User user = new User("kaanalgan", "123456", following, follower, null, null, null);
        BrowseUsersView trialView = new BrowseUsersView(null, user);

    }

}
