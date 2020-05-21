package tube;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

public class JSONFormatter implements IFormatter<Map<Integer,Video>>{
    private Gson gson;

    public JSONFormatter() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public String toFormat(Video video){
        return gson.toJson(video);
    }

    public Video toVideoObject(String video){
        return gson.fromJson(video,Video.class);
    }

    public Map<Integer,Video> toObject(String videoMap){
        Type videoMapType = new TypeToken<Map<Integer,Video>>() {}.getType();
        return gson.fromJson(videoMap,videoMapType);
    }

    public String toFormat(Map<Integer,Video> videoMap){
        return gson.toJson(videoMap);
    }

    public static void main(String[] args) {
        Map<Integer,Video> videoMap = new HashMap<>();
        for (int i = 1; i < 51; i++) {
            Video video = new Video(i,"video "+ i,"video " + i + " content",new Date(),0,0,new ArrayList<>());
            videoMap.put(i,video);
        }

        JSONFormatter jsonFormatter = new JSONFormatter();
        String s = jsonFormatter.toFormat(videoMap);
        IStorage storage = new FileStorage("videos.json");
        storage.save(s);

        //Map<Integer,Video> videoMap2 = jsonFormatter.toObject(s);
    }
}
