package tube;

import com.google.gson.Gson;

public class JSONFormatter {
    Gson gson;

    public JSONFormatter() {
        this.gson = new Gson();
    }

    public String toJson(Video video){
        return gson.toJson(video);
    }

    public Video fromJson(String video){
        return gson.fromJson(video,Video.class);
    }
}
