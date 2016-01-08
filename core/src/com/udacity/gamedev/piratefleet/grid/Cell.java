package com.udacity.gamedev.piratefleet.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.gamedev.piratefleet.Constants;

public class Cell {

    public static final String TAG = Cell.class.getName();
    Vector2 position;
    GridObject object;
    CellState state;
    int row;
    int col;
    public Cell(Vector2 position, int row, int col) {
        this.position = position;
        this.row = row;
        this.col = col;

        object = null;
        state = CellState.UNTOUCHED;
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

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public void renderCell(float delta, ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Constants.GRID_CELL_COLOR);
        renderer.rect(position.x, position.y, Constants.GRID_CELL_SIZE, Constants.GRID_CELL_SIZE);
        renderer.end();
    }

    public void renderState(float delta, ShapeRenderer renderer) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Color c1 = (state == CellState.HIT) ? Constants.HIT_COLOR_1 : Constants.MISS_COLOR_1;
        Color c2 = (state == CellState.HIT) ? Constants.HIT_COLOR_2 : Constants.MISS_COLOR_2;
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(c1);
        renderer.triangle(
                position.x + Constants.GRID_CELL_SIZE * 5 / 16,
                position.y + Constants.GRID_CELL_SIZE * 3 / 16,
                position.x + Constants.GRID_CELL_SIZE * 14 / 16,
                position.y + Constants.GRID_CELL_SIZE * 8 / 16,
                position.x + Constants.GRID_CELL_SIZE * 5 / 16,
                position.y + Constants.GRID_CELL_SIZE * 12 / 16
        );
        renderer.setColor(c2);
        renderer.triangle(
                position.x + Constants.GRID_CELL_SIZE * 9 / 16,
                position.y + Constants.GRID_CELL_SIZE * 3 / 16,
                position.x + Constants.GRID_CELL_SIZE * 12 / 16,
                position.y + Constants.GRID_CELL_SIZE * 12 / 16,
                position.x + Constants.GRID_CELL_SIZE * 2 / 16,
                position.y + Constants.GRID_CELL_SIZE * 9 / 16
        );
        renderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public enum CellState {
        UNTOUCHED, HIT, MISS
    }
}
