import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class FloorPlanner extends JFrame implements Serializable {
    private Floor floor;
    //saving attempt
    private static final long serialVersionUID = 1L;
 

    public FloorPlanner() {
        setTitle("Floor Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        

        floor = new Floor();
        add(floor, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Add Room");
        controlPanel.add(addButton);
        add(controlPanel, BorderLayout.WEST);
        
        JButton addButton1 = new JButton("Add Door");
        controlPanel.add(addButton1);
        add(controlPanel, BorderLayout.WEST);

        JButton addButton2 = new JButton("Add Window");
        controlPanel.add(addButton2);
        add(controlPanel, BorderLayout.WEST);

        JButton addBedButton = new JButton("Add Bed"); 
        controlPanel.add(addBedButton);
        add(controlPanel, BorderLayout.WEST);

        JButton addSofaButton = new JButton("Add Sofa");
        controlPanel.add(addSofaButton);
        add(controlPanel, BorderLayout.WEST);


        addSofaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sofa sofa = new Sofa();
                sofa.setPosition(50, 50); // Default initial position
                floor.addFurniture(sofa); // Add the bed to the floor
            }
        });

        JButton addTableButton = new JButton("Add Table");
        controlPanel.add(addTableButton);
        add(controlPanel, BorderLayout.WEST);


        addTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Table table = new Table();
                table.setPosition(50, 50); // Default initial position
                floor.addFurniture(table); // Add the bed to the floor
            }
        });

        JButton addDiningSetButton = new JButton("Add DiningSet");
        controlPanel.add(addDiningSetButton);
        add(controlPanel, BorderLayout.WEST);


        addDiningSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiningSet s = new DiningSet();
                s.setPosition(50, 50); // Default initial position
                floor.addFurniture(s); // Add the bed to the floor
            }
        });

        JButton addChairButton = new JButton("Add Chair");
        controlPanel.add(addChairButton);
        add(controlPanel, BorderLayout.WEST);


        addChairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Chair c = new Chair();
                c.setPosition(50, 50); // Default initial position
                floor.addFurniture(c); // Add the bed to the floor
            }
        });

        JButton addBasinButton = new JButton("Add Basin");
        controlPanel.add(addBasinButton);
        add(controlPanel, BorderLayout.WEST);

        addBasinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ensure rooms are available
                ArrayList<Room> bathroomRooms = new ArrayList<>();
                for (Room room : floor.getRooms()) {
                    if ("Bathroom".equals(room.getRoomType())) {
                        bathroomRooms.add(room);
                    }
                }
                
                // If no Bathroom rooms are available, show an error message
                if (bathroomRooms.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No Bathroom rooms available to add this fixture.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Create an array of Bathroom room names for the dialog
                String[] roomNames = new String[bathroomRooms.size()];
                for (int i = 0; i < bathroomRooms.size(); i++) {
                    roomNames[i] = bathroomRooms.get(i).getName();
                }
                
                // Prompt user to select a Bathroom room
                String roomName = (String) JOptionPane.showInputDialog(
                        null,
                        "Select a Bathroom room to add this fixture:",
                        "Select Room",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        roomNames,
                        roomNames[0]
                );
                
                if (roomName == null) return; // Cancelled
                
                // Get the selected room
                Room selectedRoom = null;
                for (Room room : bathroomRooms) {
                    if (room.getName().equals(roomName)) {
                        selectedRoom = room;
                        break;
                    }
                }
                

                String[] walls = {"North", "South", "East", "West"};
                String selectedWall = (String) JOptionPane.showInputDialog(
                        null,
                        "Select wall to add the basin:",
                        "Select Wall",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        walls,
                        walls[0]
                );

                if (selectedWall == null) return;

                String[] alignments = selectedWall.equals("North") || selectedWall.equals("South") ?
                        new String[]{"Left", "Center", "Right"} :
                        new String[]{"Top", "Center", "Bottom"};

                String selectedAlignment = (String) JOptionPane.showInputDialog(
                        null,
                        "Select alignment of the basin on the wall:",
                        "Basin Alignment",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        alignments,
                        alignments[1]
                );

                if (selectedAlignment == null) return;

                // Create the basin and set its position
                Basin basin = new Basin(0, 0);  // Position at (0, 0)
                basin.setPosition(selectedWall, selectedAlignment, selectedRoom.getBounds().x, selectedRoom.getBounds().y,
                        selectedRoom.getBounds().width, selectedRoom.getBounds().height);

                selectedRoom.addFixture(basin);  // Add the basin to the room
                floor.repaint();  // Repaint the floor to show the new basin
            }
        });

        JButton addCommodeButton = new JButton("Add Commode");
        controlPanel.add(addCommodeButton);
        add(controlPanel, BorderLayout.WEST);

        addCommodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ensure rooms are available
                ArrayList<Room> bathroomRooms = new ArrayList<>();
    for (Room room : floor.getRooms()) {
    if ("Bathroom".equals(room.getRoomType())) {
        bathroomRooms.add(room);
    }
    }

