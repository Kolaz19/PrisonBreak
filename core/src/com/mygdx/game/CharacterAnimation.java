package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CharacterAnimation {
    Animation[] animations;

    public CharacterAnimation() {
        animations = new Animation[3];
    }

    public void addIdleAnimation(Animation animation) {
        animations[0] = animation;
    }

    public void addMoveLeftAnimation(Animation animation) {
        animations[1] = animation;
    }

    public void addMoveRightAnimation(Animation animation) {
        animations[2] = animation;
    }
    //Test if animations are rendered
    public TextureRegion getRightAnimation(Character.State state, float stateTime) {
        switch (state) {
            case IDLE: return (TextureRegion) animations[0].getKeyFrame(stateTime);
            case LEFT: return (TextureRegion) animations[1].getKeyFrame(stateTime);
            case RIGHT: return (TextureRegion) animations[2].getKeyFrame(stateTime);
        }
        return (TextureRegion) animations[0].getKeyFrame(stateTime);
    }



}
