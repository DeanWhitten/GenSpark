import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final String[] wordBank = {
            "cat", "dog", "frog", "log", "bog", "lizard", "wizard", "blizzard",
            "humans", "pizza", "donut", "rug", "bathtub", "rock", "pineapple",
            "turtle", "america", "donkey", "swamp", "shrek"
    };

    public static Random randomGen = new Random();
    public static final int maxAttempts = 6;
    private static String secretWord;
    private static char[] secretWordLettersGuessed;
    private static int numAttempts;
    private static ArrayList < String > guessedLetters = new ArrayList < > ();

    // random word from word bank
    private static String wordGenorator() {
        return wordBank[randomGen.nextInt(wordBank.length)];
    }

    // start game
    public static void startGame() {
        numAttempts = 0;
        guessedLetters.clear();
        secretWord = wordGenorator();
        secretWordLettersGuessed = new char[secretWord.length()];

        for (int i = 0; i < secretWordLettersGuessed.length; i++) {
            secretWordLettersGuessed[i] = '_';
        }
    }

    // true if word is found by user
    public static boolean wordFound() {
        return secretWord.contentEquals(new String(secretWordLettersGuessed));
    }

    //  word found after user entered a character
    private static void enter(String c) {
        if (!guessedLetters.contains(c)) {
            if (secretWord.contains(c)) {
                int index = secretWord.indexOf(c);
                while (index >= 0) {
                    secretWordLettersGuessed[index] = c.charAt(0);
                    index = secretWord.indexOf(c, index + 1);
                }
            } else {
                numAttempts++;
            }
            guessedLetters.add(c);
        }
    }

    // state of the word found by the user
    private static String wordFoundContent() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < secretWordLettersGuessed.length; i++) {
            builder.append(secretWordLettersGuessed[i]);
            if (i < secretWordLettersGuessed.length - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    // Play method for our Hangman Game
    public static void play() {
        try (Scanner input = new Scanner(System.in)) {
            while (numAttempts < maxAttempts) {
                System.out.println("\nGuess a letter : ");
                String str = input.next();

                if (str.length() > 1) {
                    str = str.substring(0, 1);
                }

                enter(str);
                hangmanDisplay();
                System.out.println("\n" + wordFoundContent());

                if (wordFound()) {
                    System.out.println("\nYou win!");
                    break;
                } else {
                    System.out.println("\n Attempts remaining : " + (maxAttempts - numAttempts));
                }
            }

            if (numAttempts == maxAttempts) {
                System.out.println("\nYou lose!");
                System.out.println("=> Word to find was : " + secretWord);
            }
        }
    }

    //display hanging man dependent on user's guesses
    private static void hangmanDisplay() {
        System.out.println("+---+");
        switch (numAttempts){
            case 0:
                System.out.println("\n\n\n");
                break;
            case 1:
                System.out.println(" O \n\n\n");
                break;
            case 2:
                System.out.println(" O \n\\\n\n");
                break;
            case 3:
                System.out.println(" O \n\\|\n\n");
                break;
            case 4:
                System.out.println(" O \n\\|/\n\n");
                break;
            case 5:
                System.out.println(" O \n\\|/\n/\n");
                break;
            case 6:
                System.out.println(" O \n\\|/\n/ \\\n");
                break;
        }

        System.out.println("====");
    }

    public static void main(String[] args) {
        System.out.println("HANGMAN");
        startGame();
        play();
    }

}
