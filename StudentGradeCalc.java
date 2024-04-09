import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean calculateAnotherGrade = true;

        while (calculateAnotherGrade) {
            try {
                // Prompt the user to enter course details
                System.out.print("Enter the course name: ");
                String courseName = scanner.nextLine();

                System.out.print("Enter the maximum points for the course: ");
                double maxPoints = scanner.nextDouble();

                System.out.print("Enter the points obtained: ");
                double pointsObtained = scanner.nextDouble();

                // Validate inputs
                if (maxPoints <= 0 || pointsObtained < 0) {
                    System.out.println("Please enter valid positive numbers.");
                    continue; // Restart the loop to prompt again
                }

                // Calculate the grade and clamp it to a maximum of 100%
                double grade = Math.min((pointsObtained / maxPoints) * 100, 100);

                // Print the grade
                System.out.println("The grade for " + courseName + " is: " + grade);

                // Ask if the user wants to calculate another grade
                boolean validResponse = false;
                while (!validResponse) {
                    System.out.print("Do you want to calculate another grade? (yes/no): ");
                    String response = scanner.next();
                    if (response.equalsIgnoreCase("yes")) {
                        calculateAnotherGrade = true;
                        validResponse = true;
                    } else if (response.equalsIgnoreCase("no")) {
                        calculateAnotherGrade = false;
                        validResponse = true;
                    } else {
                        System.out.println("Please enter 'yes' or 'no'.");
                    }
                }

                // Consume the newline character after reading the response
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        // Close the scanner
        scanner.close();
    }
}

