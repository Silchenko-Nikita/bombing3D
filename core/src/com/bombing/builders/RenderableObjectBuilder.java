package com.bombing.builders;

import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.bombing.gameobjs.GameObject;
import com.bombing.render.RenderableObject;


// Builder
public abstract class RenderableObjectBuilder {
    RenderableObject object;
    static protected ModelBuilder modelBuilder = new ModelBuilder();

    public void setObject(RenderableObject object) {
        this.object = object;
    }

    public RenderableObject getObject() {
        return object;
    }

    public abstract void setModelInstance();
}
