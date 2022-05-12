package com.example.humansvsgoblins_android;

public class Land{

    private int x;
    private int y;

    public Land(int row, int col) {
        x = row;
        y = col;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int xVal) {
        x = xVal;
    }

    public void setY(int yVal) {
        y = yVal;
    }



}
