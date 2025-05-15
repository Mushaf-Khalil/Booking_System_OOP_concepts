import java.util.NoSuchElementException;
import java.util.Random;

public class Hotel {
    public String name;
    private Room[] rooms;
    private Reservation[] reservations;
    private int numOfReservations;

    // Constructor with only the hotel name
    public Hotel(String name) {
        this.name = name;
        Random random = new Random();
        int numRooms = random.nextInt(49) + 2; // Random number of rooms between 1 and 50
        this.rooms = new Room[numRooms];
        this.reservations = new Reservation[numRooms];
        this.numOfReservations = 0;

        // Generate random rooms based on available types
        String[] roomTypes = {"basic", "double", "luxury"};
        for (int i = 0; i < numRooms; i++) {
            String type = roomTypes[random.nextInt(roomTypes.length)];
            this.rooms[i] = new Room(type);  // Creating room with random type and setting price based on type
        }
    }

    // Constructor with hotel name and room array (for custom room initialization)
    public Hotel(String name, Room[] rooms) {
        this.name = name;
        this.rooms = new Room[rooms.length];
        System.arraycopy(rooms, 0, this.rooms, 0, rooms.length);
        this.reservations = new Reservation[rooms.length];
        this.numOfReservations = 0;
    }

    // Method to find an available room based on room type
    public Room findAvailableRoom(String roomType) {
        for (Room room : rooms) {
            if (room != null && room.getType().equalsIgnoreCase(roomType) && room.getAvailability()) {
                return room; // Return the first available room of the specified type
            }
        }
        return null; // No available room of the specified type
    }

    private void addReservation(Reservation reservation) {
        if (numOfReservations < reservations.length) {
            reservations[numOfReservations] = reservation;
            numOfReservations++;
        } else {
            System.out.println("Sorry, all rooms are already reserved 👀");
        }
    }

    private void removeReservation(String name, String type) {
        for (int i = 0; i < numOfReservations; i++) {
            if (reservations[i].getName().equalsIgnoreCase(name) &&
                    reservations[i].getRoom().getType().equalsIgnoreCase(type)) {
                reservations[i].getRoom().changeAvailability();
                for (int j = i + 1; j < numOfReservations; j++) {
                    reservations[j - 1] = reservations[j];
                }
                numOfReservations--;
                return;
            }
        }
        throw new NoSuchElementException("There is no reservation for a " + type +
                " room under the name of " + name + ".");
    }

    public boolean hasReservationForName(String name) {
        for (int i = 0; i < numOfReservations; i++) {
            if (reservations[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void createReservation(String name, String type) {
        Room room = findAvailableRoom(type);  // Use the new findAvailableRoom method here
        if (room == null) {
            System.out.println("Sorry " + name + ", we have no available rooms of the desired type.");
        } else {
            if (numOfReservations < reservations.length) {
                room.changeAvailability();
                addReservation(new Reservation(room, name));
                System.out.println("You have successfully reserved a " + type +
                        " room under the name " + name + ". We look forward to having you at " + this.name);
            } else {
                System.out.println("There is an issue with the reservation system");
            }
        }
    }

    public void cancelReservation(String name, String type) {
        try {
            removeReservation(name, type);
            System.out.println(name + ", your reservation for a " + type + " room has been successfully cancelled.");
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String InvoiceMessage(String name) {
        double total = 0;
        String roomType = "";

        for (int i = 0; i < numOfReservations; i++) {
            if (reservations[i].getName().equalsIgnoreCase(name)) {
                total += reservations[i].getRoom().getPrice();
                roomType = reservations[i].getRoom().getType();
            }
        }

        String invoiceMessage = "Name of customer: " + name + "\n";
        invoiceMessage += "Total price: $" + String.format("%.1f", total) + "\n";
        invoiceMessage += "Room booked type: " + roomType;
        return invoiceMessage;
    }

    @Override
    public String toString() {
        int b = 0, d = 0, l = 0;

        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null && rooms[i].getAvailability()) {
                if (rooms[i].getType().equalsIgnoreCase("basic"))
                    b++;
                else if (rooms[i].getType().equalsIgnoreCase("double"))
                    d++;
                else if (rooms[i].getType().equalsIgnoreCase("luxury"))
                    l++;
            }
        }

        String res = "Hotel name: " + name + "\n";
        res += "Available Rooms: " + b + " Basic, " + d + " Double , " + l + " Luxury";
        return res;
    }
}
