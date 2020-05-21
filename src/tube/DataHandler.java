package tube;

import java.util.*;

public class DataHandler  implements IDataHandler, Observer {
    Map<String,User> userMap;
    Map<Integer,Video> videoMap;
    IStorage videoStorage;
    IFormatter<Map<Integer,Video>> videoFormatter;
    public DataHandler() {
        //load from xml and json
        this.userMap = new HashMap<>();
        this.videoStorage = new FileStorage("videos.json");
        this.videoFormatter = new JSONFormatter();
        this.videoMap = videoFormatter.toObject(videoStorage.read());
        addObservers();
    }

    private void addObservers(){
        for (Video video: videoMap.values()){
            video.addObserver(this);
            String videoMapJson = videoFormatter.toFormat(videoMap);
            videoStorage.save(videoMapJson);
        }
        for (User user: userMap.values()){
            user.addObserver(this);
        }
    }

    public List<User> getUserList(){
        return new ArrayList<>(userMap.values());
    }

    public User getUser(String username){
        return userMap.get(username);
    }

    public Video getVideo(int id){
        return videoMap.get(id);
    }

    public void putUser(User user){
        userMap.put(user.getUsername(), user);

    }

    public void putVideo(Video video){
        videoMap.put(video.getId(), video);

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Video){
            Video video = (Video) o;
            putVideo(video);
        }else{
            User user = (User) o;
            putUser(user);
        }
    }
}