// If no Bathroom rooms are available, show an error message
    if (bathroomRooms.isEmpty()) {
    JOptionPane.showMessageDialog(null, "No Bathroom rooms available to add this fixture.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
    }

// Create an array of Bathroom room names for the dialog
    String[] roomNames = new String[bathroomRooms.size()];
    for (int i = 0; i < bathroomRooms.size(); i++) {
    roomNames[i] = bathroomRooms.get(i).getName();
    }

// Prompt user to select a Bathroom room
    String roomName = (String) JOptionPane.showInputDialog(
        null,
        "Select a Bathroom room to add this fixture:",
        "Select Room",
        JOptionPane.QUESTION_MESSAGE,
        null,
        roomNames,
        roomNames[0]
    );

    if (roomName == null) return; // Cancelled

    // Get the selected room
    Room selectedRoom = null;
    for (Room room : bathroomRooms) {
        if (room.getName().equals(roomName)) {
        selectedRoom = room;
        break;
        }
    }


                String[] walls = {"North", "South", "East", "West"};
                String selectedWall = (String) JOptionPane.showInputDialog(
                        null,
                        "Select wall to add the basin:",
                        "Select Wall",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        walls,
                        walls[0]
                );

                if (selectedWall == null) return;

                String[] alignments = selectedWall.equals("North") || selectedWall.equals("South") ?
                        new String[]{"Left", "Center", "Right"} :
                        new String[]{"Top", "Center", "Bottom"};

                String selectedAlignment = (String) JOptionPane.showInputDialog(
                        null,
                        "Select alignment of the basin on the wall:",
                        "Basin Alignment",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        alignments,
                        alignments[1]
                );

                if (selectedAlignment == null) return;

                // Create the basin and set its position
                Commode commode = new Commode(0, 0);  // Position at (0, 0)
                commode.setPosition(selectedWall, selectedAlignment, selectedRoom.getBounds().x, selectedRoom.getBounds().y,
                        selectedRoom.getBounds().width, selectedRoom.getBounds().height);

                selectedRoom.addFixture(commode);  // Add the basin to the room
                floor.repaint();  // Repaint the floor to show the new basin
            }
        });

        JButton addShowerButton = new JButton("Add Shower");
        controlPanel.add(addShowerButton);
        add(controlPanel, BorderLayout.WEST);

        addShowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ensure rooms are available
                ArrayList<Room> bathroomRooms = new ArrayList<>();
                for (Room room : floor.getRooms()) {
                    if ("Bathroom".equals(room.getRoomType())) {
                        bathroomRooms.add(room);
                    }
                }
                
                // If no Bathroom rooms are available, show an error message
                if (bathroomRooms.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No Bathroom rooms available to add this fixture.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Create an array of Bathroom room names for the dialog
                String[] roomNames = new String[bathroomRooms.size()];
                for (int i = 0; i < bathroomRooms.size(); i++) {
                    roomNames[i] = bathroomRooms.get(i).getName();
                }
                
                // Prompt user to select a Bathroom room
                String roomName = (String) JOptionPane.showInputDialog(
                        null,
                        "Select a Bathroom room to add this fixture:",
                        "Select Room",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        roomNames,
                        roomNames[0]
                );
                
                if (roomName == null) return; // Cancelled
                
                // Get the selected room
                Room selectedRoom = null;
                for (Room room : bathroomRooms) {
                    if (room.getName().equals(roomName)) {
                        selectedRoom = room;
                        break;
                    }
                }                

                String[] walls = {"North", "South", "East", "West"};
                String selectedWall = (String) JOptionPane.showInputDialog(
                        null,
                        "Select wall to add the basin:",
                        "Select Wall",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        walls,
                        walls[0]
                );

                if (selectedWall == null) return;

                String[] alignments = selectedWall.equals("North") || selectedWall.equals("South") ?
                        new String[]{"Left", "Center", "Right"} :
                        new String[]{"Top", "Center", "Bottom"};

                String selectedAlignment = (String) JOptionPane.showInputDialog(
                        null,
                        "Select alignment of the basin on the wall:",
                        "Basin Alignment",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        alignments,
                        alignments[1]
                );

                if (selectedAlignment == null) return;

                // Create the basin and set its position
                Shower shower = new Shower(0, 0);  // Position at (0, 0)
                shower.setPosition(selectedWall, selectedAlignment, selectedRoom.getBounds().x, selectedRoom.getBounds().y,
                        selectedRoom.getBounds().width, selectedRoom.getBounds().height);

                selectedRoom.addFixture(shower);  // Add the basin to the room
                floor.repaint();  // Repaint the floor to show the new basin
            }
        });

        JButton addKitchenSinkButton = new JButton("Add KitchenSink");
        controlPanel.add(addKitchenSinkButton);
        add(controlPanel, BorderLayout.WEST);

        addKitchenSinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ensure rooms are available
                ArrayList<Room> kitchenRooms = new ArrayList<>();
        for (Room room : floor.getRooms()) {
            if ("Kitchen".equals(room.getRoomType())) {
                kitchenRooms.add(room);
            }
        }

        // If no Kitchen rooms are available, show an error message
        if (kitchenRooms.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Kitchen rooms available to add a stove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create an array of Kitchen room names for the dialog
        String[] roomNames = new String[kitchenRooms.size()];
        for (int i = 0; i < kitchenRooms.size(); i++) {
            roomNames[i] = kitchenRooms.get(i).getName();
        }

        // Prompt user to select a Kitchen room
        String roomName = (String) JOptionPane.showInputDialog(
                null,
                "Select a Kitchen room to add the stove:",
                "Select Room",
                JOptionPane.QUESTION_MESSAGE,
                null,
                roomNames,
                roomNames[0]
        );

        if (roomName == null) return; // Cancelled

        // Get the selected room
        Room selectedRoom = null;
        for (Room room : kitchenRooms) {
            if (room.getName().equals(roomName)) {
                selectedRoom = room;
                break;
            }
        }

                String[] walls = {"North", "South", "East", "West"};
                String selectedWall = (String) JOptionPane.showInputDialog(
                        null,
                        "Select wall to add the basin:",
                        "Select Wall",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        walls,
                        walls[0]
                );

                if (selectedWall == null) return;

                String[] alignments = selectedWall.equals("North") || selectedWall.equals("South") ?
                        new String[]{"Left", "Center", "Right"} :
                        new String[]{"Top", "Center", "Bottom"};

                String selectedAlignment = (String) JOptionPane.showInputDialog(
                        null,
                        "Select alignment of the basin on the wall:",
                        "Basin Alignment",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        alignments,
                        alignments[1]
                );

                if (selectedAlignment == null) return;

                // Create the basin and set its position
                KitchenSink ks = new KitchenSink(0, 0);  // Position at (0, 0)
                ks.setPosition(selectedWall, selectedAlignment, selectedRoom.getBounds().x, selectedRoom.getBounds().y,
                        selectedRoom.getBounds().width, selectedRoom.getBounds().height);

                selectedRoom.addFixture(ks);  // Add the basin to the room
                floor.repaint();  // Repaint the floor to show the new basin
            }
        });

        JButton addStoveButton = new JButton("Add Stove");
        controlPanel.add(addStoveButton);
        add(controlPanel, BorderLayout.WEST);

        addStoveButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Filter rooms to only get Kitchen-type rooms
        ArrayList<Room> kitchenRooms = new ArrayList<>();
        for (Room room : floor.getRooms()) {
            if ("Kitchen".equals(room.getRoomType())) {
                kitchenRooms.add(room);
            }
        }

        // If no Kitchen rooms are available, show an error message
        if (kitchenRooms.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Kitchen rooms available to add a stove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create an array of Kitchen room names for the dialog
        String[] roomNames = new String[kitchenRooms.size()];
        for (int i = 0; i < kitchenRooms.size(); i++) {
            roomNames[i] = kitchenRooms.get(i).getName();
        }

        // Prompt user to select a Kitchen room
        String roomName = (String) JOptionPane.showInputDialog(
                null,
                "Select a Kitchen room to add the stove:",
                "Select Room",
                JOptionPane.QUESTION_MESSAGE,
                null,
                roomNames,
                roomNames[0]
        );

        if (roomName == null) return; // Cancelled

        // Get the selected room
        Room selectedRoom = null;
        for (Room room : kitchenRooms) {
            if (room.getName().equals(roomName)) {
                selectedRoom = room;
                break;
            }
        }

        // Prompt user to select a wall to add the stove
        String[] walls = {"North", "South", "East", "West"};
        String selectedWall = (String) JOptionPane.showInputDialog(
                null,
                "Select wall to add the stove:",
                "Select Wall",
                JOptionPane.QUESTION_MESSAGE,
                null,
                walls,
                walls[0]
        );

        if (selectedWall == null) return;

        String[] alignments = {"Left", "Center", "Right"};
        if ("East".equals(selectedWall) || "West".equals(selectedWall)) {
            alignments = new String[]{"Top", "Center", "Bottom"};
        }
        String selectedAlignment = (String) JOptionPane.showInputDialog(
                null,
                "Select alignment of the stove on the wall:",
                "Stove Alignment",
                JOptionPane.QUESTION_MESSAGE,
                null,
                alignments,
                alignments[1]
        );

        if (selectedAlignment == null) return;

        // Stove size
        int stoveWidth = 60;
        int stoveHeight = 30;

        // Calculate position based on wall and alignment
        int stoveX = selectedRoom.getBounds().x; // Default position
        int stoveY = selectedRoom.getBounds().y;

        switch (selectedWall) {
            case "North":
                stoveY = selectedRoom.getBounds().y; // Align to the top wall
                if ("Left".equals(selectedAlignment)) {
                    stoveX = selectedRoom.getBounds().x;
                } else if ("Center".equals(selectedAlignment)) {
                    stoveX = selectedRoom.getBounds().x + (selectedRoom.getBounds().width - stoveWidth) / 2;
                } else if ("Right".equals(selectedAlignment)) {
                    stoveX = selectedRoom.getBounds().x + selectedRoom.getBounds().width - stoveWidth;
                }
                break;

            case "South":
                stoveY = selectedRoom.getBounds().y + selectedRoom.getBounds().height - stoveHeight; // Align to bottom wall
                if ("Left".equals(selectedAlignment)) {
                    stoveX = selectedRoom.getBounds().x;
                } else if ("Center".equals(selectedAlignment)) {
                    stoveX = selectedRoom.getBounds().x + (selectedRoom.getBounds().width - stoveWidth) / 2;
                } else if ("Right".equals(selectedAlignment)) {
                    stoveX = selectedRoom.getBounds().x + selectedRoom.getBounds().width - stoveWidth;
                }
                break;

            case "East":
                stoveX = selectedRoom.getBounds().x + selectedRoom.getBounds().width - stoveWidth; // Align to right wall
                if ("Top".equals(selectedAlignment)) {
                    stoveY = selectedRoom.getBounds().y;
                } else if ("Center".equals(selectedAlignment)) {
                    stoveY = selectedRoom.getBounds().y + (selectedRoom.getBounds().height - stoveHeight) / 2;
                } else if ("Bottom".equals(selectedAlignment)) {
                    stoveY = selectedRoom.getBounds().y + selectedRoom.getBounds().height - stoveHeight;
                }
                break;

            case "West":
                stoveX = selectedRoom.getBounds().x; // Align to left wall
                if ("Top".equals(selectedAlignment)) {
                    stoveY = selectedRoom.getBounds().y;
                } else if ("Center".equals(selectedAlignment)) {
                    stoveY = selectedRoom.getBounds().y + (selectedRoom.getBounds().height - stoveHeight) / 2;
                } else if ("Bottom".equals(selectedAlignment)) {
                    stoveY = selectedRoom.getBounds().y + selectedRoom.getBounds().height - stoveHeight;
                }
                break;
        }

        // Create and add stove to the room
        Stove stove = new Stove(stoveX, stoveY);
        selectedRoom.addFixture(stove);
        floor.repaint();
    }
});


        
        addBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bed bed = new Bed();
                bed.setPosition(50, 50); // Default initial position
                floor.addFurniture(bed); // Add the bed to the floor
            }
        });

        //from here save thingy
        JButton saveButton = new JButton("Save Plan");
        JButton loadButton = new JButton("Load Plan");
        controlPanel.add(saveButton);
        add(controlPanel, BorderLayout.WEST);
        controlPanel.add(loadButton);
        add(controlPanel, BorderLayout.WEST);
        
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePlan();
            }
        });

		loadButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        loadPlan();
		    }
		});
		
		

		//to here
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoom();
            }
        });
        
        addButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (floor.getRooms().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No rooms available to add a door.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                String[] roomNames = floor.getRoomNames();
                String roomName = (String) JOptionPane.showInputDialog(
                        null,
                        "Select a room to add the door:",
                        "Select Room",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        roomNames,
                        roomNames[0]
                );
        
                if (roomName == null) return; // Cancelled
        
                Room selectedRoom = floor.getRoomByName(roomName);
        
                String[] walls = {"North", "South", "East", "West"};
                String selectedWall = (String) JOptionPane.showInputDialog(
                        null,
                        "Select wall to add the door:",
                        "Select Wall",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        walls,
                        walls[0]
                );
        
                if (selectedWall == null) return;
        
                // Determine alignments based on the selected wall
                String[] alignments;
                if (selectedWall.equals("East") || selectedWall.equals("West")) {
                    alignments = new String[]{"Top", "Center", "Bottom"};
                } else {
                    alignments = new String[]{"Left", "Center", "Right"};
                }
        
                String selectedAlignment = (String) JOptionPane.showInputDialog(
                        null,
                        "Select alignment of the door on the wall:",
                        "Door Alignment",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        alignments,
                        alignments[1] // Default to "Center"
                );
        
                if (selectedAlignment == null) return;
        
                // Validate door placement
                if ((selectedRoom.getRoomType().equals("Bedroom") || selectedRoom.getRoomType().equals("Bathroom"))
                        && !floor.hasAdjacentRoom(selectedRoom, selectedWall)) {
                    JOptionPane.showMessageDialog(null, "Bedrooms and bathrooms cannot have doors to the outside.", "Invalid Door Placement", JOptionPane.WARNING_MESSAGE);
                    return;
                }
        
                selectedRoom.addDoor(selectedWall, selectedAlignment);
                floor.repaint();
            }
        });
        
