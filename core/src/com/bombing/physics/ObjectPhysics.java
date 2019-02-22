package com.bombing.physics;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.Collision;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;

// Adapter
public abstract class ObjectPhysics {
    float mass;
    btRigidBody body;
    private Vector3 posBuff = new Vector3();
    private ObjectMotionState motionState;

    ObjectPhysics(float mass){
        this.mass = mass;
    }

    void constructBody(btCollisionShape shape){
        Vector3 centerOfMass = new Vector3(0, 0, 0);
        btRigidBody.btRigidBodyConstructionInfo construction =
                new btRigidBody.btRigidBodyConstructionInfo(mass, null, shape, centerOfMass);

        body = new btRigidBody(construction);
        body.setActivationState(Collision.DISABLE_DEACTIVATION);
        body.setCollisionFlags(body.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_CUSTOM_MATERIAL_CALLBACK);
    }

    void constructMotionState(Matrix4 transform){
        motionState = new ObjectMotionState(transform);
        body.setMotionState(motionState);
    }

    public float getMass() {
        return mass;
    }

    public void setPos(Vector3 coords){
        Quaternion quaternion = new Quaternion();
        body.getWorldTransform().getRotation(quaternion);

        Matrix4 worldTrans = body.getWorldTransform();
        worldTrans.setToTranslation(coords);
        worldTrans.rotate(quaternion);

        body.setWorldTransform(worldTrans);
    }

    public Vector3 getPos(){
        return body.getWorldTransform().getTranslation(posBuff);
    }

    public void rotate(Vector3 axis, float degrees){
        body.setWorldTransform(body.getWorldTransform().rotate(axis, degrees));
    }

    public void setVelocity(Vector3 velocity){
        body.setLinearVelocity(velocity);
    }

    public Vector3 getVelocity() {
        return body.getLinearVelocity();
    }

    public void setWorldTransform(Matrix4 worldTrans){
        motionState.setWorldTransform(worldTrans);
    }
}
