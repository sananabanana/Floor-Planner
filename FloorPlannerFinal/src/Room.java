import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Room implements Serializable{
	private static final long serialVersionUID = 1L;
    private String name;
    private String roomType;
    private Rectangle bounds;
    private Point originalPosition;
    private Color roomColor;
    private ArrayList<Door> doors;
    private ArrayList<Windows> windows;
    private ArrayList<Fixture> fixtures;
    

    public Room(String name, String roomType, int x, int y, int width, int height) {
        this.name = name;
        this.roomType = roomType;
        this.bounds = new Rectangle(x, y, width, height);
        this.originalPosition = new Point(x, y);
        this.doors = new ArrayList<>();
        this.windows = new ArrayList<>();
        this.fixtures = new ArrayList<>();

        // Set the room color based on the room type
        switch (roomType) {
        case "Living Room":
            roomColor = Color.YELLOW;
            break;
        case "Bedroom":
            roomColor = Color.GREEN;
            break;
        case "Bathroom":
            roomColor = Color.BLUE;
            break;
        case "Dining Hall":
            roomColor = Color.ORANGE;
        case "Kitchen":
            roomColor = Color.RED;
        break;
        default:
            roomColor = Color.LIGHT_GRAY; // Default color
            break;
        }
    }

    public void addFixture(Fixture fixture) {
        fixtures.add(fixture);
    }
   
    public void addDoor(String wall, String alignment) {
      //  doors.add(new Door(wall, alignment, bounds.x, bounds.y, bounds.width, bounds.height));
    	Door newDoor = new Door(wall, alignment, bounds.x, bounds.y, bounds.width, bounds.height);

        // Check if the new door overlaps any existing doors or windows
        if (isOverlapping(newDoor.getBounds())) {
            JOptionPane.showMessageDialog(null, "This door overlaps an existing door or window.", "Overlap Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        doors.add(newDoor);
   
    }
    
    

    private boolean isOverlapping(Rectangle bounds2) {
        for (Door door : doors) {
            if (door.getBounds().intersects(bounds2)) {
                return true;
            }
        }
        // Check overlap with existing windows
        for (Windows window : windows) {
            if (window.getBounds().intersects(bounds2)) {
                return true;
            }
        }
        return false;

	}

	public void addWindow(String wall, String alignment){
       // windows.add(new Windows(wall, alignment, bounds.x, bounds.y, bounds.width, bounds.height));
    	Windows newWindow = new Windows(wall, alignment, bounds.x, bounds.y, bounds.width, bounds.height);
        if (isOverlapping(newWindow.getBounds())) {
            JOptionPane.showMessageDialog(null, "This window overlaps an existing door or window.", "Overlap Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        windows.add(newWindow);
    }

    public String getName() {
        return name;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    

    public void draw(Graphics g) {
        g.setColor(roomColor);  // Use the color based on the room type
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.drawString(name, bounds.x + 5, bounds.y + 15);
        
        
        for (Door door : doors) {
            door.draw(g, roomColor);
        }

        for (Windows window : windows) {
            window.draw(g, roomColor);
        }

        for (Fixture fixture : fixtures) {
            fixture.draw(g);
        }

  

    }

    public void setPosition(int x, int y) {
        bounds.setLocation(x, y);
        for (Door door : doors) {
            door.setPosition(bounds.x, bounds.y, bounds.width, bounds.height);
        }

        for (Windows window : windows) {
            window.setPosition(bounds.x, bounds.y, bounds.width, bounds.height);
        }

    }

    public Point getOriginalPosition() {
        return originalPosition;
    }

    public void resetPosition() {
        bounds.setLocation(originalPosition);
        for (Door door : doors) {
            door.setPosition(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        for (Windows window : windows) {
            window.setPosition(bounds.x, bounds.y, bounds.width, bounds.height);
        }

    }
    public String getRoomType() {
        return roomType;
    }
  
}