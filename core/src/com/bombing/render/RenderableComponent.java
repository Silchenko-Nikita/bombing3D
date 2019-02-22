package com.bombing.render;


import com.bombing.gameobjs.Cannonball;
import com.bombing.utils.visitor.VisitorAccepter;

import java.util.ArrayList;

public interface RenderableComponent extends Renderable, VisitorAccepter {

    void add(RenderableComponent component);
    void remove(RenderableComponent component);
    ArrayList<Cannonball> getCannonballs();
}
