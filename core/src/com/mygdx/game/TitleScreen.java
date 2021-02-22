package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class TitleScreen extends BasicScreen {
    private Texture background;
    private Texture playButtonTexture;
    private Texture playButtonPressedTexture;
    private Rectangle playButton;
    private Texture exitButtonTexture;
    private Texture exitButtonPressedTexture;
    private Rectangle exitButton;
    private boolean playButtonSelected;
    private boolean exitButtonSelected;
    private Starter mainGame;
    private Music musicStart;
    private Music musicLoop;
    private Sound hover;

    public TitleScreen(Starter mainGame) {
        super();
        background = new Texture ("background.png");
        playButtonTexture = new Texture("menuPlay_0.png");
        playButtonPressedTexture = new Texture ("menuPlay_1.png");
        exitButtonTexture = new Texture("menuExit_0.png");
        exitButtonPressedTexture = new Texture("menuExit_1.png");
        playButton = new Rectangle(background.getWidth() / 2 - playButtonTexture.getWidth() / 2, 230, playButtonTexture.getWidth(), playButtonTexture.getHeight());
        exitButton = new Rectangle(background.getWidth() / 2 - exitButtonTexture.getWidth() / 2, 160, playButtonTexture.getWidth(), playButtonTexture.getHeight());
        Gdx.graphics.setWindowedMode(background.getWidth(), background.getHeight());
        setupCamera();
        this.mainGame = mainGame;
        musicStart = Gdx.audio.newMusic(Gdx.files.internal("soundtrackTitlescreen.mp3"));
        musicLoop  = Gdx.audio.newMusic(Gdx.files.internal("soundtrackTitlescreenLoop.mp3"));
        musicStart.setLooping(false);
        musicStart.play();
        changeMusic();
        hover = Gdx.audio.newSound(Gdx.files.internal("buttonChange.mp3"));
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

        checkExitButtonSelected();
        checkPlayButtonSelected();



        spriteBatch.begin();
        spriteBatch.draw(background,0,0);
        if (!playButtonSelected) {
            spriteBatch.draw(playButtonTexture,playButton.getX(),playButton.getY());
        } else {
            spriteBatch.draw(playButtonPressedTexture,playButton.getX(),playButton.getY());
        }

        if (!exitButtonSelected) {
            spriteBatch.draw(exitButtonTexture,exitButton.getX(),exitButton.getY());
        } else {
            spriteBatch.draw(exitButtonPressedTexture,exitButton.getX(),exitButton.getY());
        }
        spriteBatch.end();
        checkExit();
        checkStartGame();
    }

    private void changeMusic() {
        musicStart.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                musicLoop.setLooping(true);
                musicLoop.play();
            }
        });
    }

    @Override
    public void show() {
        Gdx.graphics.setWindowedMode(background.getWidth(), background.getHeight());
    }

    private void checkPlayButtonSelected() {
        if (!playButtonSelected) {
            if (playButton.contains(mouseCoordinate.getX(), mouseCoordinate.getY())) {
                hover.play();
            }
        }
        playButtonSelected = playButton.contains(mouseCoordinate.getX(), mouseCoordinate.getY());
    }

    private void checkExitButtonSelected() {
        if (!exitButtonSelected) {
            if (exitButton.contains(mouseCoordinate.getX(), mouseCoordinate.getY())) {
                hover.play();
            }
        }
        exitButtonSelected = exitButton.contains(mouseCoordinate.getX(), mouseCoordinate.getY());
    }

    private void checkStartGame() {
        if (!playButtonSelected) {
            return;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            musicStart.stop();
            musicLoop.stop();
            mainGame.startGame();
        }
    }

    private void checkExit() {
        if (!exitButtonSelected) {
            return;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            musicStart.stop();
            musicLoop.stop();
            Gdx.app.exit();
        }
    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {
        musicStart.dispose();
        musicLoop.dispose();
        playButtonTexture.dispose();
        playButtonPressedTexture.dispose();
        exitButtonTexture.dispose();
        exitButtonPressedTexture.dispose();
        background.dispose();
        hover.dispose();
    }
}
