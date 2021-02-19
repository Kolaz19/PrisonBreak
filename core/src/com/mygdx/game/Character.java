package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;

public class Character extends Coordinate {
    private int height;
    private int width;
    private State state;
    private boolean trapped;
    private Rectangle hitbox;
    Sound stepSound;
    int stepCounter;

    private int speedRight;
    private int speedLeft;
    private int speedUp;
    private int speedDown;


    enum State {
        IDLE,
        RIGHT,
        LEFT,
    }

    public Character(float x, float y,int height, int width, int hitBoxHeight, int hitBoxWidth, Sound step) {
        super(x, y);
        this.height = height;
        this.width = width;
        state = State.IDLE;
        trapped = true;
        hitbox = new Rectangle(x + width / 2 - hitBoxWidth / 2, y + width / 2 - hitBoxWidth / 2, hitBoxWidth , hitBoxHeight);
        this.stepSound = step;
        stepCounter = 0;
    }

    public void resetState(boolean diagonal) {
        if (diagonal) {
            speedRight = speedLeft = speedDown = speedUp = 2; //(int) (Math.sin(45) * 2);
        } else {
            speedRight = speedLeft = speedDown = speedUp = 2;
        }
        if (!Controls.isUpButtonPressed() && !Controls.isRightButtonPressed() && !Controls.isLeftButtonPressed() && !Controls.isDownButtonPressed()) {
            state = State.IDLE;
        }
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

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void playSound () {
        stepCounter++;
        if (stepCounter == 15) {
            stepCounter = 0;
            stepSound.play(0.1f);
        }
    }

    public void moveRight() {
        this.addToX(speedRight);
        state = State.RIGHT;
        hitbox.x = hitbox.x + speedRight;
    }

    public void moveLeft() {
        this.addToX(speedLeft * -1);
        state = State.LEFT;
        hitbox.x = hitbox.x - speedLeft;
    }

    public void moveUp() {
        this.addToY(speedUp);
        hitbox.y = hitbox.y + speedUp;
        if (state == State.IDLE) {
            state = State.LEFT;
        }
    }

    public void moveDown() {
        this.addToY(speedDown * -1);
        hitbox.y = hitbox.y - speedDown;
        if (state == State.IDLE) {
            state = State.RIGHT;
        }
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

    public void setTrapped(boolean isTrapped) {
        trapped = isTrapped;
    }

}
