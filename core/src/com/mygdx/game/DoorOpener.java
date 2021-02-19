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
            door.shouldOpen(shouldOpen);
        }
    }

}
