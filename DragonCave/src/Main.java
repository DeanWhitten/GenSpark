import java.util.Scanner;

public class Main {
       public static void main (String[] args){
           int selection;

           System.out.println(
                   "\n-------------------------------------------------------------------------\n" +
                   "   You are in a land full of dragons. In front of you,\n" +
                   "   you see two caves. In one cave, the dragon is friendly\n" +
                   "   and will share his treasure with you. The other dragon\n" +
                   "   is greedy and hungry and will eat you on sight.\n" +
                   "-------------------------------------------------------------------------\n" +
                   "\nWhich cave will you go into? (1 or 2)\n"
           );

           Scanner userInput = new Scanner(System.in);
           selection = userInput.nextInt();

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
           }  else{
               System.out.println("ERROR: INVALID INPUT, " + selection + " IS NOT AN OPTION!");
           }

           
       }
}
