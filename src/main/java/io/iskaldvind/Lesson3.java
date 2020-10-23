package io.iskaldvind;

import java.util.Random;
import java.util.Scanner;

public class Lesson3 {

    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();
    public static final String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    public static final String emptyField = "";
    public static final char hiddenSymbol = '#';
    public static final int fieldSize = 15;

    public static void main(String[] args) {
        final String answer = words[random.nextInt(words.length)];
        final String field = fillField(emptyField);
        gameTurn(field, answer);
    }

    public static void gameTurn(String field, String answer) {
        System.out.println("\n\n" + field + "\nType your next guess:");
        final String guess = scanner.nextLine();
        checkGuess(guess, answer);
    }

    public static void checkGuess(String guess, String answer) {
        final String resultField = openLetters(emptyField, guess, answer);
        if (guess.equals(answer)) {
            System.out.println("\n\n" + resultField + "\nCongratulations! You Win the game");
            return;
        }
        System.out.println("Nope");
        gameTurn(resultField, answer);
    }

    public static String fillField(String field) {
        if (field.length() == fieldSize) return field;
        return fillField(field + hiddenSymbol);
    }

    public static String openLetters(String newField, String guess, String answer) {
        if (newField.length() == answer.length() || newField.length() == guess.length()) return fillField(newField);
        final char charInAnswer = answer.charAt(newField.length());
        final char charInGuess = guess.charAt(newField.length());
        final char nextChar = charInGuess == charInAnswer ? charInAnswer : hiddenSymbol;
        return openLetters(newField + nextChar, guess, answer);
    }
}
