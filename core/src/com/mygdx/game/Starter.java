package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;


public class Starter extends Game {
	SpriteBatch batch;
	Texture img;
	Animation<TextureRegion> animation;
	float stateTime;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("characterIdle.png");
		TextureAtlas atlas = new TextureAtlas("Deep.atlas");
		animation = new Animation<TextureRegion>(0.2f,atlas.findRegions("characterRunning"), Animation.PlayMode.LOOP);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		stateTime += Gdx.graphics.getDeltaTime();

		batch.begin();
		batch.draw(animation.getKeyFrame(stateTime),5,5,50,50);
		batch.end();
		}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
