package com.mygdx.game;

import com.badlogic.gdx.Audio;
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
    CameraManager camManager;
    TextureAtlas atlas;
    CharacterAnimation charAnimation1;
    Music track1;


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
        addCharacters();
        addButtons();
        //Cam
        camManager = new CameraManager(camera,map);
        //Music
        track1 = Gdx.audio.newMusic(Gdx.files.internal("soundtrack1Loop.mp3"));
        track1.setVolume(0.2f);
        //track1.play();
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
        moveCharacters();


        map.render("Default");
        camManager.adjustCamera(characters);


        spriteBatch.begin();
        for (Button button : buttons) {
            spriteBatch.draw(button.getCorrectTexture(),button.getX(),button.getY());
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

        Character character1 = new Character(3,16,15,6, 8,10, stepSound);
        characters.add(character1);
        Character character2 = new Character (3, 19,15,6, 8,10, stepSound);
        characters.add(character2);
        Character character3 = new Character (3, 14,15,6, 8, 10, stepSound);
        characters.add(character3);
    }

    private void addButtons() {

        Sound buttonInSound = Gdx.audio.newSound(Gdx.files.internal("TriggerIn.mp3"));
        Sound buttonOutSound = Gdx.audio.newSound(Gdx.files.internal("TriggerOut.mp3"));

        TextureRegion buttonUp = atlas.findRegion("button",0);
        TextureRegion buttonDown = atlas.findRegion("button",1);
        buttons.add(new Button(buttonUp,buttonDown,59,5,1,buttonInSound, buttonOutSound));
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
