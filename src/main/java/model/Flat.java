package model;

import java.util.ArrayList;

public class Flat {
    private String floor, number;
    private final ArrayList<Room> rooms = new ArrayList<>();

    public Flat(String floor, String number) {
        this.floor = floor;
        this.number = number;
    }

    public Flat() {
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFloor() {
        return floor;
    }

    public String getNumber() {
        return number;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Room room) {
        rooms.add(room);
    }
}
