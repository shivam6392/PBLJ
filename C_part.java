import java.io.*;
import java.util.*;
class Employee implements Serializable {
    int id;
    String name;
    double salary;
    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public String toString() {
        return id + " " + name + " " + salary;
    }
}
public class EmployeeManagement {
    static final String file = "employees.dat";

    public static void addEmployee(Employee emp) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true)) {
            protected void writeStreamHeader() throws IOException {}
        }) {
            oos.writeObject(emp);
            System.out.println("Employee added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void viewEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Employee emp = (Employee) ois.readObject();
                System.out.println(emp);
            }
        } catch (EOFException e) {
            return;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    addEmployee(new Employee(id, name, salary));
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
