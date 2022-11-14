package JAXB;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import model.JAXB.FlatJAXB;

import java.io.File;
import java.util.Scanner;

import static java.lang.System.in;

public class MainJAXB {
    private String scan() {
        Scanner s = new Scanner(in);
        return s.nextLine();
    }

    public static void main(String[] args) {
        System.out.print("Введите название файла:");
        String s = new MainJAXB().scan();
        System.out.println(s);
        FlatJAXB flat=new FlatJAXB();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FlatJAXB.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            flat = (FlatJAXB) un.unmarshal(new File(s));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println("Floor:"+flat.getFloor()+"  Number:"+flat.getNumber()+"   Area:"+ flat.getArea());
        System.out.println(flat.getRooms().get(0).getHeight()+"  "+flat.getRooms().get(0).getWidth());
    }
}
