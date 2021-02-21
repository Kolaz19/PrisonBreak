package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Collision {
    private MapCollision mapCollision;

    public Collision (MapCollision mapCollision) {
        this.mapCollision = mapCollision;
    }

    public void reduceSpeedThroughCollision (Character character) {
        if (!character.isFree()) {
            mapCollision.willHitFreeTile(character);
        }
        if (character.isFree()) {
            return;
        }
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
            character.addToSpeedUp(- 0.1f);
        }
    }

    private void reduceSpeedDown (Character character) {
        if (!Controls.isDownButtonPressed()) {
            return;
        }
        while (mapCollision.willHitBottomBoundary(character)) {
            character.addToSpeedDown(- 0.1f);
        }
    }

    private void reduceSpeedRight (Character character) {
        if (!Controls.isRightButtonPressed()) {
            return;
        }
        while (mapCollision.willHitRightBoundary(character)) {
            character.addToSpeedRight(- 0.1f);
        }
    }

    private void reduceSpeedLeft (Character character) {
        if (!Controls.isLeftButtonPressed()) {
            return;
        }
        while (mapCollision.willHitLeftBoundary(character)) {
            character.addToSpeedLeft(- 0.1f);
        }
    }

    public void stopCharactersOutOfCamera(ArrayList<Character> characters, OrthographicCamera cam) {
        int leftX = (int) (cam.position.x - cam.viewportWidth / 2);
        int rightX = (int) (cam.position.x + cam.viewportWidth / 2);

        for (Character character : characters) {
            if (character.getX() < leftX + 20) {
                character.addToSpeedLeft(character.getSpeedLeft() * - 1);
            } else if (character.getX() > rightX - 20) {
                character.addToSpeedRight(character.getSpeedRight() * -1);
            }
        }
    }

    public void checkPressedButtons(ArrayList<Character> characters, ArrayList<Button> buttons) {
        for (Button button : buttons) {
            boolean isPressed = false;
            for (Character character : characters) {
                if (button.getHitbox().overlaps(character.getHitbox())) {
                    isPressed = true;
                }
            }
            button.setPressed(isPressed);
        }
    }

    public void checkBlockedDoors(ArrayList<Character> characters, ArrayList<Door> doors) {
        for (Character character : characters) {
            for (Door door : doors) {
                if (door.isOpen()) {
                    continue;
                }

                Coordinate cordToHit = new Coordinate(character.getX() + character.getWidth() / 2, character.getY() + 7);
                if (Controls.isUpButtonPressed()) {
                    if (door.getHitbox().contains(cordToHit.getX(),cordToHit.getY())) {
                        character.addToSpeedUp(character.getSpeedUp() * -1);
                    }
                }

                cordToHit.setY(character.getY() - 1);
                if (Controls.isDownButtonPressed()) {
                    if (door.getHitbox().contains(cordToHit.getX(),cordToHit.getY())) {
                        character.addToSpeedDown(character.getSpeedUp() * -1);
                    }
                }

            }
        }
    }

    public void characterToCharacterCollision(ArrayList<Character> characters) {
        Rectangle rectToHit = new Rectangle(characters.get(0).getHitbox().getX(),characters.get(0).getHitbox().getY(),characters.get(0).getHitbox().getWidth(),characters.get(0).getHitbox().getHeight());
        for (Character charOut : characters) {
            for (Character charIn : characters) {
                if (charOut != charIn) {

                    if (Controls.isUpButtonPressed()) {
                      rectToHit.x = charOut.getHitbox().getX();
                      rectToHit.y = charOut.getHitbox().getY() + charOut.getSpeedUp();
                      if (charIn.getHitbox().overlaps(rectToHit)) {
                          charOut.addToSpeedUp(charOut.getSpeedUp() * - 1);
                      }
                    }

                    if (Controls.isDownButtonPressed()) {
                        rectToHit.x = charOut.getHitbox().getX();
                        rectToHit.y = charOut.getHitbox().getY() - charOut.getSpeedDown();
                        if (charIn.getHitbox().overlaps(rectToHit)) {
                            charOut.addToSpeedDown(charOut.getSpeedDown() * - 1);
                        }
                    }

                    if (Controls.isRightButtonPressed()) {
                        rectToHit.x = charOut.getHitbox().getX() + charOut.getSpeedRight();
                        rectToHit.y = charOut.getHitbox().getY();
                        if (charIn.getHitbox().overlaps(rectToHit)) {
                            charOut.addToSpeedRight(charOut.getSpeedRight() * - 1);
                        }
                    }

                    if (Controls.isLeftButtonPressed()) {
                        rectToHit.x = charOut.getHitbox().getX() - charOut.getSpeedLeft();
                        rectToHit.y = charOut.getHitbox().getY();
                        if (charIn.getHitbox().overlaps(rectToHit)) {
                            charOut.addToSpeedLeft(charOut.getSpeedLeft() * - 1);
                        }
                    }


                }
            }
        }
    }


}
