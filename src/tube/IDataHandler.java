package tube;

public interface IDataHandler {
    public User getUser(String username);

    public Video getVideo(int id);

    public void putUser(User user);

    public void putVideo(Video video);
}
