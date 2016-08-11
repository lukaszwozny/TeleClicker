package com.mygdx.teleclicker.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.teleclicker.Core.AbstractScreen;
import com.mygdx.teleclicker.Core.Assets;
import com.mygdx.teleclicker.Enums.ScreenEnum;
import com.mygdx.teleclicker.Service.ScreenService;
import com.mygdx.teleclicker.TeleClicker;
import com.mygdx.teleclicker.ui.LoadingBar;

/**
 * Created by Senpai on 11/08/2016.
 */
public class LoadingScreen extends AbstractScreen {

    private Image logo;
    private Image loadingFrame;
    private Image loadingBarHidden;
    private Image loadingBarI;
    private Image screenBg;
    private Image loadingBg;

    private float startX, endX;
    private float percent;

    private Actor loadingBar;

    public LoadingScreen(){

    }

    @Override
    public void show() {

        // Tell the manager to load assets for the loading screen
        Assets.getInstance().manager.load("data/loading.pack", TextureAtlas.class);
        // Wait until they are finished loading
        Assets.getInstance().manager.finishLoading();

        // Get our textureatlas from the manager
        TextureAtlas atlas = Assets.getInstance().manager.get("data/loading.pack", TextureAtlas.class);

        // Grab the regions from the atlas and create some images
        logo = new Image(atlas.findRegion("libgdx-logo"));
        loadingFrame = new Image(new Texture(Gdx.files.internal("data/frame.png")));

        loadingBarHidden = new Image(atlas.findRegion("loading-bar-hidden"));

        screenBg = new Image(atlas.findRegion("screen-bg"));
        loadingBg = new Image(atlas.findRegion("loading-frame-bg"));

        // Add the loading bar animation
        loadingBarI = new Image(new Texture(Gdx.files.internal("data/bar.png")));

        Animation anim = new Animation(0.05f, atlas.findRegions("loading-bar-anim") );
        anim.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
        loadingBar = new LoadingBar(anim);

        // Or if you only need a static bar, you can do
        // loadingBar = new Image(atlas.findRegion("loading-bar1"));

        // Add all the actors to the stage
        addActor(screenBg);
        addActor(loadingBar);
        addActor(loadingBg);
        addActor(loadingBarHidden);
        addActor(loadingFrame);
        addActor(logo);
        addActor(loadingBarI);

        // Add everything to be loaded, for instance:
        Assets.getInstance().loadAll();
        // game.manager.load("data/assets1.pack", TextureAtlas.class);
        // game.manager.load("data/assets2.pack", TextureAtlas.class);
        // game.manager.load("data/assets3.pack", TextureAtlas.class);
    }

    @Override
    public void resize(int width, int height) {
        // Set our screen to always be XXX x 480 in size
        width = TeleClicker.WIDTH;
        height = TeleClicker.HEIGHT;

        // Make the background fill the screen
        screenBg.setSize(width, height);

        // Place the logo in the middle of the screen and 100 px up
        logo.setX((width - logo.getWidth()) / 2);
        logo.setY((height - logo.getHeight()) / 2 + 100);

        // Place the loading frame in the middle of the screen
        loadingFrame.setX((width - loadingFrame.getWidth()) / 2);
        loadingFrame.setY((height - loadingFrame.getHeight()) / 2);

        // Place the loading bar at the same spot as the frame, adjusted a few px
        loadingBar.setX(loadingFrame.getX() + 15);
        loadingBar.setY(loadingFrame.getY() + 5);

        // Place the image that will hide the bar on top of the bar, adjusted a few px
        loadingBarHidden.setX(loadingBar.getX() + 35);
        loadingBarHidden.setY(loadingBar.getY() - 3);
        // The start position and how far to move the hidden loading bar
        startX = loadingBarHidden.getX();
        endX = 440;

        // The rest of the hidden bar
        loadingBg.setSize(405, 50);
        loadingBg.setX(loadingBarHidden.getX() + 30);
        loadingBg.setY(loadingBarHidden.getY() + 3);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

//        if (Assets.getInstance().manager.update()) { // Load some, will return true if done loading
//                ScreenService.getInstance().setScreen(ScreenEnum.LOGIN);
//        }
//
//        // Interpolate the percentage to make it more smooth
//        percent = Interpolation.linear.apply(percent, Assets.getInstance().manager.getProgress(), 0.1f);
//
//        // Update positions (and size) to match the percentage
//        loadingBarHidden.setX(startX + endX * percent);
//        loadingBg.setX(loadingBarHidden.getX() + 30);
//        loadingBg.setWidth(TeleClicker.WIDTH - TeleClicker.WIDTH * percent);
//        loadingBg.invalidate();
//
//        // Show the loading screen
//        act();
//        draw();
    }

    @Override
    public void buildStage() {

    }

    @Override
    public void initBgTexture() {

    }
}
