package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class BasicScreen extends ScreenAdapter {
    protected SpriteBatch spriteBatch;
    protected Coordinate mouseCoordinate;
    protected OrthographicCamera camera;
    private float stateTime;

    public BasicScreen() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        mouseCoordinate = new Coordinate(0, 0);
    }

    protected void clearScreen() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected void updateView() {
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

    }

    protected void updateStateTime() {
         stateTime += Gdx.graphics.getDeltaTime();
    }

    public float getStateTime() {
        return stateTime;
    }

    protected void updateMouseCoordinate() {
        Vector3 mousePos = new Vector3();
        mousePos.x = Gdx.input.getX();
        mousePos.y = Gdx.input.getY();
        camera.unproject(mousePos);
        mouseCoordinate.setX(mousePos.x);
        mouseCoordinate.setY(mousePos.y);
    }

    protected abstract void setupCamera();

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
