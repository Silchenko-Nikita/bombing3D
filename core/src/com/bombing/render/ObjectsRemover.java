package com.bombing.render;

import com.bombing.World;
import com.bombing.gameobjs.GameObject;
import com.bombing.utils.visitor.Visitor;

public class ObjectsRemover implements Visitor {
    @Override
    public void visit(Object object) {
        if (object instanceof RenderableObject) {
            ((RenderableObject)object).hide();
        }

        if (object instanceof GameObject) {
            World.getPhysics().removeGameObject((GameObject) object);
        }
    }
}
