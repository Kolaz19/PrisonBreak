package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;

public class Character extends Coordinate {
    private int height;
    private int width;
    private State state;
    private boolean trapped;
    private Rectangle hitbox;
    private int group;
    Sound stepSound1;
    Sound stepSound2;
    private boolean playFirstStep;
    int stepCounter;
    private boolean isFree;

    private float speedRight;
    private float speedLeft;
    private float speedUp;
    private float speedDown;

    public int getGroup() {
        return group;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree() {
        isFree = true;
    }


    enum State {
        IDLE,
        RIGHT,
        LEFT,
    }

    public Character(float tileX, float tileY,int height, int width, int hitBoxHeight, int hitBoxWidth,int group, Sound step1, Sound step2) {
        super(tileX * 16, tileY * 16);
        this.height = height;
        this.width = width;
        state = State.IDLE;
        trapped = true;
        hitbox = new Rectangle((tileX * 16) + width / 2 - hitBoxWidth / 2, (tileY * 16) + width / 2 - hitBoxWidth / 2, hitBoxWidth , hitBoxHeight);
        this.stepSound1 = step1;
        this.stepSound2 = step2;
        stepCounter = 0;
        setTrapped(true);
        this.group = group;
        playFirstStep = true;
        isFree = false;
    }

    public void resetState(boolean diagonal) {
        if (diagonal) {
            speedRight = speedLeft = speedDown = speedUp = (float) (Math.sin(45) * 1.25f);
        } else {
            speedRight = speedLeft = speedDown = speedUp = 1.25f;
        }
        if (!Controls.isUpButtonPressed() && !Controls.isRightButtonPressed() && !Controls.isLeftButtonPressed() && !Controls.isDownButtonPressed()) {
            state = State.IDLE;
        }
    }

    public boolean isTrapped() {
        return trapped;
    }


    public void addToSpeedUp(float amount) {
        speedUp += amount;
    }

    public void addToSpeedDown(float amount) {
        speedDown += amount;
    }

    public void addToSpeedRight(float amount) {
        speedRight += amount;
    }

    public void addToSpeedLeft(float amount) {
        speedLeft += amount;
    }

    public float getSpeedUp() {
        return speedUp;
    }

    public float getSpeedDown() {
        return speedDown;
    }

    public float getSpeedRight() {
        return speedRight;
    }

    public float getSpeedLeft() {
        return speedLeft;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void playSound () {
        stepCounter++;
        if (stepCounter == 15) {
            stepCounter = 0;
            if (playFirstStep) {
                stepSound1.play(0.1f);
                playFirstStep = false;
            } else {
                stepSound2.play(0.07f);
                playFirstStep = true;
            }
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
