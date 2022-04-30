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


            // move UP
            if(validSelection.equals("w")){
                landObj.updateLocation(hX, hY);
                makeMove((hX - 1), hY);

            }
            // move DOWN
            if(validSelection.equals("s")){
                landObj.updateLocation(hX, hY);
                makeMove((hX + 1), hY);
            }
            // move RIGHT
            if(validSelection.equals("d")){
                landObj.updateLocation(hX, hY);
                makeMove(hX, (hY+1));
            }
            // move LEFT
            if(validSelection.equals("a")){
                landObj.updateLocation(hX, hY);
                makeMove(hX, (hY-1));
            }
        }
    }


    private static void makeMove(int x, int y){
      int hX = userObj.getX();
      int hY = userObj.getY();

      if(hX != 0 && hY != 0){
          for(int i = 0; i < goblinArr.size(); i++){
              Goblin gEle = (Goblin) goblinArr.get(i);
              int gX = gEle.getX();
              int gY = gEle.getY();

              if (gX == x && gY == y ){
                  startBattle(gEle, userObj.getName(),userObj.getHealth(), userObj.getStrength(), userObj.getLives());
              }else {
                  userObj.setX(x);
                  userObj.setY(y);
              }
          }
          showControls();
          landObj.updateHumanLocation(x, y);
          landObj.printMap();
      }
      showControls();
      landObj.updateHumanLocation(x, y);
      landObj.printMap();


    }

    private static void startBattle(Goblin gEle, String hName, int hHealth, int hStrength, int lives) {
        String gName = gEle.getName();
        int gHealth = gEle.getHealth();
        int gStrength = gEle.getStrength();


        while(lives != 0 && gHealth != 0){
            displayBattle(gName,gHealth,gStrength, hName, hHealth, hStrength, lives);
            String battleMove = userInput.nextLine();

            while(!battleMove.matches("[1234]")){
                System.out.println(battleMove + " ISN'T AN OPTION! Select your move human!");
                battleMove = userInput.nextLine();
            }

            calculateMoveResults(battleMove, gHealth, gStrength, hHealth, hStrength, lives);
        }



    }



    private static void displayBattle(String gName, int gHealth, int gStrength, String hName, int hHealth, int hStrength, int lives) {
        String hHth = String.valueOf(hHealth);
        String hStg = String.valueOf(hStrength);
        String hlives = String.valueOf(lives);

        String gHth = String.valueOf(gHealth);
        String gStg = String.valueOf(gStrength);


        String battleScreen = ("""
                +-------------------------------------------------------------------------------------------------------+
                  +------------+                                               +-----------+
                  |      +     |    $humanName the human                                  |  (,,,) W  |   $goblinName
                  |  ,.'` `'., |    Health: $humanHealth / 100      vs                  |  d'o'b |  |   Health: $goblinHealth / 100
                  |   |:o o:|  |    Strength: $humanStrength                               |  <(_)--c  |   Strength: $goblinStrength
                  |    \\(_)/   |                                               |  _( )_ |  |
                  +____________+    Lives:  $lives                                  +-----------+
                +-------------------------------------------------------------------------------------------------------+
                   1 = Attack       2 = Block       3 = Dodge       4 = RUN!
                """).replace("$humanName",hName)
                .replace("$humanHealth", hHth)
                .replace("$humanStrength", hStg)
                .replace("$lives", hlives)
                .replace("$goblinName", gName)
                .replace("$goblinHealth", gHth)
                .replace("$goblinStrength", gStg);

        System.out.println(battleScreen);
    }
    private static void calculateMoveResults(String battleMove, int gHealth, int gStrength, int hHealth, int hStrength, int lives) {
                   /*
                   * store: human choice, human health, hum str, human lives, goblin health, goblin strength
                   * generate goblin selection 1 - 3 : 1 - attack, 2 block, 3 dodge  return num as a string
                   *
                   * concatenate human + goblin selection and store it
                   *
                   * switch over human/goblin choice concatenated. exp--> humanChoice(1) + goblinChoice(3) = 13
                   *        
                   * */
    }
}