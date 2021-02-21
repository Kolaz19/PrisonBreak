package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
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
    CharacterAnimation charAnimation2;
    CharacterAnimation charAnimation3;
    CharacterAnimation charAnimation4;
    CharacterAnimation charAnimation5;
    MusicManager musicManager;
    Starter mainGame;
    Sound doorOpenSound;
    Sound stepSound1;
    Sound stepSound2;
    Sound buttonInSound;
    Sound buttonOutSound;


    Level(Starter mainGame) {
        super();
        this.mainGame = mainGame;
        map = new Map("map.tmx");
        setupCamera();
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
        musicManager.playCorrectTrack(doors);


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
        checkEnding();
    }

    @Override
    public void show() {
        super.show();
        if (!Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode())) {
            Gdx.graphics.setWindowedMode(1920,1080);
        }
    }

    private void addCharacters() {
        charAnimation1 = new CharacterAnimation();
        charAnimation1.addIdleAnimation(new Animation<TextureRegion>(0.3f,atlas.findRegions("char1Idle"), Animation.PlayMode.LOOP));
        charAnimation1.addMoveLeftAnimation(new Animation<TextureRegion>(0.1f,atlas.findRegions("char1RunningLeft"), Animation.PlayMode.LOOP));
        charAnimation1.addMoveRightAnimation(new Animation<TextureRegion>(0.1f,atlas.findRegions("char1RunningRight"), Animation.PlayMode.LOOP));

        stepSound1 = Gdx.audio.newSound(Gdx.files.internal("step1.mp3"));
        stepSound2 = Gdx.audio.newSound(Gdx.files.internal("step2.mp3"));

        Character character1 = new Character(8,16,15,6, 8,10, 1, stepSound1, stepSound2);
        character1.setTrapped(false);
        characters.add(character1);

      Character character2 = new Character (14, 18,15,6, 8,10, 2, stepSound1, stepSound2);
        characters.add(character2);

        Character character3 = new Character (51, 21,15,6, 8,10, 4, stepSound1, stepSound2);
        characters.add(character3);

        Character character4 = new Character (146, 7,15,6, 8,10, 6, stepSound1, stepSound2);
        characters.add(character4);

        Character character5 = new Character (89, 21,15,6, 8,10, 7, stepSound1, stepSound2);
        characters.add(character5);

    }

    private void addMusic() {
        musicManager.addTrack(Gdx.audio.newMusic(Gdx.files.internal("soundtrack1.mp3")),1);
        musicManager.addTrack(Gdx.audio.newMusic(Gdx.files.internal("soundtrack1Loop.mp3")),2);
        musicManager.addTrack(Gdx.audio.newMusic(Gdx.files.internal("soundtrack2.mp3")),3);
        musicManager.addTrack(Gdx.audio.newMusic(Gdx.files.internal("soundtrack2Loop.mp3")),4);
        musicManager.addTrack(Gdx.audio.newMusic(Gdx.files.internal("soundtrack3.mp3")),5);
        musicManager.addTrack(Gdx.audio.newMusic(Gdx.files.internal("soundtrack3Loop.mp3")),6);
    }

    private void addButtons() {
        buttonInSound = Gdx.audio.newSound(Gdx.files.internal("TriggerIn.mp3"));
        buttonOutSound = Gdx.audio.newSound(Gdx.files.internal("TriggerOut.mp3"));

        TextureRegion buttonUp = atlas.findRegion("button",0);
        TextureRegion buttonDown = atlas.findRegion("button",1);
        buttons.add(new Button(buttonUp,buttonDown,2,17,1,buttonInSound, buttonOutSound));

        buttons.add(new Button(buttonUp,buttonDown,2,12,2,buttonInSound, buttonOutSound));

        buttons.add(new Button(buttonUp,buttonDown,32,15,3,buttonInSound, buttonOutSound));
        buttons.add(new Button(buttonUp,buttonDown,28,7,3,buttonInSound, buttonOutSound));

        buttons.add(new Button(buttonUp,buttonDown,48,8,4,buttonInSound, buttonOutSound));
        buttons.add(new Button(buttonUp,buttonDown,51,3,4,buttonInSound, buttonOutSound));

        buttons.add(new Button(buttonUp,buttonDown,58,11,5,buttonInSound, buttonOutSound));
        buttons.add(new Button(buttonUp,buttonDown,68,18,5,buttonInSound, buttonOutSound));
        buttons.add(new Button(buttonUp,buttonDown,73,6,5,buttonInSound, buttonOutSound));

        buttons.add(new Button(buttonUp,buttonDown,123,15,6,buttonInSound, buttonOutSound));
        buttons.add(new Button(buttonUp,buttonDown,128,5,6,buttonInSound, buttonOutSound));
        buttons.add(new Button(buttonUp,buttonDown,132,13,6,buttonInSound, buttonOutSound));

        buttons.add(new Button(buttonUp,buttonDown,83,16,7,buttonInSound, buttonOutSound));
        buttons.add(new Button(buttonUp,buttonDown,91,10,7,buttonInSound, buttonOutSound));
        buttons.add(new Button(buttonUp,buttonDown,101,19,7,buttonInSound, buttonOutSound));
        buttons.add(new Button(buttonUp,buttonDown,111,9,7,buttonInSound, buttonOutSound));
    }

    private void addDoors() {
        doorOpenSound = Gdx.audio.newSound(Gdx.files.internal("cellOpen.mp3"));
        Animation<TextureRegion> doorOpen =  new Animation<TextureRegion>(0.3f,atlas.findRegions("door"), Animation.PlayMode.NORMAL);
        doors.add(new Door(7,11,doorOpen, 1, doorOpenSound));

        doors.add(new Door(14,13,doorOpen, 2, doorOpenSound));

        doors.add(new Door(40,13,doorOpen, 3, doorOpenSound));

        doors.add(new Door(56,18,doorOpen, 4, doorOpenSound));

        doors.add(new Door(77,9,doorOpen, 5, doorOpenSound));

        doors.add(new Door(146,3,doorOpen, 6, doorOpenSound));
        doors.add(new Door(141,11,doorOpen, 6, doorOpenSound));

        doors.add(new Door(89,18,doorOpen, 7, doorOpenSound));
        doors.add(new Door(159,12,doorOpen, 7, doorOpenSound));
    }

    private void adjustSpeedOfCharacters () {
        for (Character entity: characters) {
            collisionManager.reduceSpeedThroughCollision(entity);
        }
        collisionManager.stopCharactersOutOfCamera(characters,camera);
        collisionManager.characterToCharacterCollision(characters);
    }

    private void checkEnding() {
        if (Controls.isExitButtonPressed()) {
            //mainGame.startMenu();
            Gdx.app.exit();
        }
    }

    private void moveCharacters() {
        boolean playSound = false;

        for (Character entity: characters) {
            if (Controls.isUpButtonPressed()) {
                entity.moveUp();
                playSound = true;
            }
            if (Controls.isDownButtonPressed() || entity.isFree()) {
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

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {
        atlas.dispose();
        map.dispose();
        doorOpenSound.dispose();
        stepSound1.dispose();
        stepSound2.dispose();
        buttonInSound.dispose();
        buttonOutSound.dispose();
        musicManager.dispose();
        //spriteBatch.dispose();
    }
}
