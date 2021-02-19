package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Door {
    private Animation<TextureRegion> animation;
    private int x;
    private int y;

    public Door(int tileX, int tileY, Animation<TextureRegion> animation) {
        x = tileX * 16;
        y = tileY * 16;
        this.animation = animation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }




}
