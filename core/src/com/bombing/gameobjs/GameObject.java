package com.bombing.gameobjs;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.bombing.builders.RenderableObjectBuildDirector;
import com.bombing.builders.gameobjs.GameObjectBuildDirector;
import com.bombing.builders.gameobjs.GameObjectBuilder;
import com.bombing.physics.ObjectPhysics;
import com.bombing.render.RenderableObject;

public abstract class GameObject extends RenderableObject {
    public ObjectPhysics objectPhysics;

    public GameObject() {
        buildDirector = new GameObjectBuildDirector();
    }

    @Override
    public void setPos(Vector3 coords) {
        objectPhysics.setPos(coords);
    }

    @Override
    public Vector3 getPos() {
        return objectPhysics.getPos();
    }

    @Override
    public void rotate(Vector3 axis, float degrees) {
        objectPhysics.rotate(axis, degrees);
    }

    public void setVelocity(Vector3 velocity) {
        objectPhysics.setVelocity(velocity);
    }

    public Vector3 getVelocity() {
        return objectPhysics.getVelocity();
    }
}