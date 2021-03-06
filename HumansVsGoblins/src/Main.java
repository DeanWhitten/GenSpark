import java.util.*;

public class Main {
    static Scanner userInput = new Scanner(System.in);
    public static String userName = "";
    public static Human userObj;
    public static List<Object> goblinArr;
    public static Land landObj;

    public static void main(String[] args) {
        startGame();
    }


    public static void startGame() {

        showIntroTitle();
        showIntroText();

        askHumanName();
        userObj = (Human) generateHumanObj();

        showControls();

        Land land = new Land();
        land.land();
        landObj = land;


        land.printMap();

        userObj.setX(land.updateHumanLocation().get("x"));
        userObj.setY(land.updateHumanLocation().get("y"));
        goblinArr = (List<Object>) generateGoblinObjs();
        gameEngine();
    }



    private static void showIntroTitle() {
        System.out.println("""
                 ██░ ██  █    ██  ███▄ ▄███▓ ▄▄▄      ███▄    █   ██████       ██▒   █▓ ██████        ▄████  ▒█████   ▄▄▄▄    ██▓     ██▓ ███▄    █   ██████\s
                ▓██░ ██▒ ██  ▓██▒▓██▒▀█▀ ██▒▒████▄    ██ ▀█   █ ▒██    ▒      ▓██░   █▒██    ▒       ██▒ ▀█▒▒██▒  ██▒▓█████▄ ▓██▒    ▓██▒ ██ ▀█   █ ▒██    ▒\s
                ▒██▀▀██░▓██  ▒██░▓██    ▓██░▒██  ▀█▄ ▓██  ▀█ ██▒░ ▓██▄         ▓██  █▒░ ▓██▄        ▒██░▄▄▄░▒██░  ██▒▒██▒ ▄██▒██░    ▒██▒▓██  ▀█ ██▒░ ▓██▄  \s
                ░▓█ ░██ ▓▓█  ░██░▒██    ▒██ ░██▄▄▄▄██▓██▒  ▐▌██▒  ▒   ██▒       ▒██ █░░ ▒   ██▒     ░▓█  ██▓▒██   ██░▒██░█▀  ▒██░    ░██░▓██▒  ▐▌██▒  ▒   ██▒
                ░▓█▒░██▓▒▒█████▓ ▒██▒   ░██▒ ▓█   ▓██▒██░   ▓██░▒██████▒▒        ▒▀█░ ▒██████▒▒     ░▒▓███▀▒░ ████▓▒░░▓█  ▀█▓░██████▒░██░▒██░   ▓██░▒██████▒▒
                 ▒ ░░▒░▒░▒▓▒ ▒ ▒ ░ ▒░   ░  ░ ▒▒   ▓▒█░ ▒░   ▒ ▒ ▒ ▒▓▒ ▒ ░        ░ ▐░ ▒ ▒▓▒ ▒ ░      ░▒   ▒ ░ ▒░▒░▒░ ░▒▓███▀▒░ ▒░▓  ░░▓  ░ ▒░   ▒ ▒ ▒ ▒▓▒ ▒ ░
                 ▒ ░▒░ ░░░▒░ ░ ░ ░  ░      ░  ▒   ▒▒ ░ ░░   ░ ▒░░ ░▒  ░ ░        ░ ░░ ░ ░▒  ░ ░       ░   ░   ░ ▒ ▒░ ▒░▒   ░ ░ ░ ▒  ░ ▒ ░░ ░░   ░ ▒░░ ░▒  ░ ░
                 ░  ░░ ░ ░░░ ░ ░ ░      ░     ░   ▒     ░   ░ ░ ░  ░  ░            ░░ ░  ░  ░       ░ ░   ░ ░ ░ ░ ▒   ░    ░   ░ ░    ▒ ░   ░   ░ ░ ░  ░  ░ \s
                 ░  ░  ░   ░            ░         ░  ░        ░       ░             ░       ░             ░     ░ ░   ░          ░  ░ ░           ░       ░ \s
                                                                                   ░                                       ░                                \s                                                                                        
                """);
    }
    private static void showIntroText() {
        System.out.println("""
                   _________________________________________________________________________________________________________________________________________
                 / \\                                                                                                                                        \\.
                |   |                                                                                                                                       |.
                 \\_ |          You Wake up in a strange land......                                                                                         |.
                    |                                                                                                                                       |.
                    |           After finding fellow humans in this strange land you find that the human race has been nearly wiped out........             |.
                    |                                                                                                                                       |.
                    |           ......... You are told GOBLINS are responsible!                                                                             |.
                    |                                                                                                                                       |.
                    |                                                                                                                                       |.
                    |           It is up to you to save the human race from goblin menace!                                                                  |.
                    |   ____________________________________________________________________________________________________________________________________|__
                    |  /                                                                                                                                      /
                    \\_/______________________________________________________________________________________________________________________________________/.
                """);
    }

    private static void askHumanName() {
        System.out.println("What is your name human?!");
        userName = userInput.nextLine();
    }

    private static Object generateHumanObj() {
        return  new Human(userName);
    }

