package tube;

import com.thoughtworks.xstream.XStream;

import javax.xml.*;
import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLFormatter{


    public XMLFormatter() {
    }

    public static String toFormat(List<User> user)
    {
        String xmlContent = "";
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            jaxbMarshaller.marshal(user, sw);

            //Verify XML Content
            xmlContent = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlContent;
    }

   /* public String toFormat(User user)
    {
        String xmlContent = "";
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            jaxbMarshaller.marshal(user, sw);

            //Verify XML Content
            xmlContent = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlContent;
    }*/


    public static User toObject(String xmlAsStr) {

        File xmlFile = new File("users.xml");

        User user = null;

        JAXBContext jaxbContext;
        try
        {
            jaxbContext = JAXBContext.newInstance(User.class);
            StringReader reader = new StringReader(xmlAsStr);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            user = (User) jaxbUnmarshaller.unmarshal(reader);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return user;
    }



    public static String toFormat(Users user){
        XStream xstream = new XStream();
        xstream.alias("user", User.class);
        xstream.alias("users", Users.class);
        xstream.addImplicitCollection(Users.class, "users");
        return xstream.toXML(user);
    }

    public static Users fromFormat(String data){
        XStream xstream = new XStream();
        xstream.alias("user", User.class);
        xstream.alias("users", Users.class);
        xstream.addImplicitCollection(Users.class, "users");
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<users>\n" +
                "    <user>\n" +
                "        <username>kaanalgan</username>\n" +
                "        <password>123456</password>\n" +
                "        <followings/>\n" +
                "        <followers/>\n" +
                "        <likedVideos/>\n" +
                "        <dislikedVideos/>\n" +
                "        <watchlists/>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "        <username>efecan</username>\n" +
                "        <password>123456</password>\n" +
                "        <followings/>\n" +
                "        <followers/>\n" +
                "        <likedVideos/>\n" +
                "        <dislikedVideos/>\n" +
                "        <watchlists/>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "        <username>zekihan</username>\n" +
                "        <password>123456</password>\n" +
                "        <followings/>\n" +
                "        <followers/>\n" +
                "        <likedVideos/>\n" +
                "        <dislikedVideos/>\n" +
                "        <watchlists/>\n" +
                "    </user>\n" +
                "</users>";
        Users users = (Users)xstream.fromXML(xmlContent);
        return users;

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

        //serializationDriver(users);

        /*Users listOfUsers = new Users(users);
        String xml = toFormat(listOfUsers);
        System.out.println(xml);*/
        //marshal(user1);

        Users users1 = fromFormat("");
        for(User u : users1.getUsers()){
            System.out.println("Username: " + u.getUsername());
            System.out.println("Password: " + u.getPassword());
        }

    }
}