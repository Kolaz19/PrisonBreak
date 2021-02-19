package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controls {
    public static boolean diagonal;

    public static void updateDiagonal() {
        if ((isUpButtonPressed() && isRightButtonPressed()) || (isRightButtonPressed() && isDownButtonPressed())
        || (isDownButtonPressed() && isLeftButtonPressed()) || (isLeftButtonPressed() && isUpButtonPressed())) {
            diagonal = true;
        } else {
            diagonal = false;
        }
    }
    public static boolean isUpButtonPressed() {
        return (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) && !(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN));
    }

    public static boolean isDownButtonPressed() {
        return (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) && !(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP));
    }

    public static boolean isRightButtonPressed() {
        return (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && !(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT));
    }

    public static boolean isLeftButtonPressed() {
        return (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) && !(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT));
    }

}
