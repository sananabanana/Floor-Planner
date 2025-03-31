import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class Floor extends JPanel implements Serializable{
	private static final long serialVersionUID = 1L;
    private ArrayList<Room> rooms;
    private ArrayList<Furniture> furnitureItems;
    private Room selectedRoom;
    private Point lastMousePosition;

    public Floor() {
    	
        this.furnitureItems = new ArrayList<>();

        rooms = new ArrayList<>();
        setLayout(null); // Set layout to null for absolute positioning

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (Room room : rooms) {
                    if (room.getBounds().contains(e.getPoint())) {
                        selectedRoom = room;
                        lastMousePosition = e.getPoint();
                        break;
                    }
                }
            }

          //  @Override
//            public void mouseReleased(MouseEvent e) {
//                if (selectedRoom != null) {
//                    // Calculate the new position of the selected room
//                    int newX = selectedRoom.getBounds().x;
//                    int newY = selectedRoom.getBounds().y;
//
//                    // Create a temporary room with the intended position for collision checking
//                    Room tempRoom = new Room(
//                        selectedRoom.getName(),
//                        selectedRoom.getRoomType(),
//                        newX,
//                        newY,
//                        selectedRoom.getBounds().width,
//                        selectedRoom.getBounds().height
//                    );
//
//                    // Check for overlap against existing rooms
//                    if (canPlaceRoom(tempRoom)) {
//                        selectedRoom.setPosition(newX, newY); // Move the room to the new position
//                    } else {
//                        JOptionPane.showMessageDialog(Floor.this, "Error: Rooms cannot overlap!", "Overlap Error", JOptionPane.ERROR_MESSAGE);
//                        selectedRoom.resetPosition(); // Reset position if overlap occurs
//                    }
//
//                    selectedRoom = null; // Deselect the room
//                    repaint(); // Refresh the display
//                }
//            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedRoom != null) {
                    // Calculate the new position of the selected room
                    int newX = selectedRoom.getBounds().x;
                    int newY = selectedRoom.getBounds().y;

                    // Adjust the position if it goes out of bounds
                    if (newX < 0) newX = 0;
                    if (newY < 0) newY = 0;
                    if (newX + selectedRoom.getBounds().width > getWidth()) {
                        newX = getWidth() - selectedRoom.getBounds().width;
                    }
                    if (newY + selectedRoom.getBounds().height > getHeight()) {
                        newY = getHeight() - selectedRoom.getBounds().height;
                    }

                    // Create a temporary room with the adjusted position for collision checking
                    Room tempRoom = new Room(
                        selectedRoom.getName(),
                        selectedRoom.getRoomType(),
                        newX,
                        newY,
                        selectedRoom.getBounds().width,
                        selectedRoom.getBounds().height
                    );

                    // Check for overlap against existing rooms
                    if (canPlaceRoom(tempRoom)) {
                        selectedRoom.setPosition(newX, newY); // Move the room to the new position
                    } else {
                        JOptionPane.showMessageDialog(Floor.this, "Error: Rooms cannot overlap!", "Overlap Error", JOptionPane.ERROR_MESSAGE);
                        selectedRoom.resetPosition(); // Reset position if overlap occurs
                    }

                    selectedRoom = null; // Deselect the room
                    repaint(); // Refresh the display
                }
            }

        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedRoom != null) {
                    int dx = e.getX() - lastMousePosition.x;
                    int dy = e.getY() - lastMousePosition.y;
                    selectedRoom.setPosition(selectedRoom.getBounds().x + dx, selectedRoom.getBounds().y + dy);
                    lastMousePosition = e.getPoint();
                    repaint(); // Update the display as the room is dragged
                }
            }
        });
    }

    public void addFurniture(Furniture furniture) {
        furnitureItems.add(furniture);
        furniture.enableDragging(this); // Enable dragging for the furniture
        repaint();
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public String[] getRoomNames() {
        String[] roomNames = new String[rooms.size()];
        for (int i = 0; i < rooms.size(); i++) {
            roomNames[i] = rooms.get(i).getName();
        }
        return roomNames;
    }

    public Room getRoomByName(String name) {
        for (Room room : rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    public boolean hasAdjacentRoom(Room room, String wall) {
        Rectangle roomBounds = room.getBounds();
        
        // Define the bounds of the area we're checking for neighboring rooms
        Rectangle checkBounds = new Rectangle(roomBounds);
        switch (wall) {
            case "North":
                checkBounds.setLocation(roomBounds.x, roomBounds.y - roomBounds.height);
                break;
            case "South":
                checkBounds.setLocation(roomBounds.x, roomBounds.y + roomBounds.height);
                break;
            case "East":
                checkBounds.setLocation(roomBounds.x + roomBounds.width, roomBounds.y);
                break;
            case "West":
                checkBounds.setLocation(roomBounds.x - roomBounds.width, roomBounds.y);
                break;
        }
        
        // Check if any other room intersects with this area
        for (Room otherRoom : rooms) {
            if (otherRoom != room && otherRoom.getBounds().intersects(checkBounds)) {
                return true; // Adjacent room found
            }
        }
        return false; // No adjacent room
    }

    // Method to calculate new room position based on the relative direction and alignment
    public Point calculatePositionRelative(Room referenceRoom, String direction, String alignment, int newRoomWidth, int newRoomHeight) {
        Rectangle refBounds = referenceRoom.getBounds();
        int newX = 0, newY = 0;

        // Calculate position based on direction
        switch (direction) {
            case "North":
                newY = refBounds.y - newRoomHeight;
                newX = refBounds.x;
                break;
            case "South":
                newY = refBounds.y + refBounds.height;
                newX = refBounds.x;
                break;
            case "East":
                newX = refBounds.x + refBounds.width;
                newY = refBounds.y;
                break;
            case "West":
                newX = refBounds.x - newRoomWidth;
                newY = refBounds.y;
                break;
        }

        // Adjust for alignment
        switch (alignment) {
            case "Align Left":
                newX = refBounds.x;
                break;
            case "Align Right":
                newX = refBounds.x + refBounds.width - newRoomWidth;
                break;
            case "Align Top":
                newY = refBounds.y;
                break;
            case "Align Bottom":
                newY = refBounds.y + refBounds.height - newRoomHeight;
                break;
        }

        return new Point(newX, newY);
    }

//    public boolean addRoom(Room room) {
//        if (canPlaceRoom(room)) {
//            rooms.add(room);
//            
//            repaint();
//            return true;
//        }
//        return false;
//    }

    
    public boolean addRoom(Room room) {
        if (canPlaceRoom(room) && isWithinBounds(room)) {
            rooms.add(room);
            repaint();
            return true;
        }
        JOptionPane.showMessageDialog(this, "Error: Room exceeds screen bounds!", "Boundary Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean isValidWindowPlacement(Room room, String wall) {
        Rectangle roomBounds = room.getBounds();
    
        // Simulate the window placement
        Rectangle windowBounds = null;
        switch (wall) {
            case "North":
                windowBounds = new Rectangle(roomBounds.x, roomBounds.y - 5, roomBounds.width, 5);
                break;
            case "South":
                windowBounds = new Rectangle(roomBounds.x, roomBounds.y + roomBounds.height, roomBounds.width, 5);
                break;
            case "East":
                windowBounds = new Rectangle(roomBounds.x + roomBounds.width, roomBounds.y, 5, roomBounds.height);
                break;
            case "West":
                windowBounds = new Rectangle(roomBounds.x - 5, roomBounds.y, 5, roomBounds.height);
                break;
        }
    
        // Check for overlaps with other rooms
        for (Room otherRoom : rooms) {
            if (otherRoom != room && otherRoom.getBounds().intersects(windowBounds)) {
                return false;
            }
        }
    
        return true;
    }
    

    private boolean isWithinBounds(Room room) {
        Rectangle roomBounds = room.getBounds();
        return roomBounds.x >= 0 && roomBounds.y >= 0 &&
               roomBounds.x + roomBounds.width <= getWidth() &&
               roomBounds.y + roomBounds.height <= getHeight();
    }
    
    
    private boolean canPlaceRoom(Room room) {
    
        for (Room r : rooms) {
            if (r != selectedRoom && r.getBounds().intersects(room.getBounds())) {
                return false; // Overlap detected
            }
        }
        return true; // No overlap
    }

    public ArrayList<Furniture> getFurnitureItems() {
        return furnitureItems;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Room room : rooms) {
            room.draw(g);
        }

        for (Furniture furniture : furnitureItems) {
            furniture.draw(g);
        }
        
    }

}