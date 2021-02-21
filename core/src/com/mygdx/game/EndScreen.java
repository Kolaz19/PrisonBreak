package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;


public class EndScreen extends BasicScreen {
    private Texture background;
    private Starter mainGame;
    private Music musicLoop;



    public EndScreen(Starter mainGame) {
        super();
        background = new Texture ("endscreen.png");
        Gdx.graphics.setWindowedMode(background.getWidth(), background.getHeight());
        setupCamera();
        this.mainGame = mainGame;
        musicLoop  = Gdx.audio.newMusic(Gdx.files.internal("soundtrack3Loop.mp3"));
        musicLoop.setLooping(true);
        musicLoop.setVolume(0.5f);
        musicLoop.play();
    }


    @Override
    protected void setupCamera() {
        camera.viewportWidth = background.getWidth();
        camera.viewportHeight = background.getHeight();
        camera.position.x = camera.viewportWidth / 2;
        camera.position.y = camera.viewportHeight / 2;
    }

    @Override
    public void render(float delta) {
        this.clearScreen();
        this.updateMouseCoordinate();
        this.updateStateTime();
        this.updateView();




        spriteBatch.begin();
        spriteBatch.draw(background,0,0);
        spriteBatch.end();
        checkExit();
    }



    @Override
    public void show() {
        Gdx.graphics.setWindowedMode(background.getWidth(), background.getHeight());
    }

    private void checkExit() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            dispose();
            dispose();
            Gdx.app.exit();
        }
    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        background.dispose();
        musicLoop.stop();
        musicLoop.dispose();
    }
}