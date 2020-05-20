package tube;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.*;
import javax.xml.bind.JAXB;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class XMLFormatter {

    private Document domDoc;

    public XMLFormatter() {
    }

    protected void buildDOMDocument(List<User> users) {

        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        DOMImplementation impl;
        Element elmt1;
        Element elmt2;

        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            impl = builder.getDOMImplementation();
            domDoc = impl.createDocument(null, null, null);
            elmt1 = domDoc.createElement("root");
            domDoc.appendChild(elmt1);

            for (User user:users) {
                elmt2 = domDoc.createElement("user");
                elmt2.setTextContent(user.toString());
                elmt1.appendChild(elmt2);

            }

        }
        catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    protected void serializeXML() {

        DOMSource domSrc;
        Transformer txformer;
        StringWriter sw;
        StreamResult sr;

        try {
            domSrc = new DOMSource(domDoc);

            txformer = TransformerFactory.newInstance().newTransformer();
            txformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            txformer.setOutputProperty(OutputKeys.METHOD, "xml");
            txformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            txformer.setOutputProperty(OutputKeys.INDENT, "yes");
            txformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            txformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            sw = new StringWriter();
            sr = new StreamResult(sw);

            txformer.transform(domSrc, sr);

            System.out.println(sw.toString());
        } catch (TransformerException | TransformerFactoryConfigurationError ex) {
            ex.printStackTrace();
        }
    }

    public static void serializationDriver(List<User> users) {

        XMLFormatter lcl = new XMLFormatter();
        lcl.buildDOMDocument(users);
        lcl.serializeXML();
    }

    public static void main(String[] args) {
        User user2 = new User("user2","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        User user3 = new User("user3","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        User user4 = new User("user4","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        User user5 = new User("user5","123",new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        User user1 = new User("user1","123",users,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());

        //serializationDriver(users);

        StringWriter sw = new StringWriter();
        JAXB.marshal(user1, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }
}