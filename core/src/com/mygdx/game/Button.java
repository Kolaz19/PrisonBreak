package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Button {
    private TextureRegion buttonUpTexture;
    private TextureRegion buttonDownTexture;
    private int group;
    private boolean isPressed;
    private Rectangle hitBox;
    private Sound inSound;
    private Sound outSound;

    public Button(TextureRegion buttonUp, TextureRegion buttonDown, int tileX, int tileY, int group, Sound in, Sound out) {
        buttonUpTexture = buttonUp;
        buttonDownTexture = buttonDown;
        this.group = group;
        hitBox = new Rectangle(tileX * 16,tileY * 16,buttonDown.getRegionWidth(),buttonDown.getRegionHeight());
        isPressed = false;
        inSound = in;
        outSound = out;
    }

    public TextureRegion getCorrectTexture() {
        if (isPressed) {
            return buttonDownTexture;
        } else {
            return buttonUpTexture;
        }
    }


    public int getX() {
        return (int) hitBox.x;
    }

    public int getY() {
        return (int) hitBox.y;
    }

    public Rectangle getHitbox () {
        return hitBox;
    }

    public void setPressed (boolean isPressed) {
        if (this.isPressed != isPressed) {
            if(isPressed) {
                inSound.play();
            } else {
                outSound.play();
            }
            this.isPressed = isPressed;
        }
    }

    public boolean isPressed() {
        return isPressed;
    }


    public int getGroup() {
        return group;
    }
}
