public class Room {
    public String type;
    private double price;
    private boolean availability;

    public Room(String type) {
        // verify the type of room to set the corresponding price
        type = type.toLowerCase();
        if (type.equals("basic")) {
            price = 90;
        } else if (type.equals("double")) {
            price = 110;
        } else if (type.equals("luxury")) {
            price = 150;
        } else {
            throw new IllegalArgumentException("No room of type '" + type + "' can be created");
        }
        this.type = type;
        availability = true;
    }

    public Room() {
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void changeAvailability() {
        availability = !availability;
    }
public static Room findAvailableRoom(Room[] rooms, String type) {
        for (Room room : rooms) {
            if (room.getType().equalsIgnoreCase(type) && room.getAvailability()) {
                return room;
            }
        }
        return null;
    }
}
class Reservation extends Room{
    private String name;
    private Room roomReserved;

    public Reservation (Room room, String name) {
        this.name = name;
        this .roomReserved = room;
    }
    public String getName() {
        return name;
    }
    public Room getRoom() {
        return roomReserved;
    }
}