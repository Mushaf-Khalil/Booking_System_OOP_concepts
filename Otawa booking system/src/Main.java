import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Get hotel name from user input
            String hotelName = JOptionPane.showInputDialog(null, 
                "Please enter the name of the hotel you'd like to book:",
                "Enter Hotel Name", JOptionPane.QUESTION_MESSAGE);

            if (hotelName != null && !hotelName.isEmpty()) {
                // Proceed with creating and showing the GUI
                createAndShowGUI(hotelName);
            } else {
                // Show an error message if the hotel name is empty
                JOptionPane.showMessageDialog(null, 
                    "Hotel name cannot be empty, Exiting the Booking System........",
                    "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1); // Exit if no hotel name is provided
            }
        });
    }
    public static void createAndShowGUI(String hotelName) {
        // Create a new Hotel object and initialize the BookingSystem's hotel
        BookingSystem.hotel = new Hotel(hotelName);

        // Use SwingUtilities.invokeLater to ensure the GUI is created and shown on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Call the createAndShowGUI method from the BookingSystem to display the GUI
            BookingSystem.createAndShowGUI();
        });
    }
}

