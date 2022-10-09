package SAX;

import model.Flat;
import model.Room;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.util.ArrayList;

public class Reader extends DefaultHandler {
    private int indent = 0;
    final static int INDENT = 4;

    private final ArrayList<Flat> flats = new ArrayList<>();

    private final ArrayList<String> sums = new ArrayList<>();

    private int area;
    private int indexFlat = 0;
    private int indexRoom;

    public int getArea() {
        return area;
    }

    public ArrayList<Flat> getFlats() {
        return flats;
    }

    public void startDocument() throws SAXException {
        printString("Начало документа");
    }

    public void endDocument() throws SAXException {
        printString("Конец документа");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        indent += INDENT;
        printString("Элемент " + qName + ":");
        if (qName.equals("flat")) {
            indexRoom = indexFlat;
            indexFlat++;
            String floor = attributes.getValue("floor");
            String number = attributes.getValue("number");
            printString("Floor " + ":" + floor);
            printString("Number " + ":" + number);
            flats.add(new Flat(floor, number));
            System.out.println(indexFlat);
        }
        if (qName.equals("room")) {
            String height = attributes.getValue("height");
            String width = attributes.getValue("width");
            printString("Height " + ":" + height);
            printString("Width " + ":" + width);
            System.out.println(indexRoom);
            flats.get(indexRoom).setRooms(new Room(width, height));
        }
    }

    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        printString("Конец элемента " + qName + ".");
        indent -= INDENT;
    }

    public void warning(SAXParseException e) throws SAXException {
        System.out.println("Предупреждение :" + e.getPublicId());
    }

    public void error(SAXParseException e) throws SAXException {
        System.out.println("Ошибка :" + e.getPublicId());
    }

    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("Фатальная ошибка :" + e.getPublicId());
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        indent += INDENT;
        String str = new String(ch, start, length);
        printString(str);
        System.out.println("str:" + str);
        area = Integer.parseInt(str);
        indent -= INDENT;
    }

    public void printString(String str) {
        String ind_s;
        if (indent > 0) {
            char[] ind = new char[indent];
            java.util.Arrays.fill(ind, ' ');
            ind_s = new String(ind, 0, indent);
        } else {
            ind_s = "";
        }
        System.out.println(ind_s + str);
    }

    public static void main(String[] args) {
        DefaultHandler handler = new Reader();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            parser.parse("src/main/resources/flat.xml", handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
