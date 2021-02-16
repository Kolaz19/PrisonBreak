package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Level extends BasicScreen {
    Map map;
    MapCollision mapCollision;
    Character character;
    Texture texture;



    Level() {
        map = new Map("Level3.tmx");
        setupCamera();
        spriteBatch = new SpriteBatch();
        mapCollision = new MapCollision(map,10,10);
        character = new Character(20,20,20,5);
        texture = new Texture ("characterIdle.png");
    }


    @Override
    protected void setupCamera() {
        camera.viewportWidth = 1920 * map.getMapWidth("Default") / 1080;
        camera.viewportHeight = map.getMapHeight("Default") ;
        camera.position.x = map.getMapWidth("Default") / 2;
        camera.position.y = map.getMapHeight("Default") / 2;
    }

    @Override
    public void render(float delta) {
        this.clearScreen();
        this.updateView();
        this.updateMouseCoordinate();
        this.updateStateTime();
        map.setView(camera);
        Controls.updateDiagonal();
        character.resetState(Controls.diagonal);
        moveCharacters();


        map.render("Default");

        spriteBatch.begin();
        spriteBatch.draw(texture,character.getX(),character.getY());
        spriteBatch.end();
    }

    @Override
    public void show() {
        super.show();
    }

    private void moveCharacters() {

    }

}
