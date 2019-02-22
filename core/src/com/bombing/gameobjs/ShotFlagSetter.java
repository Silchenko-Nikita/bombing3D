package com.bombing.gameobjs;

import com.bombing.World;
import com.bombing.render.RenderableObject;
import com.bombing.utils.visitor.Visitor;

public class ShotFlagSetter implements Visitor {
    @Override
    public void visit(Object object) {
        if (object instanceof Cannonball) {
            ((Cannonball)object).isShot = true;
        }
    }
}
