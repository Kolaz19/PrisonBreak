package com.mygdx.game;

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





}
