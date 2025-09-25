import java.io.*;
class Student implements Serializable {
    int id;
    String name;
    transient double marks;
    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
}
public class StudentSerialization {
    public static void main(String[] args) {
        String filename = "student.ser";
        Student s1 = new Student(101, "Rahul", 89.5);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(s1);
            System.out.println("Object has been serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Student s2 = (Student) ois.readObject();
            System.out.println("Object has been deserialized");
            System.out.println("ID: " + s2.id);
            System.out.println("Name: " + s2.name);
            System.out.println("Marks: " + s2.marks);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
