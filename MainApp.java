import java.io.*;
import java.util.*;

// Student class
class Student implements Serializable {
    private final int rollNo;
    private final String name;
    private final String course;
    private final double gpa;

    public Student(int rollNo, String name, String course, double gpa) {
        this.rollNo = rollNo;
        this.name = name;
        this.course = course;
        this.gpa = gpa;
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public double getGpa() { return gpa; }

    @Override
    public String toString() {
        return rollNo + " - " + name + " - " + course + " - GPA: " + gpa;
    }
}

// Student Manager class
class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        for (Student st : students) {
            if (st.getRollNo() == s.getRollNo()) {
                System.out.println("❌ RollNo already exists!");
                return;
            }
        }
        students.add(s);
        System.out.println("✅ Student added successfully!");
    }

    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No students found.");
            return;
        }
        for (Student s : students) System.out.println(s);
    }

    public void searchByRollNo(int rollNo) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                System.out.println("🔍 Found: " + s);
                return;
            }
        }
        System.out.println("❌ Student not found!");
    }

    public void searchByName(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                System.out.println("🔍 Found: " + s);
                return;
            }
        }
        System.out.println("❌ Student not found!");
    }

    public void sortByGPA() {
        students.sort((a, b) -> Double.compare(b.getGpa(), a.getGpa()));
        displayAll();
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
            System.out.println("✅ Data saved to file.");
        } catch (IOException e) {
            System.out.println("⚠️ Error saving file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                students = (List<Student>) obj;
                System.out.println("✅ Data loaded from file.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ No previous data found or file error.");
        }
    }
}

// Main Application
public class MainApp {
    private static double calculateGPA(Scanner sc) {
        double total = 0;
        System.out.println("Enter marks for 5 subjects (0-100):");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Subject " + i + ": ");
            total += sc.nextInt();
        }
        return (total / 5) / 10.0; // Convert to GPA (0-10)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        manager.loadFromFile("students.dat");

        while (true) {
            System.out.println("\n=== Student Record Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. Display All");
            System.out.println("3. Search by RollNo");
            System.out.println("4. Search by Name");
            System.out.println("5. Sort by GPA");
            System.out.println("6. Save & Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter RollNo: ");
                    int rollNo = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    double gpa = calculateGPA(sc);
                    manager.addStudent(new Student(rollNo, name, course, gpa));
                }
                case 2 -> manager.displayAll();
                case 3 -> {
                    System.out.print("Enter RollNo to search: ");
                    manager.searchByRollNo(sc.nextInt());
                }
                case 4 -> {
                    System.out.print("Enter Name to search: ");
                    manager.searchByName(sc.nextLine());
                }
                case 5 -> manager.sortByGPA();
                case 6 -> {
                    manager.saveToFile("students.dat");
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }
}
