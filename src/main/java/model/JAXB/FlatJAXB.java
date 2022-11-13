package model.JAXB;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;


@XmlRootElement(name = "flat")
public class FlatJAXB {
    private String floor, number;

    private ArrayList<RoomJAXB> rooms= new ArrayList<>();

    public String getFloor() {
        return floor;
    }
    @XmlElement(name = "floor")
    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getNumber() {
        return number;
    }
    @XmlElement(name = "number")
    public void setNumber(String number) {
        this.number = number;
    }

    public ArrayList<RoomJAXB> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<RoomJAXB> rooms) {
        this.rooms = rooms;
    }
}
