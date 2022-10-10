package DOM;

import SAX.Reader;
import model.Flat;
import model.Room;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;

public class Main {
    private String scan() {
        Scanner s = new Scanner(in);
        return s.nextLine();
    }

    public static void main(String[] args) {
        Flat flat;
        ArrayList<Room> rooms;
        Flat f = new Flat();
        Room r = new Room();
        int area = 0;
        System.out.print("Введите название файла:");
        String s = new DOM.Main().scan();
        System.out.println(s);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(s);
            ReaderDOM obj = new ReaderDOM();
            obj.myPrint(document);
            System.out.println("area="+obj.getArea());
            flat = obj.getFlat();
            rooms = flat.getRooms();
            for (Room room : rooms) {
                area = area + Integer.parseInt(room.getHeight()) * Integer.parseInt(room.getWidth());
            }
            System.out.println("Floor:" + flat.getFloor() + ", number:" + flat.getNumber() + "\n");
            for (Room room : rooms) {
                System.out.println("height:" + room.getHeight() + ",  width:" + room.getWidth() + "\n");
            }
            System.out.println("area:" + area);
            if (area != obj.getArea()) {
                System.out.println("Площадь в xml не соответсвует действительности");
                System.out.print("Введите название исправленного файла:");
                String name = new DOM.Main().scan();
                try {
                    DocumentBuilderFactory factoryD = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder1 = null;
                    builder1 = factoryD.newDocumentBuilder();
                    Document document1 = builder.parse(s);
                    NodeList areaL = document1.getElementsByTagName("flat");
                    Element ar = null;
                    ar = (Element) areaL.item(0);
                    Node areaNode = ar.getElementsByTagName("area").item(0).getFirstChild();
                    areaNode.setNodeValue(Integer.toString(area));
                    DOMSource dom_source = new DOMSource(document);
                    StreamResult out_stream = new StreamResult(name);
                    TransformerFactory tFactory = TransformerFactory.newInstance();
                    Transformer transformer = tFactory.newTransformer(/* !!!! */);

                    DocumentType docType = document.getDoctype();
                    String systemID = docType.getSystemId();
                    String publicID = docType.getPublicId();
                    String res = publicID + "\" \"" + systemID;

                    transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, res);
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                    transformer.transform(dom_source, out_stream);
                } catch (IOException | TransformerException | SAXException | ParserConfigurationException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        /*Reader handler = new Reader();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            parser.parse(s, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }*/

    }
}