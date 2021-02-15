package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;



public class Starter extends Game {
	Texture img;
	float stateTime;

	
	@Override
	public void create () {
		this.setScreen(new Level());
	}

	
	@Override
	public void dispose () {
		img.dispose();
	}
}
