package com.mygdx.game;

import com.badlogic.gdx.Game;

public class Starter extends Game {

	
	@Override
	public void create () {
		startMenu();
	}

	public void startGame() {
		setScreen(new Level(this));
	}

	public void startMenu() {
		setScreen(new TitleScreen(this));
	}

	
	@Override
	public void dispose () {

	}
}
