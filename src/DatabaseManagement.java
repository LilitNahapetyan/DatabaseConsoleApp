import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DatabaseManagement {
    private final List<Person> personDatabase;
    private final String filename;

    public DatabaseManagement(String filename) {
        this.filename = filename;
        personDatabase = new ArrayList<>();
        loadFromAFile();
        updateGenerateID();
    }

    private void updateGenerateID() {
        int maxID = 0;
        for (Person person : personDatabase) {
            if (person.getID() > maxID) {
                maxID = person.getID();
            }
        }
        Person.setGenerateID(maxID);
    }


    public void addOrUpdatePerson() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the person's name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("Name cannot be empty. Please enter a valid name.");
            System.out.print("Enter the person's name: ");
            name = scanner.nextLine().trim();
        }

        System.out.print("Enter the person's surname: ");
        String surname = scanner.nextLine().trim();
        while (surname.isEmpty()) {
            System.out.println("Surname cannot be empty. Please enter a valid surname.");
            System.out.print("Enter the person's surname: ");
            surname = scanner.nextLine().trim();
        }

        int age;

        while (true) {
            try {
                System.out.print("Enter the person's age: ");
                age = scanner.nextInt();
                if (age < 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid age. Please enter a positive integer.");
                scanner.nextLine();
            }
        }

        System.out.print("Enter the person's date of birth (yyyy-MM-dd): ");
        String date;
        while (true) {
            scanner.nextLine();
            date = scanner.nextLine();

            if (isValidDate(date)) {
                break;
            } else {
                System.out.print("Invalid date format. Please use yyyy-MM-dd: ");
            }
        }

        boolean personExists = false;
        for (Person p : personDatabase) {
            if (Objects.equals(p.getName(), name) && Objects.equals(p.getSurname(), surname)) {
                personExists = true;
                System.out.println("This person already exists in the database.");
                System.out.println("1 - Add as a new person");
                System.out.println("2 - Update the existing person's data");
                int choice = scanner.nextInt();
                if (choice == 2) {
                    p.setAge(age);
                    p.setDate(date);
                    System.out.println("Person's data updated successfully!");
                }
                break;
            }
        }

        if (!personExists) {
            Person person = new Person(name, surname, age, date);
            personDatabase.add(person);
            System.out.println("Person added successfully!");
        }
    }


    public void deletePerson() {
        System.out.println("Current List of Persons:");
        displayPersonList();

        Scanner scanner = new Scanner(System.in);

        int idToDelete = -1;

        while (true) {
            System.out.print("Enter the ID of the person you want to delete: ");

            if (scanner.hasNextInt()) {
                idToDelete = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for the ID.");
                scanner.nextLine();
            }
        }

        boolean found = false;

        for (Person person : personDatabase) {
            if (person.getID() == idToDelete) {
                personDatabase.remove(person);
                System.out.println("Person with ID " + idToDelete + " has been deleted successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Person with ID " + idToDelete + " not found in the database.");
        }
    }

    public void writeToDatabase() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOutputStream.writeInt(personDatabase.size());
            for (Person p : personDatabase) {
                objectOutputStream.writeObject(p);
            }
            System.out.println("Data saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving data to the file: " + e.getMessage());
        }
    }

    public void loadFromAFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {

            int size = objectInputStream.readInt();
            for (int i = 0; i < size; i++) {
                Person person = (Person) objectInputStream.readObject();
                personDatabase.add(person);
            }
            System.out.println("Data loaded successfully from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data from the file: " + e.getMessage());
        }
    }

    public void displayPersonList() {
        for (Person p : personDatabase) {
            System.out.println(p);
        }
    }

    public boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
