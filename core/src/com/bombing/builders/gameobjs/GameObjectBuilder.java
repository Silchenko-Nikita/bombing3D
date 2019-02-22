package com.bombing.builders.gameobjs;

import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.bombing.builders.RenderableObjectBuilder;
import com.bombing.gameobjs.GameObject;
import com.bombing.render.RenderableObject;

// Builder
public abstract class GameObjectBuilder extends RenderableObjectBuilder {
    GameObject object;

    public void setObject(RenderableObject object) {
        this.object = (GameObject) object;
    }

    public GameObject getObject() {
        return object;
    }

    public abstract void setPhysics();
}
