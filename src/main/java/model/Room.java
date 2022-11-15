package model;

public class Room {
    private final String width;
    private final String height;

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
