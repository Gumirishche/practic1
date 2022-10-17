package JAXB;

import java.io.*;

public class ReaderJAXB {
    public static void main(String[] args) {
        try {
            JAXBContext jc = JAXBContext.newInstance(RootClass.class);
            InputStream is = new FileInputStream("test.xml");
            Unmarshaller um = jc.createUnmarshaller();
            RootClass object2 = (RootClass) um.unmarshal(is);
            System.out.println(object2.getValue());
            System.out.println(object2.getName().getInnerValue());
            is.close();
        }
        catch (JAXBException e) {e.printStackTrace();}
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }
}
