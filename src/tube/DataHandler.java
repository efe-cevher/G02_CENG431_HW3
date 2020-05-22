package tube;

import java.util.*;

public class DataHandler  implements IDataHandler, Observer {
    Map<String,User> userMap;
    Map<Integer,Video> videoMap;
    IStorage videoStorage, userStorage;
    IFormatter<Map<Integer,Video>> videoFormatter;
    XMLFormatter userFormatter;

    public DataHandler() {
        //load from xml and json

        this.videoStorage = new FileStorage("videos.json");
        this.userStorage = new FileStorage("users.xml");
        this.videoFormatter = new JSONFormatter();
        this.userFormatter = new XMLFormatter();
        this.videoMap = videoFormatter.toObject(videoStorage.read());
        this.userMap = userFormatter.toObject(userStorage.read());
        addObservers();
    }

    private void addObservers(){
        for (Video video: videoMap.values()){
            video.addObserver(this);
        }
        for (User user: userMap.values()){
            user.addObserver(this);
        }

    }

    public List<User> getUserList(){
        return new ArrayList<>(userMap.values());
    }

    @Override
    public List<String> getUsernames() {
        return new ArrayList<>(userMap.keySet());
    }

    public User getUser(String username){
        return userMap.get(username);
    }

    public Video getVideo(int id){
        return videoMap.get(id);
    }

    public void putUser(User user){
        userMap.put(user.getUsername(), user);
        //List<User> users = new ArrayList<>(userMap.values());
        String userAsXML = userFormatter.toFormat(userMap);
        userStorage.append(userAsXML);
    }

    public void putVideo(Video video){
        videoMap.put(video.getId(), video);
        String videoMapJson = videoFormatter.toFormat(videoMap);
        videoStorage.save(videoMapJson);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Video){
            Video video = (Video) o;
            putVideo(video);
        }else{
            User user = (User) o;
            //putUser(user);
        }
    }

    public static void main(String[] args) {
        DataHandler d = new DataHandler();
        d.putUser(new User("kaanalgan", "123456", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        d.putUser(new User("efecan", "123456", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        d.putUser(new User("zekihan", "123456", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }
}
