import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String surname;
    private int age;
    private int ID;
    private String date;
    private static int generateID = 0;

    public Person(String name, String surname, int age, String date) {
        generateID++;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.ID = generateID;
        this.date = date;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public static int getGenerateID() {
        return generateID;
    }

    public static void setGenerateID(int ID) {
        generateID = ID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ID: ").append(ID).append(", Name: ").append(name).append(", Surname: ").append(surname).append(", Age: ").append(age).append(", Date: ").append(date);
        return stringBuilder.toString();
    }

}
