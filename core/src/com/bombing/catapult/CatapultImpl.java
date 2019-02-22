package com.bombing.catapult;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.bombing.Globals;
import com.bombing.factories.TrajectoryPointFactory;
import com.bombing.field.Field;
import com.bombing.gameobjs.Cannonball;
import com.bombing.physics.Physics;
import com.bombing.render.RenderableObject;
import com.bombing.render.TrajectoryPoint;

import static com.bombing.physics.Physics.timeStep;

public class CatapultImpl extends RenderableObject implements Catapult {
    static final float planeAngle = (float) Math.PI / 4;
    static final float planeAngleTangent = (float) Math.tan(planeAngle);
    private final float velocityConversionFactor = Physics.gravityAcceleration * 0.15f * Field.cellSize / timeStep;
    private final int trajectoryPointsNum = 30;
    static final Vector3 cannonballPos = new Vector3(0, 1 * Field.cellSize, (Field.depth / 2 - 5) * Field.cellSize);

    private final TrajectoryPoint trajectoryPoint = TrajectoryPointFactory.getTrajectoryPoint();

    private boolean stretching = false;
    private Vector3 velocity = new Vector3();
    private Vector3 posBuff = new Vector3();
    Cannonball cannonball;

    @Override
    public void stretch(float startX, float startY, float endX, float endY) {
        stretch(endX - startX, endY - startY);
    }

    @Override
    public void release(){
        stretching = false;

        cannonball.setVelocity(velocity);
        cannonball.acceptIn(Globals.shotFlagSetter, 0.05f);

        cannonball = null;
    }

    @Override
    public void charge(Cannonball cannonball){
        this.cannonball = cannonball;
        cannonball.setPos(CatapultImpl.cannonballPos);
    }

    @Override
    public Vector3 getInitialCannonballPos() {
        return cannonballPos.cpy();
    }

    @Override
    public void render(ModelBatch batch, Environment environment){
        if (!stretching) return;

        for (int i = 0; i < trajectoryPointsNum; i++) {
            posBuff.set(cannonballPos);
            float k = 0.04f * i;

            posBuff.add(velocity.x * k,
                    (velocity.y * k - Field.cellSize * (1 - cannonball.cellFraction)
                            - Physics.gravityAcceleration * Field.cellSize * (float) Math.pow(k * Field.cellSize, 2) / 2),
                    velocity.z * k - Field.cellSize * (1 - cannonball.cellFraction) / 2);

            trajectoryPoint.setPos(posBuff);
            trajectoryPoint.render(batch, environment);
        }
    }

    @Override
    public boolean isCharged() {
        return cannonball != null;
    }

    @Override
    public boolean isStretching() {
        return stretching;
    }

    private void stretch(float deltaX, float deltaY){
        float x = - deltaX * velocityConversionFactor * Field.cellSize;
        float y = deltaY * CatapultImpl.planeAngleTangent * velocityConversionFactor * Field.cellSize;
        float z = - deltaY / CatapultImpl.planeAngleTangent * velocityConversionFactor * Field.cellSize;

        stretching = true;
        velocity.set(x, y, z);
    }

    boolean stretchStartCoordsValid(float startX, float startY){
        float cannonballCenterX = 0.5f;
        float cannonballCenterY = 0.6f;
        float cannonballCenterRadiusSqr = 0.012f * Cannonball.cellFraction * Cannonball.cellFraction;

        return Math.pow(startX - cannonballCenterX, 2) +
                Math.pow(startY - cannonballCenterY, 2) <= cannonballCenterRadiusSqr;
    }
}
