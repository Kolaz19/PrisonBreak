package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

public class CameraManager {
    private OrthographicCamera cam;
    private Map map;

    public CameraManager(OrthographicCamera camera, Map map) {
        this.cam = camera;
        this.map = map;
    }

    public void adjustCamera(ArrayList<Character> characters) {
        int minX = (int) characters.get(0).getX();
        int maxX = (int) characters.get(0).getX();
        for (Character character : characters) {
            if (character.getX() < minX) {
                minX = (int) character.getX();
            } else if (character.getX() > maxX) {
                maxX = (int) character.getX();
            }
        }

        int xAverage = ( maxX + minX ) / 2 + ( characters.get(0).getWidth() / 2 );
        if (isOverLeftTolerance(xAverage)) {
            cam.position.x = cam.viewportWidth / 2;
        } else if (isOverRightTolerance(xAverage)) {
            cam.position.x = map.getMapWidth("Default") - cam.viewportWidth / 2;
        } else {
            cam.position.x = xAverage;
        }
    }

    private boolean isOverLeftTolerance (int positionX) {
        return positionX <= 0 + cam.viewportWidth / 2;
    }

    private boolean isOverRightTolerance (int positionX) {
        return positionX >= map.getMapWidth("Default") - cam.viewportWidth / 2;
    }

    private int getLeftX() {
        return (int) (cam.position.x - cam.viewportWidth / 2);
    }

    private int getRightX() {
        return (int) (cam.position.x + cam.viewportWidth / 2);
    }

}
