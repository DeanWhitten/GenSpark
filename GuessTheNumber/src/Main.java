 import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class Main {

    static String userName;
    static int attemptNum = 0,
            userInputNum,
            mysteryNum, //gets rewritten with a random num once game starts
            maxNumAttempts = 6; // 6 toggle on game engine------- 9 toggle off game engine-----8 end game after play

    static Scanner userInput = new Scanner (System.in);

    public static void main(String[] args) {
            gameEngine();
    }

    private static void gameEngine() {
        startGame();
        while(maxNumAttempts == 6){
            if (attemptNum == 0) {
                takeGuess();
            } else {
                if (attemptNum != maxNumAttempts) {
                    takeGuess();
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

    private static int takeGuess() {
        System.out.println("Take a Guess.");
        try{
            userInputNum = Integer.parseInt(userInput.next());
            checkNumber();
            attemptNum++;
            return userInputNum;
        } catch(NumberFormatException e){
            System.out.println("ERROR: INVALID INPUT - NOT A NUMBER");
            userInput.nextLine();
            return 0;
        }
    }

    private static void checkNumber() {
        if(mysteryNum > userInputNum){
            System.out.println("Your guess is too low.");
        }  else if (userInputNum > mysteryNum){
            System.out.println("Your Guess is too high.");
        }   else{
            System.out.println("Good job," + userName + "!" + " You guessed my number in " + (attemptNum + 1) + " guesses!");
            maxNumAttempts = 9;
            playAgain();
        }
    }

    private static void playAgain() {
        System.out.println("Would you like to play again? (y or n)"); //hot fix for duplicate printing: needs to be put in the while loop below inside own loop set to iterate one time
        while(maxNumAttempts == 9){
                ++maxNumAttempts;
                String playAgainResponse = userInput.nextLine();

                if(Objects.equals(playAgainResponse, "y")){
                    attemptNum = 0;
                    generateMysteryNumber();
                    maxNumAttempts = 6;
                }else if (Objects.equals (playAgainResponse, "n")){
                    maxNumAttempts = 8; //breaks
                } else{
                    maxNumAttempts = 9;
                }
            }



    }
}
