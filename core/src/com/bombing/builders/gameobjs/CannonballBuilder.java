package com.bombing.builders.gameobjs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.Collision;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.bombing.World;
import com.bombing.field.Field;
import com.bombing.gameobjs.Cannonball;
import com.bombing.gameobjs.Target;
import com.bombing.physics.SphereObjectPhysics;

public class CannonballBuilder extends GameObjectBuilder {
    @Override
    public void setModelInstance() {
        object.modelInstance = new ModelInstance(modelBuilder.createSphere(Cannonball.cellFraction * Field.cellSize,
                Cannonball.cellFraction * Field.cellSize, Cannonball.cellFraction * Field.cellSize,
                20, 20,
                new Material(new TextureAttribute(TextureAttribute.Diffuse, new Texture("bird.jpg"))),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates));
    }

    @Override
    public void setPhysics() {
        object.objectPhysics = new SphereObjectPhysics(Cannonball.cellFraction * Field.cellSize,
                object.modelInstance.transform, Cannonball.mass);
        World.getPhysics().addGameObject(object);

        object.rotate(new Vector3(0, 1, 0), -135);
    }
}
