import java.awt.*;
import java.io.*;

public class Sofa extends Furniture implements Serializable {
    private static final int SOFA_WIDTH = 30; // Fixed width
    private static final int SOFA_HEIGHT = 20; // Fixed height
    private static final int ARMREST_WIDTH = 7; // Fixed width for armrests
    private Color sofaColor;

    private static final long serialVersionUID = 1L;

    public Sofa() {
        super(0, 0, SOFA_WIDTH, SOFA_HEIGHT); // Automatically positioned at (0, 0)
        this.sofaColor = Color.CYAN; // Default sofa color
    }

    @Override
    public void draw(Graphics g) {
        int x = bounds.x;
        int y = bounds.y;
        int width = bounds.width;
        int height = bounds.height;

        // Draw the sofa base
        g.setColor(sofaColor);
        g.fillRect(x, y, width, height);

        // Draw armrests
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, ARMREST_WIDTH, height); // Left armrest
        g.fillRect(x + width - ARMREST_WIDTH, y, ARMREST_WIDTH, height); // Right armrest

        // Draw cushion lines
        g.setColor(Color.BLACK);
        int lineSpacing = height / 3; // Divide the sofa into 4 parts for 3 lines
        for (int i = 1; i <= 2; i++) {
            int lineY = y + i * lineSpacing;
            g.drawLine(x + ARMREST_WIDTH, lineY, x + width - ARMREST_WIDTH, lineY); // Lines go across sofa
        }

        // Draw outline
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height); // Outline for sofa
    }
}