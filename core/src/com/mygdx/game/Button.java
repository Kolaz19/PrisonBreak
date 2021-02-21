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
    private int padding;

    public Button(TextureRegion buttonUp, TextureRegion buttonDown, int tileX, int tileY, int group, Sound in, Sound out) {
        buttonUpTexture = buttonUp;
        buttonDownTexture = buttonDown;
        this.group = group;
        padding = 3;
        hitBox = new Rectangle(tileX * 16 + padding,tileY * 16 + padding,buttonDown.getRegionWidth() - 2*padding,buttonDown.getRegionHeight() - 2*padding);
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
        return (int) hitBox.x - padding;
    }

    public int getY() {
        return (int) hitBox.y - padding;
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
