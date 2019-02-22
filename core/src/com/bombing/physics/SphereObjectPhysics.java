package com.bombing.physics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;


public class SphereObjectPhysics extends ObjectPhysics {

    public SphereObjectPhysics(float diameter, Matrix4 objectTrans, float mass){
        super(mass);

        btCollisionShape shape = new btSphereShape(diameter / 2.0f);

        constructBody(shape);
        constructMotionState(objectTrans);

        this.mass = mass;
    }
}
