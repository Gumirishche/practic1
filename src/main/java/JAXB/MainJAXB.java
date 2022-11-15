package JAXB;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.JAXB.FlatJAXB;
import model.JAXB.RoomJAXB;

import java.io.File;
import java.util.Scanner;

import static java.lang.System.in;

public class MainJAXB {

    private String scan() {
        Scanner s = new Scanner(in);
        return s.nextLine();
    }

    public static void main(String[] args) {
        int area = 0;
        System.out.print("Введите название файла:");
        String s = "src/main/resources/" + new MainJAXB().scan() + ".xml";
        System.out.println(s);
        FlatJAXB flat = new FlatJAXB();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FlatJAXB.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            flat = (FlatJAXB) un.unmarshal(new File(s));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println("Floor:" + flat.getFloor() + "  Number:" + flat.getNumber() + "   Area:" + flat.getArea());
        for (RoomJAXB room : flat.getRooms()) {
            area = area + Integer.parseInt(room.getHeight()) * Integer.parseInt(room.getWidth());
            System.out.println("height:" + room.getHeight() + ",  width:" + room.getWidth() + "\n");
        }
        if (area != Integer.parseInt(flat.getArea())) {
            System.out.println("Area посчитанная:" + area);
            System.out.println("Area считанная:" + flat.getArea());
            flat.setArea(Integer.toString(area));
            System.out.println("Площадь в xml не соответсвует действительности");
            System.out.print("Введите название исправленного файла:");
            String name = "src/main/resources/" + new MainJAXB().scan() + ".xml";
            try {
                JAXBContext context = JAXBContext.newInstance(FlatJAXB.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

                // маршаллинг объекта в файл
                marshaller.marshal(flat, new File(name));
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }
}
