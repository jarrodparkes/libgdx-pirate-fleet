package com.udacity.gamedev.piratefleet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Arrays;

public class PirateFleetScreen extends InputAdapter implements Screen {

    public static final String TAG = PirateFleetScreen.class.getName();

    // world viewport
    ShapeRenderer renderer;
    ExtendViewport viewport;

    // HUD viewport
    SpriteBatch batch;
    ScreenViewport textViewport;
    BitmapFont font;

    // game data
    PirateFleetGame game;
    GameManager manager;

    public PirateFleetScreen(PirateFleetGame game) {
        this.game = game;
    }

    @Override
    public void show () {
        // setup world
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        viewport = new ExtendViewport(Constants.WORLD_SIZE.x, Constants.WORLD_SIZE.y);
        manager = new GameManager();
        // setup HUD
        batch = new SpriteBatch();
        textViewport = new ScreenViewport();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize (int width, int height) {
        viewport.update(width, height, true);
        textViewport.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_WIDTH * 2);
    }

    @Override
    public void render (float delta) {
        // clear screen
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // setup drawing for HUD
        batch.setProjectionMatrix(textViewport.getCamera().combined);
        // draw HUD
        batch.begin();
        batch.end();
        // setup drawing for world
        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        // draw world
        manager.render(delta, renderer);
        renderer.end();
    }

    @Override
    public void dispose () {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}
}
