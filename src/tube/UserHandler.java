package tube;

import java.util.*;

public class UserHandler extends AbstractHandler<String, User>  implements Observer {

    public UserHandler() {
        super(new XMLFormatter(), new FileStorage("users.xml"));
        //load from xml and json
    }

    @Override
    public User get(String identifier) {
        User user = super.get(identifier);
        user.addObserver(this);
        return user;
    }

    public List<String> getUsernames() {
        return new ArrayList<>(getDataMap().keySet());
    }

    /* If any information related to observers change, update the database(XML and JSON files) */
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof User){
            User user = (User) o;
            modify(user.getUsername(), user);
        }
    }

}
