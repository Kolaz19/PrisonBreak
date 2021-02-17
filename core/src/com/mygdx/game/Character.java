package com.mygdx.game;

public class Character extends Coordinate {
    private int height;
    private int width;
    private State state;

    private int speedRight;
    private int speedLeft;
    private int speedUp;
    private int speedDown;


    enum State {
        IDLE,
        UP,
        RIGHT,
        LEFT,
        DOWN
    }

    public Character(float x, float y, int height, int width) {
        super(x, y);
        this.height = height;
        this.width = width;
        state = State.IDLE;
    }

    public void resetState(boolean diagonal) {
        if (diagonal) {
            speedRight = speedLeft = speedDown = speedUp = (int) (Math.sin(45) * 2);
        } else {
            speedRight = speedLeft = speedDown = speedUp = 2;
        }
        state = State.IDLE;
    }


    public void addToSpeedUp(int amount) {
        speedUp += amount;
    }

    public void addToSpeedDown(int amount) {
        speedDown += amount;
    }

    public void addToSpeedRight(int amount) {
        speedRight += amount;
    }

    public void addToSpeedLeft(int amount) {
        speedLeft += amount;
    }

    public int getSpeedUp() {
        return speedUp;
    }

    public int getSpeedDown() {
        return speedDown;
    }

    public int getSpeedRight() {
        return speedRight;
    }

    public int getSpeedLeft() {
        return speedLeft;
    }

    public void moveRight() {
        this.addToX(speedRight);
        state = State.RIGHT;
    }

    public void moveLeft() {
        this.addToX(speedLeft * -1);
        state = State.LEFT;
    }

    public void moveUp() {
        this.addToY(speedUp);
        state = State.UP;
    }

    public void moveDown() {
        this.addToY(speedDown * -1);
        state = State.DOWN;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public State getState() {
        return state;
    }

}
