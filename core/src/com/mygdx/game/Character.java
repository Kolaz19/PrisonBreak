package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

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
            speedRight = speedLeft = speedDown = speedUp = (int) (Math.sin(45) * 1);
        } else {
            speedRight = speedLeft = speedDown = speedUp = 3;
        }
        state = State.IDLE;
    }

    public void setSpeedInPixels(State direction, int speedInPixel) {
        switch (state) {
            case UP: speedUp = speedInPixel;
            break;
            case DOWN: speedDown = speedInPixel;
            break;
            case LEFT: speedLeft = speedInPixel;
            break;
            case RIGHT: speedRight = speedInPixel;
            break;
        }
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
