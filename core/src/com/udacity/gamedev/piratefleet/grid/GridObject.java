package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class GridObject {

    GridLocation location;

    public GridObject(GridLocation location) {
        this.location = location;
    }

    public abstract void render(float delta, ShapeRenderer renderer);
}
