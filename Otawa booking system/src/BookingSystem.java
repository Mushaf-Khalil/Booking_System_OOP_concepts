import javax.swing.*;
import java.awt.*;
import java.util.NoSuchElementException;
import java.util.Random;

public class BookingSystem {
        private static String[] typeOfRooms = {"Basic", "Double", "Luxury"};
        private static Random r = new Random(123);
        public static Hotel hotel;

        public static String getRandomType() {
                int index = r.nextInt(typeOfRooms.length);
                return typeOfRooms[index];
        }

        public static int getRandomNumberOfRooms() {
                return r.nextInt(50) + 1;
        }

        public static Room[] createRooms(int numOfRooms) {
                Room[] rooms = new Room[numOfRooms];
                for (int i = 0; i < numOfRooms; i++) {
                        rooms[i] = new Room(getRandomType());
                }
                return rooms;
        }
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Ottawa Booking System");

//       ading pic here
        frame.setIconImage(new ImageIcon("C:\\Users\\Musaa\\Desktop\\Black And White Modern Vintage Retro Brand Logo.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.black);
//        adding my label in north to make it show able in upper space of jframe
        mainPanel.add(createTitlePanel(), BorderLayout.NORTH);
//        adeding my buutons in mid area so they will be handy to use
        mainPanel.add(createButtonsPanel(), BorderLayout.CENTER);
//        adding exit button in south ehich is at the end so  it wil be in middle
        mainPanel.add(createExitButtonPanel(), BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);
//        seettiing size ..
        frame.setSize(600,350);
        frame.setLocationRelativeTo(null); // Centers the frame on the screen
        frame.setVisible(true);

    }
    private static JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//titlePanel.setBackground(new Color(91, 74, 74));
        JLabel titleLabel = new JLabel("Welcome to Ottawa Booking System");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        titlePanel.add(titleLabel);

        return titlePanel;
    }


        private static JButton createStyledButton(String text) {
                JButton button = new JButton(text);
                button.setFont(new Font("Google Sans", Font.BOLD, 20));
                button.setBackground(new Color(10, 62, 105)); // Set your desired button background color(here is  scramento )
                button.setForeground(Color.WHITE);
                button.setFocusPainted(true);
                return button;
        }


