package com.bombing.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.bombing.World;

public class GameScreen implements Screen {
    private Game game;
    private World world;

    public GameScreen(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        world = new World();
    }

    @Override
    public void render(float delta) {
        world.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
