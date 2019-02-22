package com.bombing.render;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bombing.field.Field;
import com.bombing.gameobjs.Cannonball;
import com.bombing.utils.observer.Observer;

public class WorldRenderer implements Renderer, Observer {
    private ModelBatch batch;
    private PerspectiveCamera camera;
    private Environment environment;
    private Cannonball cannonballToLookAt;

    public WorldRenderer(){
        batch = new ModelBatch();

        createCamera();
        createEnvironment();
    }

    private void createCamera(){
        camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.far = 200 * Field.cellSize;
        camera.near = .1f * Field.cellSize;
        setDefaultCameraPos();
    }

    private void createEnvironment(){
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.45f, 0.5f, 0.5f, 0.8f));
        environment.add(new DirectionalLight().set(0.25f, 0.25f, 0.25f, 1f, -0.8f, -0.2f));
//        environment.add(new PointLight().set(Color.BLUE, 0, 5, 0, 100));
    }

    public void render(Renderable[] renderables){
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.65f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        updateCamera();
        camera.update();
        batch.begin(camera);
        if (renderables != null){
            for (Renderable renderable: renderables) {
                if (renderable != null){
                    renderable.render(batch, environment);
                }
            }
        }
        batch.end();
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

    @Override
    public void update(String cls, Object data) {
        if (cls.equals("catapult released")){
            cannonballToLookAt = (Cannonball) data;
        } else if (cls.equals("cannonball landed")){
            setDefaultCameraPos();
        }
    }

    public void setDefaultCameraPos(){
        camera.position.set(0, 2 * Field.cellSize,Field.depth/2 * Field.cellSize);
        camera.lookAt(0, 0, 0);
        cannonballToLookAt = null;
    }

    private void updateCamera(){
        if (cannonballToLookAt != null){
            Vector3 pos = cannonballToLookAt.getPos();
            camera.position.set(pos.x,
                    pos.y + 0.3f * Field.cellSize,
                    pos.z + 5f * Field.cellSize);
            camera.lookAt(pos);
        }
    }
}
