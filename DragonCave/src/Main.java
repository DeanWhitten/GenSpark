import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner userInput = new Scanner(System.in);
    static int selection;
    static int checker;
       public static void main (String[] args){
           System.out.println(
                   "\n-------------------------------------------------------------------------\n" +
                   "   You are in a land full of dragons. In front of you,\n" +
                   "   you see two caves. In one cave, the dragon is friendly\n" +
                   "   and will share his treasure with you. The other dragon\n" +
                   "   is greedy and hungry and will eat you on sight.\n" +
                   "-------------------------------------------------------------------------\n"
           );
           takeInput();
    }
    
    private static void takeInput() {
            System.out.println("\nWhich cave will you go into? (1 or 2)");
            checkInput();
            outputSelection();
    }

    private static int checkInput() {
        try {
            selection = Integer.parseInt(userInput.next());
            return selection;
        } catch (NumberFormatException e) {
            int fakeNum = 0;
            userInput.nextLine();
            System.out.println("ERROR: INVALID INPUT - NOT A NUMBER");
            return fakeNum ;
        }
    }

    private static void outputSelection() {
        if (selection == 1){
            System.out.println(
                    "\n--------------------------------------------------------------------------\n" +
                            "   You approach the cave ...\n" +
                            "   It is dark and spooky ...\n" +
                            "   A Large dragon jumps out in front of you! He opens his jaws and ...\n" +
                            "   Gobbles you down in one bite!\n" +
                            "--------------------------------------------------------------------------\n"
            );
        } else if (selection == 2){
            System.out.println(
                    "\n--------------------------------------------------------------------------\n" +
                            "   You approach the cave ...\n" +
                            "   It is dark and spooky ...\n" +
                            "   A Large dragon jumps out in front of you! He opens his jaws and ...\n" +
                            "   Asks if you want some gold!\n" +
                            "--------------------------------------------------------------------------\n"
            );
        } else{
                System.out.println("Not a selection, try again.");
                takeInput();
        }
    }
}
