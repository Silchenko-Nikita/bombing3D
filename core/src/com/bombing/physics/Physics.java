package com.bombing.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.physics.bullet.dynamics.btConstraintSolver;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
import com.bombing.field.Field;
import com.bombing.gameobjs.GameObject;
import com.bombing.gameobjs.GameObjectsMediator;


// Adapter
public class Physics {
    private btCollisionConfiguration collisionConfig;
    private btDispatcher dispatcher;
    private btBroadphaseInterface broadphase;
    private btDynamicsWorld dynamicsWorld;
    private btConstraintSolver constraintSolver;

    private ObjectsContactListener contactListener;

    public final static float gravityAcceleration = 5f;
    public final static float timeStep = 1f/60f;

    private GameObjectsMediator gameObjectsMediator;

    class ObjectsContactListener extends ContactListener {
        @Override
        public boolean onContactAdded (int userValue0, int partId0, int index0, int userValue1, int partId1, int index1) {

            gameObjectsMediator.collide(userValue0, userValue1);

            return true;
        }
    }

    public Physics(){
        Bullet.init();

        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);
        broadphase = new btDbvtBroadphase();
        constraintSolver = new btSequentialImpulseConstraintSolver();

        dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher, broadphase, constraintSolver, collisionConfig);
        dynamicsWorld.setGravity(new Vector3(0, -gravityAcceleration * Field.cellSize, 0));

        gameObjectsMediator = new GameObjectsMediator();
        contactListener = new ObjectsContactListener();
    }

    public void update(){
        float delta = Math.min(1f / 30f, Gdx.graphics.getDeltaTime());
        dynamicsWorld.stepSimulation(delta, 5, timeStep);
    }

    public void addGameObject(GameObject gameObject){
        gameObject.objectPhysics.body.setUserValue(gameObjectsMediator.addGameObject(gameObject));
        dynamicsWorld.addRigidBody(gameObject.objectPhysics.body);
    }

    public void removeGameObject(GameObject gameObject){
        gameObjectsMediator.removeGameObject(gameObject);
        dynamicsWorld.removeRigidBody(gameObject.objectPhysics.body);
    }

    public GameObjectsMediator getGameObjectsMediator(){
        return gameObjectsMediator;
    }
}
