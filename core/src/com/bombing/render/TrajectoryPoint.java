package com.bombing.render;

import com.bombing.builders.TrajectoryPointBuilder;


// Singleton, Lightweight
public class TrajectoryPoint extends RenderableObject {
    public static final float cellFraction = 0.2f;
    private static TrajectoryPoint instance;

    private TrajectoryPoint() {
        builder = new TrajectoryPointBuilder();
        build();
    }

    public static TrajectoryPoint getInstance(){
        if (instance == null){
            instance = new TrajectoryPoint();
            return instance;
        }
        return null;
    }
}
