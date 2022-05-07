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

           selectionCaveResults(selection);
       }
       
       public static int selectionCaveResults(int sI){
           int s = sI;
           if (s == 1){
               cave1();
           } else if (s == 2){
               cave2();
           }  else{
               System.out.println("ERROR: INVALID INPUT, " + s + " IS NOT AN OPTION!");
           }
           return s;
       }

    private static void cave2() {
        System.out.println(
                "\n--------------------------------------------------------------------------\n" +
                        "   You approach the cave ...\n" +
                        "   It is dark and spooky ...\n" +
                        "   A Large dragon jumps out in front of you! He opens his jaws and ...\n" +
                        "   Asks if you want some gold!\n" +
                        "--------------------------------------------------------------------------\n"
        );
    }

    private static void cave1() {
           System.out.println(
            "\n--------------------------------------------------------------------------\n" +
                    "   You approach the cave ...\n" +
                    "   It is dark and spooky ...\n" +
                    "   A Large dragon jumps out in front of you! He opens his jaws and ...\n" +
                    "   Gobbles you down in one bite!\n" +
                    "--------------------------------------------------------------------------\n"
            );
    }


}
