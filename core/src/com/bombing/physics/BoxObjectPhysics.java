package com.bombing.physics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.Collision;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.bombing.field.Field;
import com.bombing.gameobjs.Box;

public class BoxObjectPhysics extends ObjectPhysics {

    public BoxObjectPhysics(Vector3 dimensions, Matrix4 objectTrans, float mass){
        super(mass);

        dimensions.scl(0.5f);
        btCollisionShape shape = new btBoxShape(dimensions);

        constructBody(shape);
        constructMotionState(objectTrans);

    }
}
