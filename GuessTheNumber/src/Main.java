import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class Main {

    static String userName,
            playAgainResponse;
    static int attemptNum = 0,
            userInputNum,
            mysteryNum = 100, //gets rewritten with a random num once game starts
            maxNumAttempts = 6; // 6 toggle on game engine------- 9 toggle off game engine-----8 end game after play

    static Scanner userInput = new Scanner (System.in);

    public static void main(String[] args) {
            gameEngine();
    }

    private static void gameEngine() {
        while(maxNumAttempts == 6){
            if (attemptNum == 0) {
                startGame();
                takeGuess();
                checkNumber();
            } else {
                if (attemptNum != maxNumAttempts) {
                    takeGuess();
                    checkNumber();
                }
                if (attemptNum == maxNumAttempts){
                    System.out.println("GAMEOVER....\nNumber of attempts:" + attemptNum);
                    maxNumAttempts = 9;
                    playAgain();
                }
            }
        }
    }

    private static void startGame() {
        System.out.println("Hello! What is your name?");
        userName = userInput.nextLine();

        System.out.println("Well, " + userName + ", I am thinking of a number between 1 and 20.");
        generateMysteryNumber();
    }

    private static void generateMysteryNumber() {
        Random random = new Random();
        mysteryNum = random.nextInt(20)+1;
    }

    private static void takeGuess() {
        System.out.println("Take a Guess.");
        userInputNum = userInput.nextInt();
        attemptNum++;
    }

    private static void checkNumber() {
        if(mysteryNum > userInputNum){
            System.out.println("Your guess is too low.");
        }  else if (userInputNum > mysteryNum){
            System.out.println("Your Guess is too high.");
        }   else{
            System.out.println("Good job," + userName + "!" + " You guessed my number in " + attemptNum + " guesses!");
            maxNumAttempts = 9;
            playAgain();
        }
    }

    private static void playAgain() {
        maxNumAttempts = 9;
        System.out.println("Would you like to play again? (y or n)");

        while(maxNumAttempts == 9){
            playAgainResponse = userInput.nextLine();
            if(Objects.equals(playAgainResponse, "y")){
                attemptNum = 0;
                mysteryNum = 100;
                maxNumAttempts = 6;
            }else if (Objects.equals (playAgainResponse, "n")){
                maxNumAttempts = 8; //breaks 
            }
        }
    }
}
