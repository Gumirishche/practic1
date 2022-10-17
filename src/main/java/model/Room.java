package model;

public class Room {
    private String width, height;

    public Room(String width, String height) {
        this.height = height;
        this.width = width;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
