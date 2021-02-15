package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class BasicScreen extends ScreenAdapter {
    protected SpriteBatch spriteBatch;
    private Vector3 mouseVector3;
    protected Coordinate mouseCoordinate;
    protected OrthographicCamera camera;
    private float stateTime;

    public BasicScreen() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        mouseVector3 = new Vector3();
        mouseCoordinate = new Coordinate(0, 0);
        camera = new OrthographicCamera();
    }

    protected void clearScreen() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected void updateView() {
        spriteBatch.setProjectionMatrix(camera.combined);
        camera.update();
    }

    protected void updateStateTime() {
         stateTime += Gdx.graphics.getDeltaTime();
    }

    protected float getStateTime() {
        return stateTime;
    }

    protected void updateMouseCoordinate() {
        mouseVector3.x = Gdx.input.getX();
        mouseVector3.y = Gdx.input.getY();
        camera.unproject(mouseVector3);
        mouseCoordinate.setX(mouseVector3.x);
        mouseCoordinate.setY(mouseVector3.y);
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
