import java.awt.*;
import java.io.*;

public class Shower extends Fixture implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constructor for Shower with fixed size (e.g., 60x40 for simplicity)
    public Shower(int x, int y) {
        super(x, y, 30, 30); // 60x40 size for the shower
    }

    @Override
    public void draw(Graphics g) {
        // Draw the blue tiles (Shower rectangle)
        g.setColor(Color.BLUE);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        // Draw a simple tile pattern (blue grid lines)
        g.setColor(Color.WHITE); // Grid lines in white to represent tiles

        int tileWidth = 10; // Tile size
        int tileHeight = 10;

        // Draw horizontal lines for tiles
        for (int i = bounds.y; i < bounds.y + bounds.height; i += tileHeight) {
            g.drawLine(bounds.x, i, bounds.x + bounds.width, i);
        }

        // Draw vertical lines for tiles
        for (int i = bounds.x; i < bounds.x + bounds.width; i += tileWidth) {
            g.drawLine(i, bounds.y, i, bounds.y + bounds.height);
        }

        // Optional: Draw border around the shower
        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height); // Border around the shower
    }
}