package com.bombing.field;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.bombing.gameobjs.Cannonball;
import com.bombing.render.ObjectsRemover;
import com.bombing.render.Renderable;
import com.bombing.render.RenderableComposite;
import com.bombing.utils.visitor.Visitor;

import java.util.ArrayList;

// Observer
public class Field implements Renderable, Json.Serializable{
    public static final float cellSize = 1.0f;
    public static final int rangeWidth = 10, rangeHeight = 10, rangeDepth = 10; // in cells
    public static final int width = 20, depth = 60; // in cells

    private RenderableComposite renderables;
    private Visitor coordsConverter;
    private String name;

    public Field(){
//        renderables = new RenderableComposite("renderables");
//        Ground ground = new Ground();
//
//        RenderableComposite boxes = new RenderableComposite("boxes");
//        RenderableComposite targets = new RenderableComposite("targets");
//
//        RenderableComposite cannonballs = new RenderableComposite("cannonballs");
//
//        RenderableComposite notRange = new RenderableComposite("notRange");
//        RenderableComposite range = new RenderableComposite("range");
//
//        Box box = new Box();
//        box.rangeCoords = new Vector3(1, 1, 3);
//        boxes.add(box);
//        box = new Box();
//        box.rangeCoords = new Vector3(-1, 1, 0);
//        boxes.add(box);
//
//        cannonballs.add(new Cannonball());
//
//        Target target = new Target();
//        target.rangeCoords = new Vector3(-1, 2, 0);
//        targets.add(target);
//
//        notRange.add(cannonballs);
//        notRange.add(ground);
//
//        range.add(boxes);
//        range.add(targets);
//
//        renderables.add(range);
//        renderables.add(notRange);

//        Json json = new Json();
//        json.setOutputType(JsonWriter.OutputType.json);
//        System.out.println(json.prettyPrint(this));
    }

    @Override
    public void render(ModelBatch batch, Environment environment){
        renderables.render(batch, environment);
    }

    @Override
    public void write(Json json) {
        json.writeValue("renderables", renderables);
        json.writeValue("coordsConverter", coordsConverter);
        json.writeValue("name", name);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        json.readField(this, "renderables", jsonData);
        json.readField(this, "coordsConverter", jsonData);
        json.readField(this, "name", jsonData);

        renderables.accept(coordsConverter);
    }


    public String getName(){
        return name;
    }

    public ArrayList<Cannonball> getCannonballs(){
        return renderables.getCannonballs();
    }

    public void clear(){
        renderables.accept(new ObjectsRemover());
    }
}
