import java.awt.*;
import java.io.*;

public class Commode extends Fixture implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Constructor for Commode with fixed size (e.g., 50x50 for simplicity)
    public Commode(int x, int y) {
        super(x, y, 10, 10); // 50x50 size
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);  // Set the color to white for the commode
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height); // Draw the square (commode)
        g.setColor(Color.BLACK);  // Set border color to black
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height); // Draw border
    }
}