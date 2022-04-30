import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Land {
    public int ROWS = 10;
    public int COLUMNS = 10;
    public String [][] map;
    private HashMap<String, Integer> locationOfHuman;
    public HashMap<String, Integer> locationOfGoblins;
    public  int numGoblins;
    public void  generateDefaultMap(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                map[i][j] = ".";
            }
        }
    }

    public void printMap() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (j == COLUMNS - 1) {
                    System.out.println(map[i][j]);
                } else {
                    System.out.print(map[i][j] + "  ");
                }
            }
        }
    }


    public void updateLocation(int x, int y){
        map[x][y] = ".";
    }
    public void updateHumanLocation(int x, int y){
        map[x][y] = "H";

        locationOfHuman.replace("x", x);
        locationOfHuman.replace("y", y);
    }

    public void updateGoblinLocation(int x, int y){
        map[x][y] = "G";

        for(int i = 0; numGoblins < 0; i++){
                locationOfGoblins.replace("x" + i, x);
                locationOfGoblins.replace("y" + i, y);
        }

    }

    private void generateHumanStartingLocation() {
        for (int i = 0; i < 1; i++) {
            int row = (int) (Math.random() * ROWS);
            int column = (int) (Math.random() * COLUMNS);

            if (!Objects.equals(map[row][column], "G")) {
                map[row][column] = "H";
                locationOfHuman.put("x", row);
                locationOfHuman.put("y", column);
            } else {
                i--;
            }
        }
    }

    private void generateGoblinLocations(){
        numGoblins = (int) (Math.random() * 10);

        for(int i = 0; i < numGoblins; i++){

            int row = (int) (Math.random() * ROWS);
            int column = (int) (Math.random() * COLUMNS);

             if (Objects.equals(map[row][column], String.valueOf('G')))
                 i--;
             else {
                 map[row][column] = String.valueOf('G');
                 locationOfGoblins.put("x" + i, row);
                 locationOfGoblins.put("y" + i, column);
             }

        }
    }
    public void land(){
         locationOfHuman = new HashMap<>();
         locationOfGoblins = new HashMap<>();

         map = new String[ROWS][COLUMNS];

         generateDefaultMap();
         generateHumanStartingLocation();
         generateGoblinLocations();

         System.out.println("\n");

    }

    public HashMap<String, Integer> updateHumanLocation() {
        return locationOfHuman;
    }
}

