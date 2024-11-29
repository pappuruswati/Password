import java.util.*;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the password length from the user
        System.out.print("Enter the desired password length: ");
        int length = scanner.nextInt();

        // Get user input for complexity (types of characters to include)
        System.out.print("Include uppercase letters? (y/n): ");
        boolean includeUppercase = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include lowercase letters? (y/n): ");
        boolean includeLowercase = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include numbers? (y/n): ");
        boolean includeNumbers = scanner.next().equalsIgnoreCase("y");

        System.out.print("Include special characters? (y/n): ");
        boolean includeSpecial = scanner.next().equalsIgnoreCase("y");

        // Generate the random password
        String password = generatePassword(length, includeUppercase, includeLowercase, includeNumbers, includeSpecial);

        // Output the generated password
        System.out.println("Generated Password: " + password);
    }

    private static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase,
                                           boolean includeNumbers, boolean includeSpecial) {
        StringBuilder password = new StringBuilder();

        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()-_=+<>?";

        StringBuilder allowedCharacters = new StringBuilder();

        // Add the allowed characters to the pool
        if (includeUppercase) {
            allowedCharacters.append(upperCaseLetters);
        }
        if (includeLowercase) {
            allowedCharacters.append(lowerCaseLetters);
        }
        if (includeNumbers) {
            allowedCharacters.append(digits);
        }
        if (includeSpecial) {
            allowedCharacters.append(specialCharacters);
        }

        // If no character types are selected, prompt user to include at least one type
        if (allowedCharacters.length() == 0) {
            throw new IllegalArgumentException("At least one character type (uppercase, lowercase, numbers, special) must be included.");
        }

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // Pick a random character from the allowed pool
            int index = random.nextInt(allowedCharacters.length());
            password.append(allowedCharacters.charAt(index));
        }

        return password.toString();
    }
}
