package com.example.humansvsgoblins_android;

import android.widget.ImageView;

import java.util.Objects;

public class Human extends Humanoid {
    private int lives ;
    private ImageView view;

    public int getLives() {return lives;}
    public void setLives(int lives){this.lives = lives;}

    public ImageView getView() {return view;}
    public void setView(ImageView v) {view = v;}

    public Human(String name){
        super(name, 100, 15);
        lives = 3;
    }

    public void generateStartLocation( int r, int c){
        for (int i = 0; i < 1; i++) {
            int row = (int) (Math.random() * r);
            int column = (int) (Math.random() * c);
            setNewLocation(row, column);
        }
    }

    private void setNewLocation(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

}