import java.awt.*;
import java.io.*;

public class Chair extends Furniture implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int SEAT_WIDTH = 15;    // Width of the seat
    private static final int SEAT_HEIGHT = 7;  // Height of the seat
    private static final int BACKREST_HEIGHT = 7; // Height of the backrest
    private static final int LEG_WIDTH = 4;     // Width of the chair legs
    private static final int LEG_HEIGHT =5;   // Height of the chair legs

    public Chair() {
        super(0, 0, SEAT_WIDTH, SEAT_HEIGHT + BACKREST_HEIGHT); // Automatically positioned at (0, 0)
    }

    @Override
    public void draw(Graphics g) {
        int seatX = bounds.x;
        int seatY = bounds.y + BACKREST_HEIGHT; // The seat is below the backrest
        int seatWidth = SEAT_WIDTH;
        int seatHeight = SEAT_HEIGHT;

        // Draw the seat
        g.setColor(new Color(139, 69, 19)); // Brown color for the seat
        g.fillRect(seatX, seatY, seatWidth, seatHeight);

        // Draw the seat outline
        g.setColor(Color.BLACK);
        g.drawRect(seatX, seatY, seatWidth, seatHeight);

        // Draw the backrest
        g.setColor(new Color(160, 82, 45)); // Lighter brown for the backrest
        g.fillRect(seatX, bounds.y, seatWidth, BACKREST_HEIGHT);

        // Draw the backrest outline
        g.setColor(Color.BLACK);
        g.drawRect(seatX, bounds.y, seatWidth, BACKREST_HEIGHT);

        // Draw the legs
        g.setColor(new Color(105, 55, 25)); // Darker brown for the legs
        // Left leg
        g.fillRect(seatX, seatY + seatHeight, LEG_WIDTH, LEG_HEIGHT);
        // Right leg
        g.fillRect(seatX + seatWidth - LEG_WIDTH, seatY + seatHeight, LEG_WIDTH, LEG_HEIGHT);

        // Draw the leg outlines
        g.setColor(Color.BLACK);
        g.drawRect(seatX, seatY + seatHeight, LEG_WIDTH, LEG_HEIGHT); // Left leg
        g.drawRect(seatX + seatWidth - LEG_WIDTH, seatY + seatHeight, LEG_WIDTH, LEG_HEIGHT); // Right leg
    }
}