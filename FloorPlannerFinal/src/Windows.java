import java.awt.*;
import java.io.*;

public class Windows implements Serializable{
    private String wall; // "North", "South", "East", "West"
    private String alignment; // "Left", "Center", "Right"
    private Rectangle bounds;

    private static final long serialVersionUID = 1L;

    public Windows(String wall, String alignment, int roomX, int roomY, int roomWidth, int roomHeight) {
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
        Graphics2D g2d = (Graphics2D) g;
    
        // Save the original stroke to restore it later
        Stroke originalStroke = g2d.getStroke();
    
        // Create a dotted line stroke
        float[] dashPattern = {5, 5}; // 5-pixel dash, 5-pixel gap
        BasicStroke dottedStroke = new BasicStroke(
            2,                     // Line thickness
            BasicStroke.CAP_BUTT,  // End cap style
            BasicStroke.JOIN_BEVEL,// Line join style
            0,                     // Miter limit (irrelevant for dashed lines)
            dashPattern,           // Dash pattern
            0                      // Dash phase
        );
        g2d.setStroke(dottedStroke);
    
        // Set color for the dotted line (e.g., black or a contrasting color)
        g2d.setColor(Color.BLACK);
    
        // Draw the dotted line based on the bounds
        if ("North".equals(wall)) {
            // Horizontal line
            g2d.drawLine(bounds.x - 1, bounds.y  , bounds.x + bounds.width + 1 , bounds.y);
        } 
        else if ("South".equals(wall)) {
            // Horizontal line
            g2d.drawLine(bounds.x - 1, bounds.y + 6 , bounds.x + bounds.width + 1 , bounds.y + 6);
        }
        else if("East".equals(wall)){
            // Vertical line
            g2d.drawLine(bounds.x + 5, bounds.y - 1, bounds.x+ 5, bounds.y + bounds.height- 1);
        }
        else{
            g2d.drawLine(bounds.x + 1, bounds.y - 1, bounds.x+ 1, bounds.y + bounds.height- 1);
        }
    
        // Restore the original stroke
        g2d.setStroke(originalStroke);
    }
    
}