package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Level extends BasicScreen {
    Map map;



    Level() {
        map = new Map("Level3.tmx");
        setupCamera();
        spriteBatch = new SpriteBatch();
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

        map.render("Default");

        spriteBatch.begin();
        spriteBatch.end();
    }

    @Override
    public void show() {
        super.show();
    }


}
