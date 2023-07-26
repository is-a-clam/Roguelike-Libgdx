package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.game.Screens.RoomScreen;

public class MyGame extends Game {
	SpriteBatch batch;
	Screen currentScreen;

	public SpriteBatch getBatch()
	{
		return batch;
	}

	@Override
	public void create () {
		Room.initialize();

		batch = new SpriteBatch();

		Room room = new Room(RoomType.ENEMY, true, true, true, true);

		currentScreen = new RoomScreen(this, room);
		setScreen(currentScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
