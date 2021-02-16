package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Character extends Coordinate {
    private int height;
    private int width;
    private State state;

    enum State {
        IDLE,
        MOVELEFT,
        MOVERIGHT,
        MOVEUP,
        MOVEDOWN,
    }

    public Character(float x, float y, int height, int width) {
        super(x, y);
        this.height = height;
        this.width = width;
        state = State.IDLE;
    }

    public void resetState() {
        state = State.IDLE;
    }

    public void moveRight(int pixels) {
        this.addToX(pixels);
        state = State.MOVERIGHT;
    }

    public void moveLeft(int pixels) {
        this.addToX(pixels * -1);
        state = State.MOVELEFT;
    }

    public void moveUp(int pixels) {
        this.addToY(pixels);
        state = State.MOVEUP;
    }

    public void moveDown(int pixels) {
        this.addToY(pixels * -1);
        state = State.MOVEDOWN;
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
