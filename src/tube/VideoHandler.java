package tube;

import java.util.*;

public class VideoHandler extends AbstractHandler<Integer, Video> implements Observer {

    public VideoHandler(){
        super(new JSONFormatter(), new FileStorage("videos.json"));
    }

    public void addObserver(){
        for(Video v: getDataMap().values()){
            v.addObserver(this);
        }
    }

    public List<Integer> getVideoIds() { return new ArrayList<>(getDataMap().keySet()); }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Video){
            Video video = (Video) o;
            modify(video.getId(), video);
        }
    }
}
