package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;


public class Level extends BasicScreen {
    Map map;
    Texture texture;
    Collision collisionManager;
    ArrayList<Character> characters;
    Texture text;



    Level() {
        map = new Map("map.tmx");
        setupCamera();
        spriteBatch = new SpriteBatch();
        texture = new Texture ("characterIdle.png");
        //Collision
        MapCollision mapCollision = new MapCollision(map,10,10);
        collisionManager = new Collision(mapCollision);
        //Characters
        characters = new ArrayList<>();
        addCharacters();
        text = new Texture("characterIdle.png");
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
        this.updateView();
        this.updateMouseCoordinate();
        this.updateStateTime();
        map.setView(camera);
        Controls.updateDiagonal();
        //Logic Characters
        for (Character entity: characters) {
            entity.resetState(Controls.diagonal);
        }
        adjustSpeedOfCharacters();
        moveCharacters();

        map.render("Default");

        spriteBatch.begin();
        for (Character entity : characters) {
            spriteBatch.draw(text,entity.getX(),entity.getY());
        }
        spriteBatch.end();
        map.render("Second");
    }

    @Override
    public void show() {
        super.show();
    }

    private void addCharacters() {
        Character character1 = new Character(59*16,9*16,15,6);
        characters.add(character1);
    }

    private void adjustSpeedOfCharacters () {
        for (Character entity: characters) {
            collisionManager.reduceSpeedThroughCollision(entity);
        }
    }

    private void moveCharacters() {
        for (Character entity: characters) {
            if (Controls.isUpButtonPressed()) {
                entity.moveUp();
            }
            if (Controls.isDownButtonPressed()) {
                entity.moveDown();
            }
            if (Controls.isRightButtonPressed()) {
                entity.moveRight();
            }
            if (Controls.isLeftButtonPressed()) {
                entity.moveLeft();
            }
        }
    }

}
