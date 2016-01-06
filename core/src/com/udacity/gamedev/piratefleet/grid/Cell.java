package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.piratefleet.Constants;

public class Cell {

    public static final String TAG = Cell.class.getName();

    Vector2 position;
    GridObject object;

    int row;
    int col;

    public Cell(Vector2 position, int row, int col) {
        this.position = position;
        object = null;
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "r: " + row + ", c: " + col;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    public GridObject getObject() {
        return object;
    }

    public void render(float delta, ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Constants.GRID_CELL_COLOR);
        renderer.rect(position.x, position.y, Constants.GRID_CELL_SIZE, Constants.GRID_CELL_SIZE);
        renderer.end();
    }
}
