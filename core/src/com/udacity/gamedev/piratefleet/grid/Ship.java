package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.Color;
import com.udacity.gamedev.piratefleet.Constants;

public class Ship extends GridObject {

    public static final String TAG = Ship.class.getName();

    public enum Orientation {
        VERTICAL, HORIZONTAL
    }

    int length;
    Orientation orientation;

    public Ship(GridLocation location, int length, Orientation orientation) {
        super(location);
        this.length = length;
        this.orientation = orientation;
    }

    public void render(float delta, ShapeRenderer renderer, Vector2 position) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLACK);
        renderer.rect(position.x + Constants.GRID_CELL_SIZE/4, position.y + Constants.GRID_CELL_SIZE/4, Constants.GRID_CELL_SIZE/2, Constants.GRID_CELL_SIZE/2);
        renderer.end();
    }

    public Array<GridLocation> locations() {
        Array<GridLocation> cells = new Array<GridLocation>();
        for (int i = 0; i < length; ++i) {
            GridLocation location = (orientation == Orientation.HORIZONTAL) ?
                    origin.locationFromOffset(0, i) : origin.locationFromOffset(i, 0);
            cells.add(location);
        }
        return cells;
    }
}
