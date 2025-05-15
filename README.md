# 🏨 Java Hotel Booking System - GUI Based

This project is a basic **Java Swing-based GUI application** that simulates a hotel booking system. The program prompts the user to input a hotel name and then displays a user interface for booking actions using the Swing library.

---

## 🚀 How It Works

1. On program launch, a dialog box asks the user to enter a hotel name.
2. If the user provides a valid name:
   - A new `Hotel` object is created.
   - The GUI is initialized and displayed through the `BookingSystem.createAndShowGUI()` method.
3. If the input is empty or canceled:
   - An error message is shown.
   - The program exits.

---

## 🧱 Project Structure

### `Main.java`
- Entry point of the application.
- Handles initial input using `JOptionPane`.
- Starts the Swing GUI thread and initializes the `BookingSystem`.

### `Hotel.java`
- (Not shown here, but assumed): Represents the hotel model, including available rooms, name, and other properties.

### `BookingSystem.java`
- (Not shown here, but assumed): Handles GUI creation and booking logic (e.g., buttons, forms, actions).

---

## 🛠 Requirements

- Java Development Kit (JDK 8 or higher)
- A Java IDE or text editor (e.g., IntelliJ IDEA, Eclipse, VS Code)
- No external libraries required – uses built-in `javax.swing`

---

## ▶️ How to Run

1. Clone or download the project.
2. Ensure all `.java` files (`Main.java`, `Hotel.java`, `BookingSystem.java`) are in the same directory or properly structured in packages.
3. Compile and run using your IDE or the terminal:

```bash
javac Main.java Hotel.java BookingSystem.java
java Main
```
##  Notes
The GUI design and full functionality depend on the implementation of Hotel and BookingSystem classes.

Feel free to expand the system to include room selection, booking confirmation, cancellation, and payment simulation.

