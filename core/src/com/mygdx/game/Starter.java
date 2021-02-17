package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;



public class Starter extends Game {

	
	@Override
	public void create () {
		this.setScreen(new Level());
	}

	
	@Override
	public void dispose () {

	}
}
