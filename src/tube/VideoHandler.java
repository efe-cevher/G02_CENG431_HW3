package tube;

import java.util.*;

public class VideoHandler extends AbstractHandler<Integer, Video> implements Observer {

    public VideoHandler(){
        super(new JSONFormatter(), new FileStorage("videos.json"));
    }

    @Override
    public Video get(Integer identifier) {
        Video video = super.get(identifier);
        video.addObserver(this);
        return video;
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
