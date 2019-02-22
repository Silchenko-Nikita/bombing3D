package com.bombing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.bombing.catapult.Catapult;

public class GameInputProcessor implements InputProcessor {
    private boolean dragging;
    private float startX, startY;
    private Catapult catapult;

    public GameInputProcessor(Catapult catapult){
        this.catapult = catapult;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        startX = screenX;
        startY = screenY;
        dragging = true;

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        dragging = false;

        catapult.release();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        float normStartX = startX / screenWidth;
        float normStartY = startY / screenHeight;

        float normEndX = Math.min(screenX, screenWidth) / screenWidth;
        float normEndY = Math.min(screenY, screenHeight) / screenHeight;

        catapult.stretch(normStartX, normStartY, normEndX, normEndY);

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
