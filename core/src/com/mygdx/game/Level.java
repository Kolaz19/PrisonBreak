package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;


public class Level extends BasicScreen {
    Map map;
    Collision collisionManager;
    ArrayList<Character> characters;
    ArrayList<Button> buttons;
    ArrayList<Door> doors;
    CameraManager camManager;
    DoorOpener doorManager;
    TextureAtlas atlas;
    CharacterAnimation charAnimation1;
    MusicManager musicManager;


    Level() {
        map = new Map("map.tmx");
        setupCamera();
        spriteBatch = new SpriteBatch();
        //Collision
        MapCollision mapCollision = new MapCollision(map,10,10);
        collisionManager = new Collision(mapCollision);
        //Textures
        atlas = new TextureAtlas("StrongerTogether.atlas");
        //Characters
        characters = new ArrayList<>();
        buttons = new ArrayList<>();
        doors = new ArrayList<>();
        addCharacters();
        addButtons();
        addDoors();
        //Cam
        camManager = new CameraManager(camera,map);
        //DoorManager
        doorManager = new DoorOpener(buttons,doors);
        //Music
        musicManager = new MusicManager();
        addMusic();
    }


    @Override
    protected void setupCamera() {
        camera.viewportWidth = map.getMapHeight("Default") * 1.56f;
        camera.viewportHeight = map.getMapHeight("Default") ;
        camera.position.x = map.getMapWidth("Default") / 2;
        camera.position.y = map.getMapHeight("Default") / 2;
    }

    @Override
    public void render(float delta) {
        //Basic preparation for rendering

        this.clearScreen();
        this.updateMouseCoordinate();
        this.updateStateTime();
        this.updateView();
        map.setView(camera);


        Controls.updateDiagonal();
        //Logic Characters
        for (Character entity: characters) {
            entity.resetState(Controls.diagonal);
        }
        adjustSpeedOfCharacters();
        collisionManager.checkPressedButtons(characters,buttons);
        collisionManager.checkBlockedDoors(characters,doors);
        doorManager.checkForDoorsToOpen();
        doorManager.setCharacterFree(characters);
        moveCharacters();


        map.render("Default");
        camManager.adjustCamera(characters);


        spriteBatch.begin();
        for (Button button : buttons) {
            spriteBatch.draw(button.getCorrectTexture(),button.getX(),button.getY());
        }
        for (Door door : doors) {
            spriteBatch.draw(door.getCorrectFrame(),door.getX(),door.getY());
        }
        for (Character entity : characters) {
            spriteBatch.draw(charAnimation1.getRightAnimation(entity.getState(),getStateTime()),entity.getX(),entity.getY());
        }
        spriteBatch.end();
        map.render("Second");
    }

    @Override
    public void show() {
        super.show();
    }

    private void addCharacters() {
        charAnimation1 = new CharacterAnimation();
        charAnimation1.addIdleAnimation(new Animation<TextureRegion>(0.3f,atlas.findRegions("char1Idle"), Animation.PlayMode.LOOP));
        charAnimation1.addMoveLeftAnimation(new Animation<TextureRegion>(0.1f,atlas.findRegions("char1RunningLeft"), Animation.PlayMode.LOOP));
        charAnimation1.addMoveRightAnimation(new Animation<TextureRegion>(0.1f,atlas.findRegions("char1RunningRight"), Animation.PlayMode.LOOP));

        Sound stepSound = Gdx.audio.newSound(Gdx.files.internal("step2.mp3"));

        Character character1 = new Character(3,16,15,6, 8,10, 0, stepSound);
        character1.setTrapped(false);
        characters.add(character1);
        Character character2 = new Character (3, 19,15,6, 8,10, 0, stepSound);
        character2.setTrapped(false);
        characters.add(character2);
        Character character3 = new Character (11, 21,15,6, 8, 10, 1, stepSound);
        character3.setTrapped(true);
        characters.add(character3);
    }

    private void addMusic() {
        musicManager.addTrack(Gdx.audio.newMusic(Gdx.files.internal("soundtrack1Loop.mp3")),1);
    }

    private void addButtons() {
        Sound buttonInSound = Gdx.audio.newSound(Gdx.files.internal("TriggerIn.mp3"));
        Sound buttonOutSound = Gdx.audio.newSound(Gdx.files.internal("TriggerOut.mp3"));

        TextureRegion buttonUp = atlas.findRegion("button",0);
        TextureRegion buttonDown = atlas.findRegion("button",1);
        buttons.add(new Button(buttonUp,buttonDown,6,13,1,buttonInSound, buttonOutSound));
        buttons.add(new Button(buttonUp,buttonDown,20,16,1,buttonInSound, buttonOutSound));
    }

    private void addDoors() {
        Animation<TextureRegion> doorOpen =  new Animation<TextureRegion>(0.3f,atlas.findRegions("door"), Animation.PlayMode.NORMAL);
        doors.add(new Door(11,19,doorOpen, 1));
    }

    private void adjustSpeedOfCharacters () {
        for (Character entity: characters) {
            collisionManager.reduceSpeedThroughCollision(entity);
        }
        collisionManager.stopCharactersOutOfCamera(characters,camera);
        collisionManager.characterToCharacterCollision(characters);
    }

    private void moveCharacters() {
        boolean playSound = false;

        for (Character entity: characters) {
            if (Controls.isUpButtonPressed()) {
                entity.moveUp();
                playSound = true;
            }
            if (Controls.isDownButtonPressed()) {
                entity.moveDown();
                playSound = true;
            }
            if (Controls.isRightButtonPressed()) {
                entity.moveRight();
                playSound = true;
            }
            if (Controls.isLeftButtonPressed()) {
                entity.moveLeft();
                playSound = true;
            }
            if (playSound) {
                entity.playSound();
            }
        }
    }

}
