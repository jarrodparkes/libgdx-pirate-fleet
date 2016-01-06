package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public abstract class GridObject {

    GridLocation origin;

    public GridObject(GridLocation origin) {
        this.origin = origin;
    }

    public abstract void render(float delta, ShapeRenderer renderer, Vector2 position);

    public abstract Array<GridLocation> locations();
}
