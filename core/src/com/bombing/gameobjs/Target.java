package com.bombing.gameobjs;

import com.bombing.builders.gameobjs.TargetBuilder;

public class Target extends RangeObject {
    public static final float cellFraction = 0.8f;
    public static final float mass = 1.0f;

    public Target() {
        builder = new TargetBuilder();
        build();
    }

}
