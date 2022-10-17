package DOM;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.IOException;

public class Writer {
    public static void main(String[] args) throws ParserConfigurationException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            Document document = builder.parse("flat1.xml");
            DOMSource dom_source = new DOMSource(document);
            StreamResult out_stream = new StreamResult("flatNew.xml");
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
        } catch (IOException | TransformerException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
