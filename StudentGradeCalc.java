import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A simple program for calculating and saving student grades.
 */
public class StudentGradeCalc {

    /**
     * Main method to start the program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String printOr = "true"; // Flag to determine if grades should be printed from file

        System.out.println("Welcome to the Student Grade Calculator!");
        System.out.println("Do you want to calculate grades? (yes/no)");
        String calculateChoice = scanner.next();

        // Calculate grades if the user chooses 'yes'
        if (calculateChoice.equalsIgnoreCase("yes")) {
            calculateGrades(scanner);
        } 
        // Print grades from file if the user chooses 'no'
        else if (calculateChoice.equalsIgnoreCase("no")) {
            System.out.println("Do you want to print grades from Grade.txt? (yes/no)");
            printOr = "false";
            String printChoice = scanner.next();
            if (printChoice.equalsIgnoreCase("yes")) {
                readGradeFile();
            } 
        } 
        // Handle invalid choice
        else {
        	printOr = "false";
            System.out.println("Invalid choice. Exiting program.");
        }
        
        // If the user hasn't printed grades yet and still wants to, ask again
        if (printOr.equals("true")) {
        	System.out.println("Do you want to print grades from Grade.txt? (yes/no)");
        	String printChoice = scanner.next();
        	if (printChoice.equalsIgnoreCase("yes")) {
        		readGradeFile();
        	}
        }

        System.out.println("\nGoodbye!");
        scanner.close();
    }

    /**
     * Method to calculate grades for courses.
     */
    private static void calculateGrades(Scanner scanner) {
        boolean addMoreCourses = true;

        displayInstructions();

        while (addMoreCourses) {
            scanner.nextLine();

            System.out.println("\nEnter the course name:");
            String courseName = scanner.nextLine();

            double maxPoints = 0;
            double pointsGot = 0;
            boolean validInput = false;

            // Prompt user for maximum points until valid input is provided
            while (!validInput) {
                System.out.println("Enter the maximum points for the course:");
                try {
                    maxPoints = scanner.nextDouble();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                    scanner.next();
                }
            }

            validInput = false;

            // Prompt user for points obtained until valid input is provided
            while (!validInput) {
                System.out.println("Enter the points you got:");
                try {
                    pointsGot = scanner.nextDouble();
                    if (pointsGot > maxPoints) {
                        System.out.println("Points obtained cannot be greater than maximum points.");
                        continue;
                    }
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                    scanner.next();
                }
            }

            double gradePercentage = (pointsGot / maxPoints) * 100;
            System.out.printf("Your grade for %s is: %.1f%%\n", courseName, gradePercentage);

            saveGradeToFile(courseName, gradePercentage);

            System.out.println("Do you want to add more courses? (yes/no)");
            String choice = scanner.next();
            addMoreCourses = choice.equalsIgnoreCase("yes");
        }
    }

    /**
     * Display instructions for the user.
     */
    private static void displayInstructions() {
        System.out.println("Please enter the details for each course you want to calculate the grade for.");
        System.out.println("You can choose to add multiple courses.");
        System.out.println("Once you finish adding courses, you can choose to print the grades from Grade.txt file.");
    }

    /**
     * Save grade information to file.
     */
    private static void saveGradeToFile(String courseName, double gradePercentage) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Grade.txt", true))) {
            writer.printf("%s: %.1f%%\n", courseName, gradePercentage);
        } catch (IOException e) {
            System.err.println("Error saving grade to file: " + e.getMessage());
        }
    }

    /**
     * Read and print grades from file.
     */
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
