package com.mygdx.teleclicker.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.teleclicker.TeleClicker;

/**
 * Created by Senpai on 21.07.2016.
 */
public abstract class AbstractScreen extends Stage implements Screen {

    protected Texture bgTexture;

    public AbstractScreen(){
        super(new StretchViewport(TeleClicker.WIDTH,TeleClicker.HEIGHT,new OrthographicCamera()));
    }

    public abstract void buildStage();

    public abstract void initBgTexture();

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl20.glClearColor(1,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.act(delta);
        super.draw();
    }

    @Override
    public void resize(int width, int height) {
        getViewport().update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
