import java.util.InputMismatchException;
import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean validInput = false;
        int userChoice = 0;

        // Display menu options
        System.out.println("Welcome To The Temperature Converter!\n");
        System.out.println("Kindly select an option:");
        System.out.println("1. Convert Celsius to Fahrenheit");
        System.out.println("2. Convert Fahrenheit to Celsius\n");

        // Input validation for menu userChoice
        while (!validInput) {
            try {
                userChoice = scan.nextInt();
                if (userChoice == 1 || userChoice == 2) {
                    validInput = true; // Valid userChoice entered
                } else {
                    System.out.println("Invalid userChoice. Please select 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scan.next(); // Clear the invalid input
            }
        }

        // convert according to the  user userChoice
        if (userChoice == 1) {
            convertCelsiusToFahrenheit(scan);
        } else {
            convertFahrenheitToCelsius(scan);
        }

        scan.close(); // Close the scan
    }

    /**
     * Converts from Celsius to Fahrenheit.
     * Prompt user for temperature in Celsius and display the result.
     */
    private static void convertCelsiusToFahrenheit(Scanner scan) {
        double celsius = getTemperatureInput(scan, "Celsius");

        // Perform conversion
        double fahrenheit = (celsius * 9 / 5) + 32;

        // Display the result
        System.out.printf("%.2f Celsius is equal to %.2f Fahrenheit.%n", celsius, fahrenheit);
    }

    /**
     * Converting from Fahrenheit to Celsius.
     * Prompts the user for temperature in Fahrenheit and displays the result.
     */
    private static void convertFahrenheitToCelsius(Scanner scan) {
        double fahrenheit = getTemperatureInput(scan, "Fahrenheit");

        //conversion
        double celsius = (fahrenheit - 32) * 5 / 9;

        // Display result
        System.out.printf("%.2f Fahrenheit is equal to %.2f Celsius.%n", fahrenheit, celsius);
    }

    private static double getTemperatureInput(Scanner scan, String scale) {
        double temperature = Double.NaN; // Initialize temperature

        while (Double.isNaN(temperature)) { // Loop until valid temperature is entered
            System.out.printf("Enter temperature in %s: ", scale);
            try {
                temperature = scan.nextDouble();

                // Check for extreme temperatures (optional)
                if ((scale.equals("Celsius") && (temperature < -273.15)) ||
                        (scale.equals("Fahrenheit") && (temperature < -459.67))) {
                    System.out.printf("Invalid input: Temperature cannot be below absolute zero (%s).%n", scale);
                    temperature = Double.NaN; // Reset to NaN to enter again
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please make sure your input is accurate and enter a valid number.");
                scan.next(); // Clear the invalid input
            }
        }

        return temperature; // Return validated temperature
    }
}