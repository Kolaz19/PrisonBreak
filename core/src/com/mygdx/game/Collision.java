package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

public class Collision {
    private MapCollision mapCollision;

    public Collision (MapCollision mapCollision) {
        this.mapCollision = mapCollision;
    }

    public void reduceSpeedThroughCollision (Character character) {
        reduceSpeedUp(character);
        reduceSpeedDown(character);
        reduceSpeedRight(character);
        reduceSpeedLeft(character);
    }

    private void reduceSpeedUp (Character character) {
        if (!Controls.isUpButtonPressed()) {
            return;
        }
        while (mapCollision.willHitUpperBoundary(character)) {
            character.addToSpeedUp(- 1);
        }
    }

    private void reduceSpeedDown (Character character) {
        if (!Controls.isDownButtonPressed()) {
            return;
        }
        while (mapCollision.willHitBottomBoundary(character)) {
            character.addToSpeedDown(- 1);
        }
    }

    private void reduceSpeedRight (Character character) {
        if (!Controls.isRightButtonPressed()) {
            return;
        }
        while (mapCollision.willHitRightBoundary(character)) {
            character.addToSpeedRight(- 1);
        }
    }

    private void reduceSpeedLeft (Character character) {
        if (!Controls.isLeftButtonPressed()) {
            return;
        }
        while (mapCollision.willHitLeftBoundary(character)) {
            character.addToSpeedLeft(- 1);
        }
    }

    public void stopCharactersOutOfCamera(ArrayList<Character> characters, OrthographicCamera cam) {
        int leftX = (int) (cam.position.x - cam.viewportWidth / 2);
        int rightX = (int) (cam.position.x + cam.viewportWidth / 2);

        for (Character character : characters) {
            if (character.getX() < leftX + 20) {
                character.addToSpeedLeft(character.getSpeedLeft() * -1);
            } else if (character.getX() > rightX - 20) {
                character.addToSpeedRight(character.getSpeedRight() * -1);
            }
        }
    }

    //TODO Character collision together





}
