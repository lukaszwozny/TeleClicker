package com.mygdx.teleclicker.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.teleclicker.Service.FontService;

import java.awt.*;

/**
 * Created by Senpai on 05/08/2016.
 */
public class MyLabel extends Group {
    private Skin skin;
    private Label label;
    private Table table;
    private String text;

    public MyLabel(String text) {
        this.text = text;
        initLabel();
        addActor(label);
    }

    @Override
    public void setColor(Color color) {
        label.remove();
        label = new Label(text, new Label.LabelStyle(FontService.getFont(), color));
        addActor(label);
    }

    public void setText(String text) {
        this.text = text;
        label.setText(text);
    }

    private void initLabel() {
        skin = new Skin(Gdx.files.internal("libgdx/uiskin.json"));
        label = new Label(text, new Label.LabelStyle(FontService.getFont(), Color.BLACK));

        table = new Table();
        label.setWrap(true);
        label.setWidth(100);
        table.add(label).width(10f);

        // ToDo
        setSize(label.getWidth(), label.getHeight());
    }

    public float getLabelWidth() {
        return label.getGlyphLayout().width;
    }
}
