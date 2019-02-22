package com.bombing;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import com.badlogic.gdx.Gdx;
import com.bombing.catapult.Catapult;
import com.bombing.catapult.CatapultCharger;
import com.bombing.catapult.CatapultProxy;
import com.bombing.field.Field;
import com.bombing.field.FieldLoader;
import com.bombing.gameobjs.Cannonball;
import com.bombing.gameobjs.GameObjectsMediator;
import com.bombing.physics.Physics;
import com.bombing.render.Renderable;
import com.bombing.render.UIRenderDecorator;
import com.bombing.render.WorldRenderer;
import com.bombing.utils.observer.Observer;

public class World implements Observer {
    private static Physics physics = new Physics();

    private Field field;
    private FieldLoader fieldLoader;

    private WorldRenderer renderer;
    private UIRenderDecorator richRenderer;
    private GameInputProcessor inputProcessor;

    private CatapultProxy catapult;
    private CatapultCharger catapultCharger;

    private GameObjectsMediator gameObjectsMediator;

    private Renderable[] renderables = new Renderable[]{ field, catapult };

    private int fieldId = 0;
    private int fieldSucceedNum = 0;

    public World() {
        renderer = new WorldRenderer();
        richRenderer = new UIRenderDecorator(renderer);

        catapult = new CatapultProxy();
        catapultCharger = new CatapultCharger(catapult);

        fieldLoader = new FieldLoader();
        nextField();

        gameObjectsMediator = physics.getGameObjectsMediator();

        gameObjectsMediator.registerObserver("cannonball landed", this);
        gameObjectsMediator.registerObserver("targets are over", this);
        gameObjectsMediator.registerObserver("cannonball landed", renderer);
        catapult.registerObserver("catapult released", renderer);

        inputProcessor = new GameInputProcessor(catapult);

        Gdx.input.setInputProcessor(inputProcessor);

        updateRenderables();
    }

    public void render(float delta){
        physics.update();

        richRenderer.render(renderables);
    }

    public static Physics getPhysics() {
        return physics;
    }

    private void nextField(){
        if (field != null){
            field.clear();
        }

        fieldId++;
        field = fieldLoader.loadField(fieldId);
        renderables[0] = field;

        if (field == null) {
            renderables = null;
            richRenderer.setLabelStr("Game Over. Fields succeed: " + fieldSucceedNum);
        } else {
            richRenderer.setLabelStr("Fields succeed: " + fieldSucceedNum);

            catapultCharger.setCannonballs(field.getCannonballs());
            catapultCharger.charge();
        }
        renderer.setDefaultCameraPos();
    }

    private void updateRenderables(){
        renderables[0] = field;
        renderables[1] = catapult;
    }

    // Observer
    @Override
    public void update(String cls, Object data) {
        if (cls.equals("targets are over")){
            fieldSucceedNum++;
            nextField();
        } else if (cls.equals("cannonball landed")){
            if (catapultCharger.cannonballsOver()){
                nextField();
            } else {
                catapultCharger.charge();
            }
        }
    }
}
