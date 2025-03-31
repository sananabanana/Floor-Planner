import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;


public abstract class Furniture implements Serializable{
    protected Rectangle bounds; // Position and size
    private boolean isDragging;
    private Point dragOffset;

    //private static final long serialVersionUID = 1L;

    public Furniture(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
        isDragging = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setPosition(int x, int y) {
        bounds.setLocation(x, y);
    }

    // Enable dragging behavior
    public void enableDragging(Component parent) {
        parent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (bounds.contains(e.getPoint())) {
                    isDragging = true;
                    dragOffset = new Point(e.getX() - bounds.x, e.getY() - bounds.y);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
            }
        });

        parent.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    setPosition(e.getX() - dragOffset.x, e.getY() - dragOffset.y);
                    parent.repaint();
                }
            }
        });
    }

    // Abstract draw method to be implemented by subclasses
    public abstract void draw(Graphics g);
}