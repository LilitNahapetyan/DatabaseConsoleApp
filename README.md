# Person Database Console Application (Java)

## Summary

The Person Database Console Application is a Java-based console program that allows users to manage a list of persons with attributes like Name, Surname, Age, ID (unique identifier), and Date of Birth. It provides data persistence, user-friendly interactions, and error handling to ensure a seamless experience.

## Features

### Data Persistence

The program ensures that data remains intact after shutting down by saving and loading the list of persons from a file.

### User Interface

- **Add a New Person**: Easily add persons with name, surname, age, and date of birth.
- **Display List of Persons**: View a list of stored persons, including their details.
- **Search for a Person**: Find persons by name and surname, with options to add as new or update.
- **Save Data to File**: Store the current list of persons for future use.
- **Load Data from File**: Retrieve the list of persons from the file when starting the program.

### Error Handling

The program gracefully handles exceptions, such as file I/O errors and invalid user input, with informative error messages.

## Usage

To use the Person Database Console Application:

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/person-database.git
