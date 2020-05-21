package tube;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JSONFormatter implements IFormatter<Video>{
    Gson gson;

    public JSONFormatter() {
        this.gson = new Gson();
    }

    public String toFormat(Video video){
        return gson.toJson(video);
    }

    public Video toObject(String video){
        return gson.fromJson(video,Video.class);
    }

    public static void main(String[] args) {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment("asd1","asd1"));
        comments.add(new Comment("asd2","asd2"));
        comments.add(new Comment("asd3","asd3"));
        comments.add(new Comment("asd4","asd4"));
        Video video = new Video(1,"video1","video1content",new Date(),5,1,comments);
        JSONFormatter jsonFormatter = new JSONFormatter();
        String s = jsonFormatter.toFormat(video);
        System.out.println(s);
        Video video1 = jsonFormatter.toObject(s);
        String s1 = jsonFormatter.toFormat(video1);
        System.out.println(s1);
    }
}
