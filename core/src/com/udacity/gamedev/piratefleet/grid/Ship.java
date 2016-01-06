package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Ship extends GridObject {

    int length;

    public Ship(GridLocation location, int length) {
        super(location);
        this.length = length;
    }

    public void render(float delta, ShapeRenderer renderer) {

    }
}
