package SAX;

import model.Flat;
import model.Room;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.*;
import java.io.IOException;

public class Reader extends DefaultHandler {
    private int indent = 0;
    final static int INDENT = 4;

    private final Flat flat = new Flat();

    private int area;
    private int indexRoom = -1;

    public int getArea() {
        return area;
    }

    public Flat getFlat() {
        return flat;
    }

    public void startDocument() {
        printString("Начало документа");
    }

    public void endDocument() {
        printString("Конец документа");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        indent += INDENT;
        printString("Элемент " + qName + ":");
        if (qName.equals("flat")) {
        String floor = attributes.getValue("floor");
        String number = attributes.getValue("number");
        flat.setFloor(floor);
        flat.setNumber(number);
        }
        if (qName.equals("room")) {
            indexRoom++;
            String height = attributes.getValue("height");
            String width = attributes.getValue("width");
            System.out.println(indexRoom);
            flat.getRooms().add(indexRoom, new Room(width, height));
        }
    }

    public void endElement(String uri, String localName,
                           String qName) {
        printString("Конец элемента " + qName + ".");
        indent -= INDENT;
    }

    public void warning(SAXParseException e) {
        System.out.println("Предупреждение :" + e.getPublicId());
    }

    public void error(SAXParseException e) {
        System.out.println("Ошибка :" + e.getPublicId());
    }

    public void fatalError(SAXParseException e) {
        System.out.println("Фатальная ошибка :" + e.getPublicId());
    }

    public void characters(char[] ch, int start, int length) {
        indent += INDENT;
        String str = new String(ch, start, length);
        printString(str);
        System.out.println("str:" + str);
        if (!str.trim().isEmpty()) {
            area = Integer.parseInt(str.trim());
        }
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
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
            parser.parse("src/main/resources/flat.xml", handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
