package com.mygdx.game;

import java.util.ArrayList;

public class DoorOpener {
    private ArrayList<Button> buttons;
    private ArrayList<Door>  doors;

    public DoorOpener(ArrayList<Button> buttons, ArrayList<Door>  doors) {
        this.buttons = buttons;
        this.doors = doors;
    }

    public void checkForDoorsToOpen() {
        for (Door door : doors) {
            boolean shouldOpen = true;
            for (Button button : buttons) {
                if (button.getGroup() == door.getGroup() && !button.isPressed()) {
                    shouldOpen = false;
                }
            }
            if(shouldOpen) {
                door.shouldOpen();
            }
        }
    }

    public void setCharacterFree(ArrayList<Character> characters) {
        for (Character character : characters) {
            for (Door door : doors) {
                if (door.getGroup() == character.getGroup()) {
                    if (door.isOpen()) {
                        character.setTrapped(false);
                    }
                }
            }
        }
    }

}
