import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
       static Scanner userInput = new Scanner(System.in);
       static int selection;
       public static void main (String[] args){


           System.out.println(
                   "\n-------------------------------------------------------------------------\n" +
                   "   You are in a land full of dragons. In front of you,\n" +
                   "   you see two caves. In one cave, the dragon is friendly\n" +
                   "   and will share his treasure with you. The other dragon\n" +
                   "   is greedy and hungry and will eat you on sight.\n" +
                   "-------------------------------------------------------------------------\n" +
                   "\nWhich cave will you go into? (1 or 2)\n"
           );

           takeInput();



           if (selection == 1){
               System.out.println(
                       "\n--------------------------------------------------------------------------\n" +
                               "   You approach the cave ...\n" +
                               "   It is dark and spooky ...\n" +
                               "   A Large dragon jumps out in front of you! He opens his jaws and ...\n" +
                               "   Gobbles you down in one bite!\n" +
                               "--------------------------------------------------------------------------\n"
               );
           }

           if (selection == 2){
               System.out.println(
                       "\n--------------------------------------------------------------------------\n" +
                               "   You approach the cave ...\n" +
                               "   It is dark and spooky ...\n" +
                               "   A Large dragon jumps out in front of you! He opens his jaws and ...\n" +
                               "   Asks if you want some gold!\n" +
                               "--------------------------------------------------------------------------\n"
               );
           }

       }

    private static void takeInput() {
            selection = checkInput();
            System.out.println(selection);
    }

    private static int checkInput() {
            try {
                return userInput.nextInt();
            }catch(InputMismatchException e){
                System.out.println("ERROR: INVALID INPUT - NOT A NUMBER");
                return userInput.nextInt();
            }
        }
}
