import java.awt.*;
import java.io.*;

public abstract class Fixture implements Serializable{

    private static final long serialVersionUID = 1L;
    protected int x, y, width, height; // Position and size
    protected String wall; // North, South, East, West
    protected String alignment; // Left, Center, Right, Top, Bottom
    protected Rectangle bounds;
    private boolean isDragging;
    private Point dragOffset;

    // Constructor that initializes position and size
    public Fixture(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
    }

    // Abstract method for drawing the fixture
    public abstract void draw(Graphics g);

    // Set the position based on the room's wall and alignment
    public void setPosition(String wall, String alignment, int roomX, int roomY, int roomWidth, int roomHeight) {
        this.wall = wall;
        this.alignment = alignment;
        
        switch (wall) {
            case "North":
                y = roomY;
                if (alignment.equals("Left")) {
                    x = roomX;
                } else if (alignment.equals("Center")) {
                    x = roomX + (roomWidth - width) / 2;
                } else if (alignment.equals("Right")) {
                    x = roomX + roomWidth - width;
                }
                break;
            case "South":
                y = roomY + roomHeight - height;
                if (alignment.equals("Left")) {
                    x = roomX;
                } else if (alignment.equals("Center")) {
                    x = roomX + (roomWidth - width) / 2;
                } else if (alignment.equals("Right")) {
                    x = roomX + roomWidth - width;
                }
                break;
            case "East":
                x = roomX + roomWidth - width;
                if (alignment.equals("Top")) {
                    y = roomY;
                } else if (alignment.equals("Center")) {
                    y = roomY + (roomHeight - height) / 2;
                } else if (alignment.equals("Bottom")) {
                    y = roomY + roomHeight - height;
                }
                break;
            case "West":
                x = roomX;
                if (alignment.equals("Top")) {
                    y = roomY;
                } else if (alignment.equals("Center")) {
                    y = roomY + (roomHeight - height) / 2;
                } else if (alignment.equals("Bottom")) {
                    y = roomY + roomHeight - height;
                }
                break;
        }
        

        bounds.setLocation(x, y);
    }

   
    

    // Getters and setters
    public Rectangle getBounds() {
        return bounds;
    }

    

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}