package com.bombing.render;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.bombing.gameobjs.Cannonball;
import com.bombing.utils.visitor.Visitor;

import java.util.ArrayList;


// Composite
public class RenderableComposite implements RenderableComponent {
    private ArrayList<RenderableComponent> children = new ArrayList<RenderableComponent>();
    private String groupName;

    public RenderableComposite(){ }

    public RenderableComposite(String groupName){
        this.groupName = groupName;
    }

    @Override
    public void render(ModelBatch batch, Environment environment) {
        for (RenderableComponent component: children){
            component.render(batch, environment);
        }
    }

    @Override
    public void add(RenderableComponent component) {
        children.add(component);
    }

    @Override
    public void remove(RenderableComponent component) {
        children.remove(component);
    }

    @Override
    public void accept(Visitor visitor) {
        for (RenderableComponent child: children){
            child.accept(visitor);
        }
    }

    @Override
    public void acceptIn(Visitor visitor, float seconds) {
        for (RenderableComponent child: children){
            child.acceptIn(visitor, seconds);
        }
    }

    @Override
    public ArrayList<Cannonball> getCannonballs() {
        ArrayList<Cannonball> cannonballs;

        if (groupName.equals("cannonballs")){
            cannonballs = new ArrayList<Cannonball>();

            for(RenderableComponent child: children){
                cannonballs.add((Cannonball) child);
            }

            return cannonballs;
        }

        for (RenderableComponent component: children){
            cannonballs = component.getCannonballs();

            if (cannonballs != null){
                return cannonballs;
            }
        }

        return null;
    }

    public String getGroupName() {
        return groupName;
    }
}
