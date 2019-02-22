package com.bombing.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bombing.field.Field;

public class UIRenderDecorator implements Renderer {
    private Renderer renderer;

    private Stage stage;
    private Label label;

    public UIRenderDecorator(Renderer renderer){
        this.renderer = renderer;

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("default.fnt"));
        labelStyle.fontColor = Color.BLACK;

        label = new Label("", labelStyle);
        label.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 12);
        label.setPosition(10, Gdx.graphics.getHeight() - 40);
        label.setAlignment(Align.center);
        stage.addActor(label);
    }

    @Override
    public void render(Renderable[] renderables) {
        renderer.render(renderables);
        stage.draw();
    }

    public void setLabelStr(String labelStr) {
        this.label.setText(labelStr);
    }
}
