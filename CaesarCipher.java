import java.util.Scanner;

public class CaesarCipher {

    /*
     * Run code when executed
     */
    public static void main(String[] args) {
        try {
            // Users input to cipher
            String rawText = input("Text to cipher: ");

            // Rotation count
            int shift = Integer.parseInt(input("Number of rotations: "));

            // Encrypted text
            String cipheredText = cipher(rawText, shift, false);

            // Decrypted text
            String decipheredText = cipher(cipheredText, shift, true);

            // Output encrypted and decrypted text
            System.out.println("Ciphered text: " + cipheredText);
            System.out.println("Deciphered text: " + decipheredText);

        // If failed to parse "key" to Integer, output error message
        } catch(NumberFormatException e) {
            System.err.println("An error has occurred, ensure your input's correct!");
        }
    }

    /**
     * Sends output and then awaits for the user to enter input.
     *
     * @return user input
     */
    public static String input(String output) {
        System.out.println(output);
        return new Scanner(System.in).next();
    }

    /**
     * Encrypts or Decrypts the given text depending on the amount of shifts
     *
     * @return encrypted or decrypted text
     */
    public static String cipher(String text, int shift, boolean reverse) {
        StringBuilder builder = new StringBuilder();

        shift = reverse ? -shift : shift;

        for(int i = 0; i < text.length(); i++) {
            int point = text.codePointAt(i);
            int fixedPoint = point + shift;

            if (Character.isAlphabetic(point)) {
                if(Character.isLowerCase(point)) {
                    point = wrapInt(fixedPoint, 'a', 'z');

                } else {
                    point = wrapInt(fixedPoint, 'A', 'Z');
                }
            }

            builder.append((char) point);
        }
        return builder.toString();
    }

    /**
     * Wraps current value if below minimum or above maximum
     *
     * @return wrapped integer
     */
    private static int wrapInt(int val, int min, int max) {
        int range = max - min + 1;
        return (val - min + range) % range + min;
    }

}