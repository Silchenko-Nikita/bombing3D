package com.bombing.builders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.bombing.builders.gameobjs.GameObjectBuilder;
import com.bombing.field.Field;
import com.bombing.render.TrajectoryPoint;

public class TrajectoryPointBuilder extends RenderableObjectBuilder {
    @Override
    public void setModelInstance() {
        object.modelInstance = new ModelInstance(modelBuilder.createSphere(TrajectoryPoint.cellFraction * Field.cellSize,
                TrajectoryPoint.cellFraction * Field.cellSize, TrajectoryPoint.cellFraction * Field.cellSize,
                20, 20,
                new Material(ColorAttribute.createDiffuse(Color.WHITE),
                        ColorAttribute.createSpecular(Color.RED), FloatAttribute.createShininess(1f)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal));
    }
}
