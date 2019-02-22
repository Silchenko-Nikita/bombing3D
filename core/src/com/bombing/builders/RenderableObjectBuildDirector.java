package com.bombing.builders;

import com.bombing.builders.gameobjs.GameObjectBuilder;
import com.bombing.gameobjs.GameObject;
import com.bombing.render.RenderableObject;

public class RenderableObjectBuildDirector implements ObjectBuildDirector {

    @Override
    public RenderableObject buildObject(RenderableObjectBuilder objectBuilder) {
        objectBuilder.setModelInstance();
        return objectBuilder.getObject();
    }
}
