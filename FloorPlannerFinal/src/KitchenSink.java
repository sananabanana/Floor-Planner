import java.awt.*;
import java.io.*;

public class KitchenSink extends Fixture implements Serializable {
    private static final long serialVersionUID = 1L;

    public KitchenSink(int x, int y) {
        super(x, y, 20, 20); 
    }

    @Override
    public void draw(Graphics g) {
        // Draw the kitchen sink (rectangle)
        g.setColor(Color.PINK);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        // Draw a dark gray circle for the drain in the center
        int drainDiameter = 10; // Diameter of the drain
        int drainX = bounds.x + (bounds.width - drainDiameter) / 2;
        int drainY = bounds.y + (bounds.height - drainDiameter) / 2;

        g.setColor(Color.DARK_GRAY);
        g.fillOval(drainX, drainY, drainDiameter, drainDiameter);

        // Optional: Draw border around the sink
        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height); // Border around the sink
    }
}