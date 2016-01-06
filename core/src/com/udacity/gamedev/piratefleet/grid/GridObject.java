package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public abstract class GridObject {

    public static final String TAG = GridObject.class.getName();

    Grid grid;
    Cell origin;

    public GridObject(Grid grid, Cell origin) {
        this.grid = grid;
        this.origin = origin;
    }

    public Cell getOrigin() {
        return origin;
    }

    public Grid getGrid() {
        return grid;
    }

    public abstract void render(float delta, ShapeRenderer renderer);

    public abstract Array<Cell> locations();
}
