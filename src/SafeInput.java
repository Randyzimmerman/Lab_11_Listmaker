import java.util.ArrayList;
import java.util.Scanner;

public class SafeInput {
    private static ArrayList<String> itemList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = SafeInput.getRegExString("[AaDdPpQq]", "Invalid choice. Please enter A, D, P, or Q.");

            switch (Character.toLowerCase(choice)) {
                case 'a':
                    addItem();
                    break;
                case 'd':
                    deleteItem();
                    break;
                case 'p':
                    printList();
                    break;
                case 'q':
                    if (confirmQuit()) {
                        System.out.println("Quitting the program. Goodbye!");
                        System.exit(0);
                    }
                    break;
            }
        } while (true);
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("A – Add an item to the list");
        System.out.println("D – Delete an item from the list");
        System.out.println("P – Print the list");
        System.out.println("Q – Quit");
        System.out.println("------------------------------");
        displayList();
    }

    private static void displayList() {
        System.out.println("Current List:");
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println((i + 1) + ". " + itemList.get(i));
            }
        }
    }

    private static void addItem() {
        System.out.print("Enter the item to add: ");
        String item = scanner.nextLine();
        itemList.add(item);
        System.out.println("Item added successfully!");
    }

    private static void deleteItem() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        displayList();
        int itemNumber = SafeInput.getRangedInt(1, itemList.size(), "Enter the item number to delete: ");
        itemList.remove(itemNumber - 1);
        System.out.println("Item deleted successfully!");
    }

    private static void printList() {
        displayList();
    }

    private static boolean confirmQuit() {
        return SafeInput.getYNConfirm("Are you sure you want to quit? (Y/N): ");
    }

    // Add your SafeInput utility methods here

    public static char getRegExString(String regex, String errorMessage) {
        char choice;
        do {
            String input = scanner.nextLine();
            if (input.length() == 1 && input.matches(regex)) {
                choice = input.charAt(0);
                break;
            } else {
                System.out.println(errorMessage);
            }
        } while (true);
        return choice;
    }

    public static int getRangedInt(int min, int max, String prompt) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print(prompt);
                scanner.next();
            }
            value = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
            if (value >= min && value <= max) {
                break;
            } else {
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
            }
        } while (true);
        return value;
    }

    public static boolean getYNConfirm(String prompt) {
        String response;
        do {
            System.out.print(prompt);
            response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("y") || response.equals("yes")) {
                return true;
            } else if (response.equals("n") || response.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        } while (true);
    }
}
