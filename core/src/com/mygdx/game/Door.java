package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Door {
    private Animation<TextureRegion> animation;
    private Rectangle hitbox;
    private float stateTime;
    private boolean isOpening;
    private boolean isOpen;
    private int group;
    private Sound openSound;

    public Door(int tileX, int tileY, Animation<TextureRegion> animation, int group, Sound open) {
        this.animation = animation;
        isOpening = false;
        this.group = group;
        this.stateTime = 0f;
        hitbox = new Rectangle(tileX * 16, tileY * 16, animation.getKeyFrame(stateTime).getRegionWidth(),animation.getKeyFrame(stateTime).getRegionHeight());
        this.openSound = open;
    }

    public float getX() {
        return hitbox.getX();
    }

    public float getY() {
        return hitbox.getY();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public TextureRegion getCorrectFrame() {
        if(animation.isAnimationFinished(stateTime)) {
            isOpen = true;
        } else if (isOpening) {
            stateTime += Gdx.graphics.getDeltaTime();
        }
        return animation.getKeyFrame(stateTime,false);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getGroup() {
        return group;
    }

    public void shouldOpen () {
        if (!this.isOpening) {
            openSound.play(0.5f);
        }
        this.isOpening = true;
    }
}
