import java.awt.*;
import java.io.*;

public class Bed extends Furniture implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_WIDTH = 35;  // Smaller width
    private static final int DEFAULT_HEIGHT = 50; // Smaller height

    public Bed() {
        super(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT); // Initialize the parent class
    }

    @Override
    public void draw(Graphics g) {
        // Draw bed frame
        g.setColor(new Color(139, 69, 19)); // Brown for bed frame
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        // Draw pillows
        g.setColor(Color.WHITE); // White pillows
        int pillowWidth = bounds.width / 2 - 10;
        int pillowHeight = bounds.height / 6;

        g.fillRect(bounds.x + 10, bounds.y + 5, pillowWidth, pillowHeight); // Left pillow
        g.fillRect(bounds.x + bounds.width / 2, bounds.y + 5, pillowWidth, pillowHeight); // Right pillow

        // Draw bed outline
        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}