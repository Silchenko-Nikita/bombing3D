package com.bombing.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.bombing.builders.ObjectBuildDirector;
import com.bombing.builders.RenderableObjectBuildDirector;
import com.bombing.builders.RenderableObjectBuilder;
import com.bombing.gameobjs.Cannonball;
import com.bombing.utils.visitor.Visitor;

import java.util.ArrayList;

public abstract class RenderableObject implements RenderableComponent, Json.Serializable {
    public ModelInstance modelInstance;
    protected ObjectBuildDirector buildDirector;
    protected RenderableObjectBuilder builder;
    private boolean hidden = false;

    private Vector3 posBuff = new Vector3();

    protected ArrayList<Visitor> deferredVisitors = new ArrayList<Visitor>();
    protected ArrayList<Float> visitorsCountdowns = new ArrayList<Float>();

    protected ArrayList<Integer> buffer = new ArrayList<Integer>();

    public RenderableObject(){
        buildDirector = new RenderableObjectBuildDirector();
    }

    protected void build() {
        if (builder != null && buildDirector != null) {
            builder.setObject(this);
            buildDirector.buildObject(builder);
        }
    }

    @Override
    public void render(ModelBatch batch, Environment environment){
        updateDeferredVisitors();

        if (modelInstance != null && !hidden) {
            batch.render(modelInstance, environment);
        }
    }

    @Override
    public void add(RenderableComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(RenderableComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Cannonball> getCannonballs() {
        return null;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void acceptIn(Visitor visitor, float seconds) {
        if (!deferredVisitors.contains(visitor)){
            deferredVisitors.add(visitor);
            visitorsCountdowns.add(seconds);
        }
    }

    protected void updateDeferredVisitors(){
        for (int i = 0; i < visitorsCountdowns.size(); i++) {
            Float visitCountdown = visitorsCountdowns.get(i);
            visitCountdown -= Gdx.graphics.getDeltaTime();
            visitorsCountdowns.set(i, visitCountdown);

            if (visitCountdown < 0){
                accept(deferredVisitors.get(i));
                buffer.add(i);
            }
        }

        for (Integer i: buffer){
            deferredVisitors.remove((int) i);
            visitorsCountdowns.remove((int) i);
        }

        buffer.clear();
    }

    public void hide(){
        hidden = true;
    }

    public void setPos(Vector3 coords){
        modelInstance.transform.setToTranslation(coords);
    }

    public Vector3 getPos(){
        return modelInstance.transform.getTranslation(posBuff);
    }

    public void rotate(Vector3 axis, float degrees){
        modelInstance.transform.rotate(axis, degrees);
    }

    // ser
    @Override
    public void write(Json json) {
    }

    @Override
    public void read(Json json, JsonValue jsonData) {

    }
}
