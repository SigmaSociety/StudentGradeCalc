import java.io.*;
import java.util.Scanner;

public class StudentGradeCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean addMoreCourses = true;

        while (addMoreCourses) {
            System.out.println("Enter the course name:");
            String courseName = scanner.nextLine();

            System.out.println("Enter the maximum points for the course:");
            double maxPoints = scanner.nextDouble();

            System.out.println("Enter the points you got:");
            double pointsGot = scanner.nextDouble();

            double gradePercentage = (pointsGot / maxPoints) * 100;
            System.out.printf("Your grade for %s is: %.1f%%\n", courseName, gradePercentage);

            saveGradeToFile(courseName, gradePercentage);

            System.out.println("Do you want to add more courses? (yes/no)");
            String choice = scanner.next();
            addMoreCourses = choice.equalsIgnoreCase("yes");
            scanner.nextLine();
        }

        System.out.println("Do you want to list your grades? (yes/no)");
        String printChoice = scanner.next();
        if (printChoice.equalsIgnoreCase("yes")) {
            readGradeFile();
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private static void saveGradeToFile(String courseName, double gradePercentage) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Grade.txt", true))) {
            writer.printf("%s: %.1f%%\n", courseName, gradePercentage);
        } catch (IOException e) {
            System.err.println("Error saving grade to file: " + e.getMessage());
        }
    }

    private static void readGradeFile() {
        try (Scanner fileScanner = new Scanner(new File("Grade.txt"))) {
            System.out.println("\nGrades from Grade.txt:");
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Grade.txt file not found.");
        }
    }
}
