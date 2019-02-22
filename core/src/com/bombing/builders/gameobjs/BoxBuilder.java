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
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.bombing.World;
import com.bombing.field.Field;
import com.bombing.gameobjs.Box;
import com.bombing.physics.BoxObjectPhysics;

public class BoxBuilder extends GameObjectBuilder {
    @Override
    public void setModelInstance() {
        object.modelInstance = new ModelInstance(modelBuilder.createBox(Box.cellFraction * Field.cellSize,
                Box.cellFraction * Field.cellSize, Box.cellFraction * Field.cellSize,
                new Material(new TextureAttribute(TextureAttribute.Diffuse, new Texture("box.jpg"))),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates));
    }

    @Override
    public void setPhysics() {
        object.objectPhysics = new BoxObjectPhysics(new Vector3(Box.cellFraction * Field.cellSize,
                Box.cellFraction * Field.cellSize, Box.cellFraction * Field.cellSize),
                object.modelInstance.transform, Box.mass);
        World.getPhysics().addGameObject(object);
    }
}

