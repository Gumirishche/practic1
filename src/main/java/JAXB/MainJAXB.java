package JAXB;

import model.JAXB.FlatJAXB;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.in;

public class MainJAXB {
    private String scan() {
        Scanner s = new Scanner(in);
        return s.nextLine();
    }
    public static void main(String[] args){
        System.out.print("Введите название файла:");
        String s = new MainJAXB().scan();
        System.out.println(s);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FlatJAXB.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            FlatJAXB flat = (FlatJAXB) un.unmarshal(new File(s));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
