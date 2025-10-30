import java.util.*;
import java.util.stream.*;
class Employee {
    String name;
    int age;
    double salary;
    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public String toString() {
        return String.format("%s (Age: %d, Salary: %.2f)", name, age, salary);
    }
}
class Student {
    String name;
    double marks;
    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
    public String toString() {
        return name + " (" + marks + "%)";
    }
}
class Product {
    String name;
    double price;
    String category;
    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public String toString() {
        return String.format("%s [₹%.2f, %s]", name, price, category);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Sort Employees using Lambda Expressions");
            System.out.println("2. Filter and Sort Students using Streams");
            System.out.println("3. Process Product Dataset using Stream Operations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    sortEmployees(sc);
                    break;

                case 2:
                    filterStudents(sc);
                    break;

                case 3:
                    processProducts(sc);
                    break;

                case 4:
                    System.out.println("Exiting program... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        } while (choice != 4);
        sc.close();
    }
    // ---------------- PART A: Sorting Employee Objects Using Lambda ----------------
    public static void sortEmployees(Scanner sc) {
        System.out.println("\n=== PART A: Sorting Employees ===");
        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();
        sc.nextLine();

        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1));
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = sc.nextInt();
            System.out.print("Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();
            employees.add(new Employee(name, age, salary));
        }
        System.out.println("\nOriginal Employee List:");
        employees.forEach(System.out::println);

        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("\nSorted by Name:");
        employees.forEach(System.out::println);

        employees.sort(Comparator.comparingInt(e -> e.age));
        System.out.println("\nSorted by Age:");
        employees.forEach(System.out::println);

        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("\nSorted by Salary (Descending):");
        employees.forEach(System.out::println);
    }
    // ---------------- PART B: Filtering and Sorting Students Using Streams ----------------
    public static void filterStudents(Scanner sc) {
        System.out.println("\n=== PART B: Filtering and Sorting Students ===");
        System.out.print("Enter number of students: ");
        int m = sc.nextInt();
        sc.nextLine();

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Marks: ");
            double marks = sc.nextDouble();
            sc.nextLine();
            students.add(new Student(name, marks));
        }

        System.out.println("\nOriginal Student List:");
        students.forEach(System.out::println);

        System.out.println("\nStudents scoring above 75%, sorted by marks:");
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted(Comparator.comparingDouble(s -> s.marks))
                .map(s -> s.name)
                .forEach(System.out::println);
    }

    // ---------------- PART C: Stream Operations on Product Dataset ----------------
    public static void processProducts(Scanner sc) {
        System.out.println("\n=== PART C: Stream Operations on Products ===");
        System.out.print("Enter number of products: ");
        int p = sc.nextInt();
        sc.nextLine();

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            System.out.println("\nEnter details for Product " + (i + 1));
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Price: ");
            double price = sc.nextDouble();
            sc.nextLine();
            System.out.print("Category: ");
            String category = sc.nextLine();
            products.add(new Product(name, price, category));
        }

        System.out.println("\nOriginal Product List:");
        products.forEach(System.out::println);

        Map<String, List<Product>> groupedByCategory =
                products.stream().collect(Collectors.groupingBy(prod -> prod.category));

        System.out.println("\nProducts Grouped by Category:");
        groupedByCategory.forEach((cat, prodList) -> 
            System.out.println(cat + ": " + prodList)
        );

        Map<String, Optional<Product>> maxByCategory =
                products.stream().collect(Collectors.groupingBy(
                        prod -> prod.category,
                        Collectors.maxBy(Comparator.comparingDouble(prod -> prod.price))
                ));

        System.out.println("\nMost Expensive Product in Each Category:");
        maxByCategory.forEach((cat, prod) ->
                System.out.println(cat + " -> " + prod.get())
        );

        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(prod -> prod.price));
        System.out.printf("\nAverage Price of All Products: ₹%.2f\n", avgPrice);
    }
}
