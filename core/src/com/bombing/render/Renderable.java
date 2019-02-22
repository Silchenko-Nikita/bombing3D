package com.bombing.render;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public interface Renderable {
    void render(ModelBatch batch, Environment environment);
}
