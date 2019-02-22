package com.bombing;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.bombing.field.Field;
import com.bombing.field.FieldLoader;
import com.bombing.gameobjs.Box;
import com.bombing.screens.GameScreen;

public class Main extends Game {

	@Override
	public void create () {
		this.setScreen(new GameScreen(this));
	}
}
