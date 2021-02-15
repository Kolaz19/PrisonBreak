package com.mygdx.game;

public class Coordinate {
    private float x;
    private float y;

    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    protected void setX(float x) {
        this.x = x;
    }

    protected void setY(float y) {
        this.y = y;
    }

    protected void addToX(float amount) {
        this.x += amount;
    }

    protected void addToY(float amount) {
        this.y += amount;
    }
}
