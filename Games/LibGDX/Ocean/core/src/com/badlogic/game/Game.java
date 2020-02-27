package com.badlogic.game;

import com.badlogic.game.map.TileMap;
import com.badlogic.game.map.TileRoom;
import com.badlogic.game.object.MapObject;
import com.badlogic.game.object.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {

	OrthographicCamera camera;
	SpriteBatch batch;

	TileMap tileMap;
	TileRoom tileRoom;

	Player player;
	
	@Override
	public void create () {

		Gdx.graphics.setWindowedMode(1280, 960);
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true);
		batch = new SpriteBatch();

		tileMap = new TileMap("maps/test.txt", 16, "437f80", 6);
		for (int i = 0; i < 10; i++) {
			tileRoom = new TileRoom();
			tileMap.add(tileRoom.map);
		}

		player = new Player(tileMap, new Texture(Gdx.files.internal("graphics/sprites/nudibranch.png")), 32, 32, 14, 10);

	}

	@Override
	public void render () {

		camera.translate(1, 0);
		camera.update();

		Gdx.gl.glClearColor(.02f, .04f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		tileMap.xOff += 0;
		tileMap.update();
		player.update();

		batch.begin();
		tileMap.draw(batch);
		player.draw(batch);
		batch.end();

		batch.setProjectionMatrix(camera.combined);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
