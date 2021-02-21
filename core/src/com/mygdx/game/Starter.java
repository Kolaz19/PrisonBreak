package com.mygdx.game;

import com.badlogic.gdx.Game;

public class Starter extends Game {
	private TitleScreen mainMenu;

	
	@Override
	public void create () {
		mainMenu = new TitleScreen(this);
		startMenu();
	}

	public void startGame() {
		setScreen(new Level(this));
	}

	public void startMenu() {
		setScreen(mainMenu);
	}

	
	@Override
	public void dispose () {

	}
}