    private static void showControls() {
         int userHealth = userObj.getHealth();
         String strHealth = String.valueOf(userHealth);

         int userStrength = userObj.getStrength();
         String strStength = String.valueOf(userStrength);

         int userLives = userObj.getLives();
         String strLives = String.valueOf(userLives);

         String controller = ("""
                _________________
                |       ᐃ       |       $humanName the Human
                |       W       |       Health:   $humanHealth / 100 
                |  ᐊ A     D ᐅ  |       Strength:  $humanStrength
                |       S       |
                |       ᐁ       |       Lives:  $lives
                -----------------
                 """).replace("$humanName", userObj.getName()).replace("$humanHealth", strHealth).replace(
                         "$humanStrength", strStength).replace("$lives", strLives);

         System.out.println(controller);
    }

    private static Object generateGoblinObjs() {
        goblinArr = new ArrayList<>();
        for( int i = 0; i < landObj.numGoblins; i++){
            Goblin goblinEle = new Goblin("Goblin" + (i+1) );
            goblinEle.setX(landObj.locationOfGoblins.get("x" + i));
            goblinEle.setY(landObj.locationOfGoblins.get("y" + i));
            goblinArr.add(goblinEle);
            System.out.println(goblinEle.getName() + " " + goblinEle.getX() + goblinEle.getY());
        }

        return goblinArr;
    }

    private static void gameEngine() {

        while(true){
            System.out.println("Select your move human!");
            String move = userInput.nextLine();
            while(!move.matches("[WwDdSsAa]")){
                System.out.println(move + " ISN'T AN OPTION! Select your move human!");
                move = userInput.nextLine();
            }

            String  validSelection = move.toLowerCase(Locale.ROOT);
            int hX = userObj.getX();
            int hY = userObj.getY();


            //  UP
            if(validSelection.equals("w")){
               //
                if( !(hX - 1 < 0) ){
                    landObj.updateLocation(hX, hY);
                    makeMove((hX - 1), hY);
                } else {
                 displayOutofBoundAttemptAlert();
                }
            }

            // move DOWN
            if(validSelection.equals("s")){
                if(!(hX + 1 >= landObj.getCOLUMNS())){
                    landObj.updateLocation(hX, hY);
                    makeMove((hX + 1), hY);
                }else {
                    displayOutofBoundAttemptAlert();
                }
            }

            // move RIGHT
            if(validSelection.equals("d")){
                if(!(hY + 1 >= landObj.getROWS())){
                    landObj.updateLocation(hX, hY);
                    makeMove(hX, (hY+1));
                } else {
                    displayOutofBoundAttemptAlert();
                }
            }

            // move LEFT
            if(validSelection.equals("a")){
                if(!(hY - 1 < 0)){
                    landObj.updateLocation(hX, hY);
                    makeMove(hX, (hY-1));
                } else {
                    displayOutofBoundAttemptAlert();
                }
            }

        }
    }

    private static void displayOutofBoundAttemptAlert() {
        System.out.println("HEY YOU CANT GO THAT WAY!");
    }


    private static void makeMove(int x, int y){
        int hX = userObj.getX();
        int hY = userObj.getY();

        //looks to see in there a goblin
        for(int i = 0; i < goblinArr.size(); i++){
            Goblin gEle = (Goblin) goblinArr.get(i);
            int gX = gEle.getX();
            int gY = gEle.getY();

            if (gX == x && gY == y ){
              startBattle(gEle);
            }else {
              userObj.setX(x);
              userObj.setY(y);
            }
        }

        landObj.updateLocation(hX, hY);
        landObj.updateHumanLocation(x, y);
        showControls();
        landObj.printMap();
    }

    private static void startBattle(Goblin gEle) {
        displayBattle(gEle);
        while((userObj.getLives() != 0 && !(userObj.getHealth() < 0)) && (gEle.getHealth() != 0 &&  !(gEle.getHealth() < 0))){
            // ^spicy logic^ - !( (HL != 0 && !(HH < 0)) && (GH != 0  && !(GH < 0)) )
            // catches the battle loop if user life & health is below 0 or resumes game if goblins health is 0 or
            // below  -> keeps going until human is out of life or goblin is killed

            String battleMove = userInput.nextLine();

            while(!battleMove.matches("[123]")){
                System.out.println(battleMove + " ISN'T AN OPTION! Select your move human!");
                battleMove = userInput.nextLine();
            }
            calculateMoveResults(gEle, battleMove);
        }
    }
    
