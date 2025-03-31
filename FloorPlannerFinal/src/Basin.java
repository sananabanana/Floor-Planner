import java.awt.*;
import java.io.*;

public class Basin extends Fixture implements Serializable{

    private static final long serialVersionUID = 1L;

    public Basin(int x, int y) {
        super(x, y, 20, 20);  // Fixed size for the Basin (width=50, height=30)
    }

    @Override
    public void draw(Graphics g) {
        // Draw the white basin (body)
        g.setColor(Color.WHITE);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height); // Basin body

        // Draw the outline of the basin (optional)
        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

        // Draw the drain (circle) in the center of the basin
        int drainDiameter = 8; // Diameter of the drain
        int drainX = bounds.x + (bounds.width - drainDiameter) / 2; // X position of the drain
        int drainY = bounds.y + (bounds.height - drainDiameter) / 2; // Y position of the drain
        g.setColor(Color.DARK_GRAY); // Drain color
        g.fillOval(drainX, drainY, drainDiameter, drainDiameter); // Draw the drain
    }
}
