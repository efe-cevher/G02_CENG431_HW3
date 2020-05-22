package tube;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.basic.*;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.ArrayTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import com.thoughtworks.xstream.security.WildcardTypePermission;

import javax.lang.model.type.ArrayType;
import javax.xml.*;
import javax.xml.bind.*;
import java.io.*;
import java.util.*;

public class XMLFormatter{


    public XMLFormatter() {
    }

    public String toFormat(Map<String, User> user){

        Users users = new Users(new ArrayList<>(user.values()));
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.omitField(Observable.class,"changed");
        xstream.omitField(Observable.class,"obs");
        xstream.alias("user", User.class);
        xstream.alias("users", Users.class);
        xstream.alias("watchlist", Watchlist.class);
        xstream.addImplicitCollection(Users.class, "users");
        StringWriter stringWriter = new StringWriter();
        xstream.marshal(users, new PrettyPrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public XStream createXStream() {
        XStream xstream = new XStream(new StaxDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out, "    ");
            }
        }) {
            // only register the converters we need; other converters generate a private access warning in the console on Java9+...
            @Override
            protected void setupConverters() {
                registerConverter(new NullConverter(), PRIORITY_VERY_HIGH);
                registerConverter(new IntConverter(), PRIORITY_NORMAL);
                registerConverter(new FloatConverter(), PRIORITY_NORMAL);
                registerConverter(new DoubleConverter(), PRIORITY_NORMAL);
                registerConverter(new LongConverter(), PRIORITY_NORMAL);
                registerConverter(new ShortConverter(), PRIORITY_NORMAL);
                registerConverter(new BooleanConverter(), PRIORITY_NORMAL);
                registerConverter(new ByteConverter(), PRIORITY_NORMAL);
                registerConverter(new StringConverter(), PRIORITY_NORMAL);
                registerConverter(new DateConverter(), PRIORITY_NORMAL);
                registerConverter(new CollectionConverter(getMapper()), PRIORITY_NORMAL);
                registerConverter((new ReflectionConverter(getMapper(), getReflectionProvider()){
                    @Override
                    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
                        Object result = new Users(new ArrayList<>());
                        result = this.doUnmarshal(result, reader, context);
                        return this.serializationMembers.callReadResolve(result);
                    }

                    @Override
                    public boolean canConvert(Class type) {
                        return type.equals(Users.class);
                    }
                }),PRIORITY_VERY_LOW);
                registerConverter((new ReflectionConverter(getMapper(), getReflectionProvider()){
                    @Override
                    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
                        Object result = new User();
                        result = this.doUnmarshal(result, reader, context);
                        return this.serializationMembers.callReadResolve(result);
                    }
                    @Override
                    public boolean canConvert(Class type) {
                        return type.equals(User.class);
                    }
                }),PRIORITY_VERY_LOW);
                registerConverter((new ReflectionConverter(getMapper(), getReflectionProvider()){
                    @Override
                    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
                        Object result = new Watchlist();
                        result = this.doUnmarshal(result, reader, context);
                        return this.serializationMembers.callReadResolve(result);
                    }
                    @Override
                    public boolean canConvert(Class type) {
                        return type.equals(Watchlist.class);
                    }
                }),PRIORITY_VERY_LOW);
            }
        };

        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(ArrayTypePermission.ARRAYS);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.addPermission(new WildcardTypePermission(new String[]{
                User.class.getName(),Users.class.getName(),Watchlist.class.getName()
        }));
        xstream.autodetectAnnotations(true);
        xstream.alias("user", User.class);
        xstream.alias("watchlist", Watchlist.class);
        xstream.alias("users", Users.class);
        xstream.addImplicitCollection(Users.class, "users");

        return xstream;
    }

    public Map<String,User> toObject(String data){
        XStream xstream = createXStream();
        Users users = (Users)xstream.fromXML(data);
        Map<String,User> userMap = new HashMap<>();
        for(User user : users.getUsers()){
            userMap.put(user.getUsername(), user);
        }
        return userMap;
    }

    public static void main(String[] args) throws JAXBException, IOException {

        User user2 = new User("kaan","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        User user3 = new User("efecan","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        User user4 = new User("zekihan","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        User user5 = new User("gayegemen","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        List<Integer> videos = new ArrayList<>();
        videos.add((new Video(1,"gay","isgay",new Date(), 0,0,null).getId()));
        videos.add((new Video(1,"gay","isgay",new Date(), 0,0,null)).getId());
        videos.add((new Video(1,"gay","isgay",new Date(), 0,0,null)).getId());
        Watchlist watchlist = new Watchlist(videos, "hey1");
        Watchlist watchlist2 = new Watchlist(videos, "hey2");
        List<Watchlist> watchlists = new ArrayList<>();
        watchlists.add(watchlist);
        watchlists.add(watchlist2);

        List<Integer> liked = new ArrayList<>();

        liked.add(1);
        liked.add(2);
        liked.add(2);
        liked.add(2);
        liked.add(2);
        liked.add(2);
        liked.add(2);

        User user1 = new User("user1","123", users, new ArrayList<>(), liked, liked, watchlists);

        XMLFormatter xmlFormatter = new XMLFormatter();
        IStorage storage = new FileStorage("users.xml");

        /*Map<String,User> usersMap =new HashMap<>();
        usersMap.put(user1.getUsername(),user1);
        String asd = xmlFormatter.toFormat(usersMap);
        storage.save(asd);*/

        //System.out.println(storage.read());

        Map<String,User> users1 = xmlFormatter.toObject(storage.read());
        for(User u : users1.values()){
            System.out.println("Username: " + u.getUsername());
            System.out.println("Password: " + u.getPassword());
        }

    }

    private static class Users {
        private List<User> users;

        public Users(List<User> users){ this.users = users; }

        public List<User> getUsers(){ return this.users; }

    }

}