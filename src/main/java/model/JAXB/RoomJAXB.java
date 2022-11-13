package model.JAXB;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Room")
public class RoomJAXB {
    private String width, height;

    public String getWidth() {
        return width;
    }
    @XmlElement(name = "width")
    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }
    @XmlElement(name = "height")
    public void setHeight(String height) {
        this.height = height;
    }
}
