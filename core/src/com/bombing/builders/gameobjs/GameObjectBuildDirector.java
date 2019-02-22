package com.bombing.builders.gameobjs;

import com.bombing.builders.ObjectBuildDirector;
import com.bombing.builders.RenderableObjectBuilder;
import com.bombing.gameobjs.GameObject;
import com.bombing.render.RenderableObject;

public class GameObjectBuildDirector implements ObjectBuildDirector {

    private GameObject buildGameObject(GameObjectBuilder gameObjectBuilder){
        gameObjectBuilder.setModelInstance();
        gameObjectBuilder.setPhysics();
        return gameObjectBuilder.getObject();
    }

    @Override
    public RenderableObject buildObject(RenderableObjectBuilder objectBuilder) {
        return buildGameObject((GameObjectBuilder) objectBuilder);
    }
}
