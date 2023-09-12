import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseManagement dbManagement = new DatabaseManagement("database");
        Scanner scanner = new Scanner(System.in);

        int command;
        while (true) {
            printMenu();
            try {
                command = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine();
                continue;
            }

            switch (command) {
                case 0:
                    exitProgram(dbManagement);
                    break;
                case 1:
                    dbManagement.addOrUpdatePerson();
                    break;
                case 2:
                    dbManagement.displayPersonList();
                    break;
                case 3:
                    dbManagement.deletePerson();
                    break;
                case 4:
                    dbManagement.writeToDatabase();
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    static void printMenu() {
        System.out.println("-------------------------------------");
        System.out.println("Please select an option:");
        System.out.println("0 - Exit");
        System.out.println("1 - Add/Update a person");
        System.out.println("2 - Display the list of persons");
        System.out.println("3 - Delete a person");
        System.out.println("4 - Save data to a file");
        System.out.println("-------------------------------------");
    }

    static void exitProgram(DatabaseManagement dbManagement) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to save changes before exiting? (Y/N): ");
        char saveChoice = Character.toLowerCase(scanner.next().charAt(0));
        if (saveChoice == 'y') {
            dbManagement.writeToDatabase();
        }
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
