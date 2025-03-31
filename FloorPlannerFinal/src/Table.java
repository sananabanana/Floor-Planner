import java.awt.*;
import java.io.*;

public class Table extends Furniture implements Serializable {
    private static final int TABLE_WIDTH = 15; // Fixed width
    private static final int TABLE_HEIGHT = 7; // Fixed height
    private static final int LEG_WIDTH = 3; // Width of table legs
    private static final int LEG_HEIGHT = 4; // Height of table legs
    private Color tableColor;

    private static final long serialVersionUID = 1L;

    public Table() {
        super(0, 0, TABLE_WIDTH, TABLE_HEIGHT); // Automatically positioned at (0, 0)
        this.tableColor = Color.ORANGE; // Default table color
    }

    @Override
    public void draw(Graphics g) {
        int x = bounds.x;
        int y = bounds.y;
        int width = bounds.width;
        int height = bounds.height;

        // Draw tabletop
        g.setColor(tableColor);
        g.fillRect(x, y, width, height);

        // Draw table legs
        g.setColor(Color.DARK_GRAY);
        // Top-left leg
        g.fillRect(x, y + height, LEG_WIDTH, LEG_HEIGHT);
        // Top-right leg
        g.fillRect(x + width - LEG_WIDTH, y + height, LEG_WIDTH, LEG_HEIGHT);
        // Bottom-left leg
        g.fillRect(x, y + height + LEG_HEIGHT - 2, LEG_WIDTH, LEG_HEIGHT);
        // Bottom-right leg
        g.fillRect(x + width - LEG_WIDTH, y + height + LEG_HEIGHT - 2, LEG_WIDTH, LEG_HEIGHT);

        // Draw outline for tabletop
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
}