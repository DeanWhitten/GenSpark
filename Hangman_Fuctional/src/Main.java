import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static Scanner userInput = new Scanner(System.in);
    public static boolean stillPlaying = true;
    public static ArrayList<String>
            gameArt = new ArrayList<>(),
            wordBank = new ArrayList<>(),
            scoreHistory = new ArrayList<>();
    public static HashSet<String>
            incorrectLetters = new HashSet<>(),
            correctLetters = new HashSet<>();
    public static String
            userName,
            secretWord,
            guess = "";
    public static int
            score = 0,
            currentArt = 0;

    private static void obtainFiles() {
        try {
            gameArt = (ArrayList<String>) Files.readAllLines(Path.of("src/Resources/art.txt"));
            wordBank = (ArrayList<String>) Files
                    .lines(Path.of("src/Resources/wordBank.txt"))
                    .collect(Collectors.toList());
            scoreHistory = (ArrayList<String>) Files
                    .lines(Path.of("src/Resources/scoreHistory.txt"))
                    .collect(Collectors.toList());
        }
        catch (IOException e) {
            System.out.println("OOPS ... File not found");
        }
    }

    private static void showIntroScreen() {
        System.out.println("""
                **************************************************
                        HANGMAN - Functional EDITION
                **************************************************
                """);
    }

    private static String getUserName() {
        System.out.println("\nEnter your name:");
        userName = userInput.nextLine();
        System.out.println("\nHello " + userName + "!");
        return userName;
    }

    private static String getNewWord() {
        secretWord = wordBank.get((int) (Math.random() * (wordBank.size() - 1)));
        return secretWord;
    }

    private static void updateGameArt() {
        Arrays.stream(gameArt.get(currentArt)
                .split(","))
                .forEach(System.out::println);
    }

    private static void updateWrongGuesses() {
        System.out.print("Guesses so far: \n");
        incorrectLetters.stream().forEach(ch -> System.out.print(ch + " "));
        System.out.println();
    }

    private static void updateWord() {
        Arrays.stream(secretWord.split("")).forEach(ch -> {
            if (correctLetters.contains(ch)) {
                System.out.print(ch);
            }
            else {
                System.out.print("_");
            }
        });
        System.out.println();
    }

    private static String displayScore(){
        return "\nScore: " + score;
    }

    private static String takeGuess() {
        System.out.println("Please make a guess:");
        try {
            guess = String.valueOf(userInput.nextLine().charAt(0));
        }
        catch (Exception e) {
            System.out.println(guess + " isn't a valid input, try again!");
            takeGuess();
        }
        return guess;
    }

    private static boolean checkGuess() {
        if (secretWord.contains(guess)) {
            correctLetters.add(guess);
            score += 25;
            return true;
        }
        else {
            incorrectLetters.add(guess);
            currentArt++;
            return false;
        }
    }

    private static boolean winner() {
        int correctGuesses = (int) Arrays.stream(secretWord.split(""))
                .filter(ch -> correctLetters.contains(ch))
                .count();
        return correctGuesses == secretWord.length();
    }

    private static boolean loser() {
        return currentArt == 7;
    }

    private static void playAgain() {
        System.out.println("Would you like to play again?");
        if (userInput.nextLine().equals("y")) {
            correctLetters.clear();
            incorrectLetters.clear();
            if (loser()) score = 0;
            currentArt = 0;
            getNewWord();
        }
        else {
            if (checkHighScore()) {
                System.out.println("WOW, you hit a  high score!");
            }
            showLeaderBoard();
            System.out.println("Goodbye, " + userName);
            stillPlaying = false;
        }
    }

    //need to rework to display TOP 5 scores
    private static void showLeaderBoard() {
        writeScoreToFile();
        obtainFiles();
        System.out.println("""
                ***********************************************************************
                                            SCORE BOARD
                """);
        scoreHistory.stream().collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("""
                ***********************************************************************
                """);
    }

    private static boolean checkHighScore() {
        if (scoreHistory.size() == 0) return true;
        List<Integer> scoreList = scoreHistory.stream()
                .map(str -> str.split(":"))
                .map(arr -> Integer.parseInt(arr[0]))
                .collect(Collectors.toList());
        int maxNumber = scoreList.stream()
                .max(Integer::compareTo)
                .get();
        return score > maxNumber;
    }

    private static void writeScoreToFile() {
        String entry;
        try {
            Path path = Path.of("src/Resources/scoreHistory.txt");
            if (Files.size(path) == 0) {
                entry = String.format("%s:%s", score, userName );
            }
            else {
                entry = String.format("%s%s:%s", System.lineSeparator(), score, userName);
            }
            Files.write(path, entry.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        }
        catch (Exception e) {
            System.out.println("OOPS file not found");
        }
    }

    public static void main(String[] args) {
        obtainFiles();
        System.out.println(scoreHistory);
        showIntroScreen();
        getUserName();
        getNewWord();
        while (stillPlaying) {
            updateGameArt();
            updateWrongGuesses();
            updateWord();
            System.out.println(displayScore());
            takeGuess();
            checkGuess();
            if (winner()) {
                score+=500;
                updateGameArt();
                updateWord();
                System.out.println(displayScore() + "\nYou win!");
                playAgain();
            }
            if (loser()) {
                updateGameArt();
                updateWord();
                System.out.println(displayScore() + "\nYou Lose!");
                playAgain();
            }
        }
    }
}