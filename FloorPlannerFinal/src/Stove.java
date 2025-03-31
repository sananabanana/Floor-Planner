import java.awt.*;
import java.io.*;

public class Stove extends Fixture implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constructor for Stove with fixed size (e.g., 60x30)
    public Stove(int x, int y) {
        super(x, y, 28, 28); // 60x30 size for the stove
    }

    @Override
    public void draw(Graphics g) {
        // Draw the stove (rectangle)
        g.setColor(Color.WHITE);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

        // Draw burners on the stove (black circles)
        int burnerDiameter = 5; // Diameter of each burner
        int burnerOffsetX = 2; // Offset from the left
        int burnerOffsetY = 2; // Offset from the top

        // Draw 4 burners (2x2 grid)
        g.setColor(Color.BLACK);
        g.fillOval(bounds.x + burnerOffsetX, bounds.y + burnerOffsetY, burnerDiameter, burnerDiameter); // Top-left burner
        g.fillOval(bounds.x + burnerOffsetX + 20, bounds.y + burnerOffsetY, burnerDiameter, burnerDiameter); // Top-right burner
        g.fillOval(bounds.x + burnerOffsetX, bounds.y + burnerOffsetY + 15, burnerDiameter, burnerDiameter); // Bottom-left burner
        g.fillOval(bounds.x + burnerOffsetX + 20, bounds.y + burnerOffsetY + 15, burnerDiameter, burnerDiameter); // Bottom-right burner

        // Optional: Draw border around the stove
        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height); // Border around the stove
    }
}