package com.bombing.gameobjs;

import com.badlogic.gdx.math.Vector3;
import com.bombing.builders.gameobjs.CannonballBuilder;

public class Cannonball extends GameObject {
    public static final float cellFraction = 0.6f;
    public static final float mass = 50.0f;
    public boolean isShot = false;
    public boolean landed = false;

    public Cannonball() {
        builder = new CannonballBuilder();
        build();
    }
}
