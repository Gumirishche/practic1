package SAX;

import model.Flat;
import model.Room;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
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
        int area = 0;
        System.out.print("Введите название файла:");
        String s = "src/main/resources/" + new Main().scan() + ".xml";
        System.out.println(s);
        Reader handler = new Reader();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
            parser.parse(s, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        flat = handler.getFlat();
        rooms = flat.getRooms();
        for (Room room : rooms) {
            area = area + Integer.parseInt(room.getHeight()) * Integer.parseInt(room.getWidth());
        }
        System.out.println("Floor:" + flat.getFloor() + ", number:" + flat.getNumber() + "\n");
        for (Room room : rooms) {
            System.out.println("height:" + room.getHeight() + ",  width:" + room.getWidth() + "\n");
        }
        System.out.println("area:" + area);
        if (area != handler.getArea()) {
            System.out.println("Area посчитанная:" + area);
            System.out.println("Area считанная:" + handler.getArea());
            System.out.println("Площадь в xml не соответсвует действительности");
        }
    }
}
