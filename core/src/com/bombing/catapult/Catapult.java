package com.bombing.catapult;

import com.badlogic.gdx.math.Vector3;
import com.bombing.gameobjs.Cannonball;
import com.bombing.render.Renderable;

public interface Catapult extends Renderable {
    void stretch(float startX, float startY, float endX, float endY); // relational to screen size
    void release();
    void charge(Cannonball cannonball);
    Vector3 getInitialCannonballPos();
    boolean isCharged();
    boolean isStretching();
}
