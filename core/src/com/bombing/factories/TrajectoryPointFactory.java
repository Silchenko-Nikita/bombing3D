package com.bombing.factories;

import com.bombing.render.TrajectoryPoint;

public class TrajectoryPointFactory {
    private static TrajectoryPoint trajectoryPoint = TrajectoryPoint.getInstance();

    public static TrajectoryPoint getTrajectoryPoint() {
        return trajectoryPoint;
    }
}
