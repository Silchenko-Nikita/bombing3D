package com.bombing.catapult;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.bombing.gameobjs.Cannonball;
import com.bombing.utils.observer.Observable;


// Proxy
public class CatapultProxy extends Observable implements Catapult {
    private CatapultImpl catapult = new CatapultImpl();

    @Override
    public void stretch(float startX, float startY, float endX, float endY) {
        if (!catapult.isCharged() || !catapult.stretchStartCoordsValid(startX, startY)) return;

        catapult.stretch(startX, startY, endX, endY);
    }

    @Override
    public void release() {
        if (!catapult.isCharged() || !catapult.isStretching()) return;

        notifyObservers("catapult released", catapult.cannonball);
        catapult.release();
    }

    @Override
    public void charge(Cannonball cannonball) {
        if (catapult.isCharged()) return;

        catapult.charge(cannonball);
    }

    @Override
    public Vector3 getInitialCannonballPos() {
        return catapult.getInitialCannonballPos();
    }

    @Override
    public void render(ModelBatch batch, Environment environment) {
        catapult.render(batch, environment);
    }

    @Override
    public boolean isCharged() {
        return catapult.isCharged();
    }

    @Override
    public boolean isStretching() {
        return catapult.isStretching();
    }
}
