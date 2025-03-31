import java.awt.*;
import java.io.*;

public class DiningSet extends Furniture implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private static final int TABLE_SIZE = 20;    // Size of the square table
    private static final int CHAIR_RADIUS = 5; // Radius of the circular chairs
    private static final int OFFSET = 6;       // Distance between the table and chairs

    public DiningSet() {
        super(0, 0, TABLE_SIZE + OFFSET * 2, TABLE_SIZE + OFFSET * 2); // Automatically positioned at (0, 0)
    }

    @Override
    public void draw(Graphics g) {
        int tableX = bounds.x + OFFSET;
        int tableY = bounds.y + OFFSET;
        int tableSize = TABLE_SIZE;

        // Draw the table
        g.setColor(new Color(139, 69, 19)); // Brown color for the table
        g.fillRect(tableX, tableY, tableSize, tableSize);

        // Draw the table outline
        g.setColor(Color.BLACK);
        g.drawRect(tableX, tableY, tableSize, tableSize);

        // Draw the chairs
        g.setColor(new Color(160, 82, 45)); // Lighter brown for chairs
        // Top chair
        g.fillOval(tableX + tableSize / 2 - CHAIR_RADIUS / 2, tableY - CHAIR_RADIUS - OFFSET / 2, CHAIR_RADIUS, CHAIR_RADIUS);
        // Bottom chair
        g.fillOval(tableX + tableSize / 2 - CHAIR_RADIUS / 2, tableY + tableSize + OFFSET / 2, CHAIR_RADIUS, CHAIR_RADIUS);
        // Left chair
        g.fillOval(tableX - CHAIR_RADIUS - OFFSET / 2, tableY + tableSize / 2 - CHAIR_RADIUS / 2, CHAIR_RADIUS, CHAIR_RADIUS);
        // Right chair
        g.fillOval(tableX + tableSize + OFFSET / 2, tableY + tableSize / 2 - CHAIR_RADIUS / 2, CHAIR_RADIUS, CHAIR_RADIUS);

        // Draw chair outlines
        g.setColor(Color.BLACK);
        // Top chair outline
        g.drawOval(tableX + tableSize / 2 - CHAIR_RADIUS / 2, tableY - CHAIR_RADIUS - OFFSET / 2, CHAIR_RADIUS, CHAIR_RADIUS);
        // Bottom chair outline
        g.drawOval(tableX + tableSize / 2 - CHAIR_RADIUS / 2, tableY + tableSize + OFFSET / 2, CHAIR_RADIUS, CHAIR_RADIUS);
        // Left chair outline
        g.drawOval(tableX - CHAIR_RADIUS - OFFSET / 2, tableY + tableSize / 2 - CHAIR_RADIUS / 2, CHAIR_RADIUS, CHAIR_RADIUS);
        // Right chair outline
        g.drawOval(tableX + tableSize + OFFSET / 2, tableY + tableSize / 2 - CHAIR_RADIUS / 2, CHAIR_RADIUS, CHAIR_RADIUS);
    }
}