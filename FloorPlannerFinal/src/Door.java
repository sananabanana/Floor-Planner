import java.awt.*;
import java.io.*;

public class Door implements Serializable{
	private static final long serialVersionUID = 1L;
    private String wall; // "North", "South", "East", "West"
    private String alignment; // "Left", "Center", "Right"
    private Rectangle bounds;

    public Door(String wall, String alignment, int roomX, int roomY, int roomWidth, int roomHeight) {
        this.wall = wall;
        this.alignment = alignment;
        setPosition(roomX, roomY, roomWidth, roomHeight);
    }
    public Rectangle getBounds() {
        return bounds;
    }

    public void setPosition(int roomX, int roomY, int roomWidth, int roomHeight) {
        int doorWidth = 20;
        int doorThickness = 5;
    
        switch (wall) {
            case "North":
                bounds = new Rectangle(
                    roomX + ("Center".equals(alignment) ? roomWidth / 2 - doorWidth / 2 : "Right".equals(alignment) ? roomWidth - doorWidth : 0),
                    roomY,
                    doorWidth,
                    doorThickness
                );
                break;
            case "South":
                bounds = new Rectangle(
                    roomX + ("Center".equals(alignment) ? roomWidth / 2 - doorWidth / 2 : "Right".equals(alignment) ? roomWidth - doorWidth : 0),
                    roomY + roomHeight - doorThickness,
                    doorWidth,
                    doorThickness
                );
                break;
            case "East":
                bounds = new Rectangle(
                    roomX + roomWidth - doorThickness,
                    roomY + ("Center".equals(alignment) ? roomHeight / 2 - doorWidth / 2 : "Bottom".equals(alignment) ? roomHeight - doorWidth : 0),
                    doorThickness,
                    doorWidth
                );
                break;
            case "West":
                bounds = new Rectangle(
                    roomX,
                    roomY + ("Center".equals(alignment) ? roomHeight / 2 - doorWidth / 2 : "Bottom".equals(alignment) ? roomHeight - doorWidth : 0),
                    doorThickness,
                    doorWidth
                );
                break;
        }
    }

    

    public void draw(Graphics g, Color roomColor) {
        g.setColor(roomColor); // Draw gap with room color

        if ("North".equals(wall)){
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height - 2);
        }
        if ("South".equals(wall)){
            g.fillRect(bounds.x - 1, bounds.y + 3 , bounds.width + 1, bounds.height - 1);
        }
        if ("East".equals(wall)){
            g.fillRect(bounds.x + 1, bounds.y + 2 , bounds.width + 1, bounds.height - 1);
        }
        if ("West".equals(wall)){
            g.fillRect(bounds.x - 1, bounds.y + 3 , bounds.width + 1, bounds.height - 1);
        }
    }
	
}