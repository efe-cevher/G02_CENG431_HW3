package tube;

import java.util.HashMap;
import java.util.Map;

public class DataHandler implements IDataHandler{
    Map<String,User> userMap;
    Map<Integer,Video> videoMap;

    public DataHandler() {
        //load from xml and json
        this.userMap = new HashMap<>();
        this.videoMap = new HashMap<>();
    }

    public User getUser(String username){
        return userMap.get(username);
    }

    public Video getVideo(int id){
        return videoMap.get(id);
    }

    public void putUser(User user){
        userMap.put(user.getUsername(), user);
        //Also update xml
    }

    public void putVideo(Video video){
        videoMap.put(video.getId(), video);
        //Also update json
    }
}
