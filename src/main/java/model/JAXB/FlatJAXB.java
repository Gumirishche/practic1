package model.JAXB;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;


@XmlRootElement(name = "flat")
@XmlType(propOrder = {"floor", "number", "rooms", "area"})
public class FlatJAXB {
    private String floor, number, area;

    private ArrayList<RoomJAXB> rooms;

    public String getArea() {
        return area;
    }

    @XmlElement(name = "area")
    public void setArea(String area) {
        this.area = area;
    }

    public String getFloor() {
        return floor;
    }

    @XmlAttribute(name = "floor")
    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getNumber() {
        return number;
    }

    @XmlAttribute(name = "number")
    public void setNumber(String number) {
        this.number = number;
    }

    public ArrayList<RoomJAXB> getRooms() {
        return rooms;
    }

    @XmlElement(name = "room")
    public void setRooms(ArrayList<RoomJAXB> rooms) {
        this.rooms = rooms;
    }
}
