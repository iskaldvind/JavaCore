import java.util.Random;
import java.util.Scanner;

public class Lesson3 {

    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();
    public static final String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    public static final String startingField = "";
    public static final String hiddenSymbol = "#";
    public static final int fieldSize = 15;

    public static void main(String[] args) {
        final String answer = words[random.nextInt(words.length)];
        final String field = fillField(startingField);
        gameTurn(field, answer);
    }

    public static void gameTurn(String field, String answer) {
        System.out.println("\n\n" + field + "\nType your next guess:");
        final String guess = scanner.nextLine();
        checkGuess(field, guess, answer);
    }

    public static void checkGuess(String field, String guess, String answer) {
        if (guess.equals(answer)) {
            final String openedField = fillField(answer);
            System.out.println("\n\n" + openedField + "\nCongratulations! You Win the game");
            return;
        }
        final String newField = openLetter(startingField, field, guess, answer);
        System.out.println("Nope");
        gameTurn(newField, answer);
    }

    public static String fillField(String field) {
        if (field.length() == fieldSize) return field;
        return fillField(field + hiddenSymbol);
    }

    public static String openLetter(String newField, String field, String guess, String answer) {
        if (newField.length() == field.length()) return newField;
        if (newField.length() == answer.length()) return fillField(newField);
        final char charInAnswer = answer.charAt(newField.length());
        final char nextChar;
        if (newField.length() < guess.length()) {
            final char charInGuess = guess.charAt(newField.length());
            nextChar = charInGuess != charInAnswer ?
                    field.charAt(newField.length()) : answer.charAt(newField.length());

        } else {
            nextChar = field.charAt(newField.length());
        }
        return openLetter(newField + nextChar, field, guess, answer);
    }
}