    private static void displayBattle(Goblin gEle) {   ////rewrite to make more reusable
        String hName = userObj.getName();
        String hHth = String.valueOf(userObj.getHealth());
        String hStg = String.valueOf(userObj.getStrength());
        String hlives = String.valueOf(userObj.getLives());

        String gName = gEle.getName();
        String gHth = String.valueOf(gEle.getHealth());
        String gStg = String.valueOf(gEle.getStrength());


        String battleScreen = ("""
                +-------------------------------------------------------------------------------------------------------+
                 +------------+                                          +-----------+
                 |      +     |    $humanName the human                        |  (,,,) W  |   $goblinName
                 |  ,.'` `'., |    Health: $humanHealth / 100      vs              |  d'o'b |  |   Health: $goblinHealth / 100
                 |   |:o o:|  |    Strength: $humanStrength                          |  <(_)--c  |   Strength: $goblinStrength
                 |    \\(_)/   |                                           |  _( )_ |  |
                 +____________+    Lives:  $lives                            +-----------+
                +-------------------------------------------------------------------------------------------------------+
                	                   1 = Attack       2 = Dodge     
                """).replace("$humanName",hName)
                .replace("$humanHealth", hHth)
                .replace("$humanStrength", hStg)
                .replace("$lives", hlives)
                .replace("$goblinName", gName)
                .replace("$goblinHealth", gHth)
                .replace("$goblinStrength", gStg);

        System.out.println(battleScreen);
    }

    private static void calculateMoveResults(Goblin gEle, String battleMove) {
                   /*
                   * store: human choice, human health, hum str, human lives, goblin health, goblin strength
                   * generate goblin selection 1 - 3 : 1 - attack, 2 block, 3 dodge  return num as a string
                   *
                   * concatenate human + goblin selection and store it
                   *
                   * switch over human/goblin choice concatenated. exp--> humanChoice(1) + goblinChoice(3) = "13"
                   *        
                   * */
        String hMove = battleMove;
        String gMove = generateGoblinMove();
        String movesCombined = hMove + gMove;

        Boolean favoredInOdds;

        switch (movesCombined){
            case"11":
                //A + A= both take dmg equal to each's strength
                userObj.setHealth(userObj.getHealth()- gEle.getStrength());
                gEle.setHealth(gEle.getHealth() - userObj.getStrength());
                displayBattle(gEle);
                System.out.println("You and the goblin both attack, resulting in both of you taking significant " +
                        "damage!");
                break;
            case"12":
                //A + D = grab dodge probability for goblin return if true, no damage, if false goblin takes damage
                // equal to human's strength multiplied by 2.
                favoredInOdds = determineMoveProbability();

                if(favoredInOdds){
                   displayBattle(gEle);
                   System.out.println("You attack but " + gEle.getName() + " was too fast! YOU MISSED!");
                }else {

                   gEle.setHealth(gEle.getHealth()- (userObj.getStrength() * 2));
                   displayBattle(gEle);
                   System.out.println("You attack and " +gEle.getName() + " attempts to dodge, but you are quick...." +
                            " ITS A DIRECT HIT!");
                }
                break;
            case"21":
                //D + A = grab dodge probability for human, if true human dodges if false human takes damage equal
                // to goblin's strength multiplied by 2
                favoredInOdds = determineMoveProbability();
                if(favoredInOdds){
                    displayBattle(gEle);
                    System.out.println(gEle.getName() + " charges at you to attack... you are quick and dodge their " +
                            "attack! You take no damage!");
                } else {
                    userObj.setHealth( userObj.getHealth() - (gEle.getStrength() *2) );
                    displayBattle(gEle);
                    System.out.println(gEle.getName() +" charges at you... you attempt to dodge.... but aren't fast " +
                            "enough... You take a direct hit and suffer major damage!");
                }
                break;
            case"22":
                //D + D = nothing happens to stats
                displayBattle(gEle);
                System.out.println(gEle.getName() + " and you are moving around looking for opening for your next moves" +
                        ".");
                break;

        }

        if(userObj.getHealth() <= 0){
            userObj.setLives(userObj.getLives()-1);
            if(!(userObj.getLives() <= 0)){
                userObj.setHealth(100);
            }else{
                gameOver();
            }
        }

        if(gEle.getHealth() <= 0){
            System.out.println("You defeat " + gEle.getName() + " in battle!");
            goblinArr.remove(gEle);

            if(goblinArr.isEmpty()){
                System.out.println("YOU WON! YOU OVER THREW THE GOBLINS AND SAVED THE HUMAN RACE!");
                System.exit(0);
            }else{
                System.out.println("There are now only " + goblinArr.size() + " goblins left!");
                levelUpGoblins();
                randomUserBoost();
            }

        }
    }

    private static void randomUserBoost() {
        userObj.setHealth(userObj.getHealth()+25);
        userObj.setLives(userObj.getLives()+1);
        userObj.setStrength(userObj.getStrength() + 10);
    }

    private static void levelUpGoblins() {
        for(int i = 0; i < goblinArr.size(); i++){
            Goblin g = (Goblin) goblinArr.get(i);
             g.setStrength(g.getStrength() + 10);
        }
    }


    private static boolean determineMoveProbability() {
        int dice = 20;
        Random roll = new Random();
        int rolledHum = roll.nextInt(dice) + 1;

        if (rolledHum % 2 == 0){;
            return true;
        } else {
            return false;
        }
    }

    private static String generateGoblinMove() {
        Random rn = new Random();
        int randomGoblinMove = rn.nextInt(1)+1;
        return String.valueOf(randomGoblinMove);
    }

    private static void gameOver() {
        System.out.println("YOU DIED! GAME OVER");
        System.exit(0);
    }




}