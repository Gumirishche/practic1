package model.JAXB;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Room")
@XmlType(propOrder = {"width", "height"})
public class RoomJAXB {
    private String width, height;

    public String getWidth() {
        return width;
    }
    @XmlAttribute(name = "width")
    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }
    @XmlAttribute(name = "height")
    public void setHeight(String height) {
        this.height = height;
    }
}
