package com.bombing.gameobjs;

import com.bombing.builders.gameobjs.BoxBuilder;


public class Box extends RangeObject {
    public static final float cellFraction = 1.0f;
    public static final float mass = 1.0f;

    public Box() {
        builder = new BoxBuilder();
        build();
    }
}