        @SuppressWarnings("unused")
        private static JPanel createButtonsPanel() {
//            using grid layout to place the  buttons in  grid order
            JPanel buttonsPanel = new JPanel(new GridLayout(2, 4, 10, 10));
//creating buttons by using styled button methood here
//            buttonsPanel.setBackground( new Color(44, 46, 49));
 JButton makeReservationButton = createStyledButton("Make Your Reservation");
 JButton cancelReservationButton = createStyledButton("Cancel Your Reservation");
 JButton seeInvoiceButton = createStyledButton("See Your  Invoice");
 JButton seeHotelInfoButton = createStyledButton("See hotel info");
//addinh here the buttons  in the button panel
                buttonsPanel.add(makeReservationButton);
                buttonsPanel.add(cancelReservationButton);
                buttonsPanel.add(seeInvoiceButton);
                buttonsPanel.add(seeHotelInfoButton);
//adding action listners by modern java lambda syntax
                makeReservationButton.addActionListener(e -> makeReservation());
                cancelReservationButton.addActionListener(e -> cancelReservation());
                seeInvoiceButton.addActionListener(e -> seeInvoice());
                seeHotelInfoButton.addActionListener(e -> seeHotelInfo());

                return buttonsPanel;
        }
//creating here the exit pannel separtely the creating my exit button s o i dont get confused and palce it at in bottom which is in south
        @SuppressWarnings("unused")
        private static JPanel createExitButtonPanel() {
                JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

                JButton exitButton = createStyledButton("Exit the Booking System");
//            exitPanel.setBackground(new Color(57, 64, 65));
exitButton.setForeground(Color.WHITE);
                exitButton.addActionListener(e -> {
                        JOptionPane.showMessageDialog(null,"Thank you, see you soon...");
                        System.exit(0);
                });

                exitPanel.add(exitButton);
                return exitPanel;
        }
        private static void makeReservation() {

            String clientName = JOptionPane.showInputDialog(null, "Please enter your name:",
                    "Make a Reservation", JOptionPane.QUESTION_MESSAGE);
    //if user dosent enter his her name  then  the last else will execute
            if (clientName != null && !clientName.isEmpty()) {
    
                String[] roomTypes = {"Basic", "Double", "Luxury"};
                String roomType = (String) JOptionPane.showInputDialog(null, "What type of room did you reserve?",
                        "Room Type", JOptionPane.UNDEFINED_CONDITION, null, roomTypes, roomTypes[0]);
    
                // Check if there is an available room of the selected type
                Room availableRoom = hotel.findAvailableRoom(roomType);
    
                if (availableRoom != null) {
                    // Room is available, proceed with reservation
                    hotel.createReservation(clientName, roomType);
                    ImageIcon TICKIcon = new ImageIcon("H:\\Green_tick.svg.png");
                    Image image1 = TICKIcon.getImage();
                    Image new2img = image1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    TICKIcon = new ImageIcon(new2img);
                    JOptionPane.showMessageDialog(null, "Reservation made successfully!",
                            "Success", JOptionPane.INFORMATION_MESSAGE,TICKIcon);
                } else {
                    // No available rooms of the selected type
                    JOptionPane.showMessageDialog(null, "Sorry, all rooms of type '" + roomType + "' are fully booked.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid name. Please try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
    
        private static void cancelReservation() {
                String clientName = JOptionPane.showInputDialog(null, "Please enter the name you used to make the reservation:",
                        "Cancel Reservation", JOptionPane.INFORMATION_MESSAGE);
    
                if (clientName != null && !clientName.isEmpty()) {
                    // Check if the entered name has an existing reservation
                    boolean hasReservation = hotel.hasReservationForName(clientName);
    
                    if (hasReservation) {
                        String[] roomTypes = {"Basic", "Double", "Luxury"};
                        String roomType = (String) JOptionPane.showInputDialog(null, "What type of room did you reserve?",
                                "Room Type", JOptionPane.QUESTION_MESSAGE, null, roomTypes, roomTypes[0]);
    
                        try {
                            hotel.cancelReservation(clientName, roomType);
                            ImageIcon TICKIcon2 = new ImageIcon("H:\\Green_tick.svg.png");
                            Image image1 = TICKIcon2.getImage();
                            Image new4img = image1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                            TICKIcon2 = new ImageIcon(new4img);
                            JOptionPane.showMessageDialog(null, "Your reservation has been successfully cancelled, " + clientName,
                                    "Success", JOptionPane.INFORMATION_MESSAGE,TICKIcon2);
                        } catch (NoSuchElementException ex) {
                            JOptionPane.showMessageDialog(null, "Error cancelling reservation. Please try again.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No reservation found for the name: " + clientName,
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid name. Please try again.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    
    
            private static void seeInvoice() {
                String clientName = JOptionPane.showInputDialog(null, "Please enter your name:",
                        "See Invoice", JOptionPane.INFORMATION_MESSAGE);
                if (clientName != null && !clientName.isEmpty()) {
                    String invoiceMessage = hotel.InvoiceMessage(clientName);
    //           resizing immage
                    ImageIcon moneyIcon = new ImageIcon("C:\\Users\\Musaa\\Downloads\\download.jpg");
                    Image image = moneyIcon.getImage();
                    Image newimg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    moneyIcon = new ImageIcon(newimg);
    
                    JOptionPane.showMessageDialog(null, invoiceMessage,
                            "Invoice", JOptionPane.INFORMATION_MESSAGE, moneyIcon);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid name. Please try again.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    
            private static void seeHotelInfo() {
                ImageIcon PRIcon = new ImageIcon("H:\\50a42c0a969d3d5a6cc04e12ce1b4dd0.jpg");
                Image image = PRIcon.getImage();
                Image new3img = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                PRIcon = new ImageIcon(new3img);
                JOptionPane.showMessageDialog(null, hotel.toString(), "Hotel Information", JOptionPane.INFORMATION_MESSAGE,PRIcon);
    //        ust used hotel.tostring
            }
        }