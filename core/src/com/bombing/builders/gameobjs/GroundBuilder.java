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
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.bombing.World;
import com.bombing.field.Field;
import com.bombing.gameobjs.Ground;
import com.bombing.gameobjs.Target;
import com.bombing.physics.BoxObjectPhysics;
import com.bombing.physics.SphereObjectPhysics;

public class GroundBuilder extends GameObjectBuilder {
    @Override
    public void setModelInstance() {
        object.modelInstance = new ModelInstance(modelBuilder.createBox(Ground.width * Field.cellSize,
                Ground.height * Field.cellSize, Ground.depth * Field.cellSize,
                new Material(
                        ColorAttribute.createDiffuse(Color.FOREST),
                        ColorAttribute.createSpecular(Color.RED), FloatAttribute.createShininess(1f)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates));
    }

    @Override
    public void setPhysics() {
        object.objectPhysics = new BoxObjectPhysics(new Vector3(Ground.width * Field.cellSize,
                Ground.height * Field.cellSize, Ground.depth * Field.cellSize),
                object.modelInstance.transform, Ground.mass);
        World.getPhysics().addGameObject(object);
    }
}
