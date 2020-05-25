package tube;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class VideoController {
    private final Video video;
    private final Watchlist currentWatchlist;
    private final VideoView videoView;
    private final VideoHandler videoHandler;
    private UserHandler userHandler;
    private SessionManager session;

    public VideoController(Video video, VideoView videoView, Watchlist watchlist, SessionManager session) {

        this.video = video;
        this.currentWatchlist = watchlist;
        this.videoView = videoView;
        this.videoHandler = new VideoHandler();
        this.userHandler = new UserHandler();
        this.session = session;

        video.addObserver(videoHandler);
        session.getUser().addObserver(userHandler);

        videoView.addDislikeActionListener(new DislikeActionListener());
        videoView.addLikeActionListener(new LikeActionListener());
        videoView.addCommentActionListener(new CommentActionListener());
        videoView.addAddToWatchlistActionListener(new AddToWatchlistActionListener());
        videoView.addBackActionListener(new BackActionListener());
    }

    private class BackActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(currentWatchlist != null){
                session.openWatchlist(currentWatchlist);
            }else{
                session.openBrowseAllVideos();
            }
        }
    }

    private class AddToWatchlistActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] choices = new String[session.getUser().getWatchlists().size()];
            if(!session.getUser().getWatchlists().isEmpty()){
                for(int i=0; i<session.getUser().getWatchlists().size(); i++){
                    choices[i] = session.getUser().getWatchlists().get(i).getName();
                }
                String selectedWatchlist = videoView.inputFromAListOfValues("Your watchlists", choices, "Choose a watchlist"); // Array of choices
                List<Watchlist> currentUserWatchlists = session.getUser().getWatchlists();
                for(Watchlist watchlist : currentUserWatchlists){
                    if(watchlist.getName().equals(selectedWatchlist)){
                        watchlist.add(video.getId());
                        session.getUser().setWatchlist(watchlist);
                    }
                }
            }else{
                //TODO FEEDBACK YOU HAVE NO WATCHLIST TO ADD A VIDEO
            }
        }
    }

    private class LikeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(session.getUser().removeFromDislikes(video.getId())){
                video.setDislikes(video.getDislikes() - 1);
            }
            if(session.getUser().addToLikes(video.getId())){
                video.setLikes(video.getLikes() + 1);
            }else{
                session.getUser().removeFromLikes(video.getId());
                video.setLikes(video.getLikes() - 1);
            }
        }
    }

    private class DislikeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(session.getUser().removeFromLikes(video.getId())){
                video.setLikes(video.getLikes() - 1);
            }
            if(session.getUser().addToDislikes(video.getId())){
                video.setDislikes(video.getDislikes() + 1);
            }else{
                session.getUser().removeFromDislikes(video.getId());
                video.setDislikes(video.getDislikes() - 1);
            }
        }
    }

    private class CommentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commentText = videoView.getCommentText();
            video.addComment(new Comment(session.getUser().getUsername(), commentText));
            videoHandler.modify(video.getId(), video);
        }
    }

}
