package com.example.humansvsgoblins_android;

public class Human extends Humanoid {
    private int lives ;

    public int getLives() {return lives;}
    public void setLives(int lives){this.lives = lives;}

    public Human(String name){
        super(name, 100, 15);
        lives = 3;
    }

}