import java.util.HashMap;

public class Humanoid {
    private String name;
    private int health, strength;
    private int x = 0, y = 0;

    public Humanoid(String name , int health, int strength){
        this.name = name;
        this.health = health;
        this.strength = strength;
    }



    //Getters
    public String getName(){return name;}
    public int getHealth(){return health;}
    public int getStrength(){return strength;}
    public int getX(){return x;}
    public int getY(){return y;}

    //Setters

    public void setName(String name){this.name = name;}
    public void setHealth(int health){this.health = health;}
    public void setStrength(int strength){this.strength = strength;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}


}
