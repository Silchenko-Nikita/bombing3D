package com.bombing.gameobjs;

import com.bombing.builders.gameobjs.GroundBuilder;
import com.bombing.field.Field;

public class Ground extends GameObject {
    public static final int width = Field.width * 3; // cells
    public static final int height = 1; // cells
    public static final int depth = Field.depth * 3; // cells
    public static final float mass = 0.0f;

    public Ground() {
        builder = new GroundBuilder();
        build();
    }
}