//// windows::
addButton2.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (floor.getRooms().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No rooms available to add a window.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Step 1: Select a room
        String[] roomNames = floor.getRoomNames();
        String roomName = (String) JOptionPane.showInputDialog(
                null,
                "Select a room to add the window:",
                "Select Room",
                JOptionPane.QUESTION_MESSAGE,
                null,
                roomNames,
                roomNames[0]
        );

        if (roomName == null) return; // Cancelled

        Room selectedRoom = floor.getRoomByName(roomName);

        // Step 2: Select a wall
        String[] walls = {"North", "South", "East", "West"};
        String selectedWall = (String) JOptionPane.showInputDialog(
                null,
                "Select wall to add the window:",
                "Select Wall",
                JOptionPane.QUESTION_MESSAGE,
                null,
                walls,
                walls[0]
        );

        if (selectedWall == null) return;

        // Step 3: Adjust alignment options based on the wall
        String[] alignments;
        if ("East".equals(selectedWall) || "West".equals(selectedWall)) {
            alignments = new String[]{"Top", "Center", "Bottom"};
        } else {
            alignments = new String[]{"Left", "Center", "Right"};
        }

        String selectedAlignment = (String) JOptionPane.showInputDialog(
                null,
                "Select alignment of the window on the wall:",
                "Window Alignment",
                JOptionPane.QUESTION_MESSAGE,
                null,
                alignments,
                alignments[1]
        );

        if (selectedAlignment == null) return;

        // Step 4: Validate placement
        if (!floor.isValidWindowPlacement(selectedRoom, selectedWall)) {
            JOptionPane.showMessageDialog(null, "Invalid window placement: Windows cannot overlap adjacent rooms.", "Placement Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Step 5: Add the window
        selectedRoom.addWindow(selectedWall, selectedAlignment);
        floor.repaint();
    }
});

        
// windows action listener ends!!
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
//        setSize(1024, 768);
		setSize(screenSize.width, screenSize.height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
       setResizable(true);
        setVisible(true);
    }
 // Save floor plan to file
    public void savePlan() {
        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showSaveDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
               out.writeObject(floor);  // Save the entire Floor object
               JOptionPane.showMessageDialog(this, "Floor plan saved successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving floor plan: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    	
    	

    }

    // Method to load the floor plan
    public void loadPlan() {
    	JFileChooser fileChooser = new JFileChooser();
    int choice = fileChooser.showOpenDialog(this);
    if (choice == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            floor = (Floor) in.readObject();
            getContentPane().removeAll();          // Clear previous content
            add(floor, BorderLayout.CENTER);       // Re-add the loaded floor
            validate();                            // Revalidate to refresh UI
            floor.repaint();
            JOptionPane.showMessageDialog(this, "Floor plan loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading floor plan: " + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();  // For debugging
        }
    }
    	
    } 
    
    
    //to here
    

    private void addRoom() {
        String name = JOptionPane.showInputDialog("Enter room name:");
        if (name == null || name.trim().isEmpty()) {
            return; // Cancelled or empty name
        }

        // Dropdown for room type
        String[] roomTypes = {"Living Room", "Bedroom", "Bathroom", "Dining Hall","Kitchen"};
        String roomType = (String) JOptionPane.showInputDialog(
                null,
                "Select Room Type:",
                "Room Type",
                JOptionPane.QUESTION_MESSAGE,
                null,
                roomTypes,
                roomTypes[0]
        );
        if (roomType == null) {
            return; // Cancelled
        }

        // If there are existing rooms, ask the user to choose a room to place relative to
        Room referenceRoom = null;
        if (!floor.getRooms().isEmpty()) {
            String[] roomNames = floor.getRoomNames(); // Get names of existing rooms
            String referenceRoomName = (String) JOptionPane.showInputDialog(
                    null,
                    "Select a room to place relative to:",
                    "Reference Room",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    roomNames,
                    roomNames[0]
            );
            referenceRoom = floor.getRoomByName(referenceRoomName);
        }

        // Direction options
        String[] directions = {"North", "South", "East", "West"};
        String direction = (String) JOptionPane.showInputDialog(
                null,
                "Select direction:",
                "Direction",
                JOptionPane.QUESTION_MESSAGE,
                null,
                directions,
                directions[0]
        );

        // Alignment options
        String[] alignments = {"Align Left", "Align Right", "Align Top", "Align Bottom"};
        String alignment = (String) JOptionPane.showInputDialog(
                null,
                "Select alignment:",
                "Alignment",
                JOptionPane.QUESTION_MESSAGE,
                null,
                alignments,
                alignments[0]
        );

        String widthStr = JOptionPane.showInputDialog("Enter width:");
        String heightStr = JOptionPane.showInputDialog("Enter height:");

        try {
            int width = Integer.parseInt(widthStr);
            int height = Integer.parseInt(heightStr);

            // Calculate new position based on direction and alignment relative to the reference room
            Point newRoomPosition;
            if (referenceRoom != null) {
                newRoomPosition = floor.calculatePositionRelative(referenceRoom, direction, alignment, width, height);
            } else {
                // If no reference room, place at a default position
                newRoomPosition = new Point(0, 0); // Default starting position
            }

            // Create and add the room
            Room room = new Room(name, roomType, newRoomPosition.x, newRoomPosition.y, width, height);
            if (floor.addRoom(room)) {
                floor.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Error: Rooms cannot overlap!", "Overlap Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter numeric values for coordinates and dimensions.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
       

    }

    public static void main(String[] args) {
        new FloorPlanner();
    }
}