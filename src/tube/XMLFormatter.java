package tube;

import javax.xml.*;
import javax.xml.bind.*;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLFormatter implements IFormatter<User>{


    public XMLFormatter() {
    }


    public String toFormat(User user)
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


    public  User toObject(String xmlAsStr) {

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


    /*public static void marshal(User user) throws JAXBException, IOException{

        JAXBContext context = JAXBContext.newInstance(User.class);
        StringWriter sw = new StringWriter();
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(user, sw);
        System.out.println(sw.toString());
    }*/

   /* public static void main(String[] args) throws JAXBException, IOException {

        User user2 = new User("user2","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        User user3 = new User("user3","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        User user4 = new User("user4","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        User user5 = new User("user5","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
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

       toFormat(user1);
        //marshal(user1);
        User user = toObject("");
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        for(User user12 : user.getFollowing()){
            System.out.println("Following: " + user12.getUsername());
        }

        for(Integer inte : user.getDislikedVideos()){
            System.out.println("Disliked: " + inte);
        }

        for(Integer inte : user.getLikedVideos()){
            System.out.println("Liked: " + inte);
        }

        for(Watchlist w : user.getWatchlists()){
            for(Integer v : w.getVideos()){
                System.out.println("Video Id: " + v);
            }
        }


    }*/
}